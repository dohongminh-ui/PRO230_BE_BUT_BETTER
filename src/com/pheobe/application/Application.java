package com.pheobe.application;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.pheobe.application.component.PanelCover;
import com.pheobe.application.component.PanelLoading;
import com.pheobe.application.component.PanelLoginAndRegister;
import com.pheobe.application.form.MainForm;
import com.pheobe.connection.DBcontext;
import com.pheobe.model.Customer;
import com.pheobe.service.ServiceUser;
import com.pheobe.application.manager.BreadcrumbManager;
import com.pheobe.application.form.other.FormDashboard;
import com.pheobe.application.menu.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.mail.Message;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import raven.toast.Notifications;
import com.pheobe.service.Customer_DAO;

/**
 *
 * @author pheoebeo
 */
public class Application extends javax.swing.JFrame {

    private static Application app;
    private final MainForm mainForm;
    
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoading loading;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private ServiceUser service;
    private JLayeredPane bg;

    private static Customer currentUser;

    public Application() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm();
        initAuthComponents();
        setContentPane(bg);
        Notifications.getInstance().setJFrame(this);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
    }
    
    private void autoLoginIfRemembered() {
        if (loginAndRegister.isRememberMe() && loginAndRegister.getDataLogin() != null) {
            System.out.println("auto login with remembered credentials");
            authenticateUser();
        }
    }
    
    private void initAuthComponents() {
        service = new ServiceUser();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        loading = new PanelLoading();
        bg = new JLayeredPane();
        
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                register();
            }
        };
        
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                authenticateUser();
            }
        };
        
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin);
        
        bg.setLayout(layout);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos 1al 0 n 100%");
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

    private void register() {
        Customer user = loginAndRegister.getUser();
        try {
            if (service.checkDuplicateUser(user.getUserName())) {
                showMessage(Notifications.Type.ERROR, "Username already exists");
            } else if (service.checkDuplicateEmail(user.getEmail())) {
                showMessage(Notifications.Type.ERROR, "Email already exists");
            } else {
                service.insertUser(user);
                showMessage(Notifications.Type.SUCCESS, "Registration successful!");
                System.out.println("Register successful");
                loginAndRegister.showRegister(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage(Notifications.Type.ERROR, "Error during registration: " + e.getMessage());
        }
    }

    private void authenticateUser() {
        // try {
        //     Customer testUser = new Customer();
        //     if (loginAndRegister.isRememberMe()) {
        //         testUser.setUserName("mmb");
        //         testUser.setEmail(loginAndRegister.getDataLogin().getEmail());
        //         testUser.setPassword(loginAndRegister.getDataLogin().getPassword());
        //     } else {
        //         testUser.setUserName("mmb");
        //         testUser.setEmail("test@example.com");
        //         testUser.setPassword("password");
        //     }
            
        //     currentUser = testUser;
            
        //     login();
        //     showForm(mainForm);
        //     setSelectedMenu(0, 0);
            
        //     showMessage(Notifications.Type.SUCCESS, "Logged in as " + currentUser.getUserName());
            
        // } catch (Exception e) {
        //     showMessage(Notifications.Type.ERROR, "Error during login: " + e.getMessage());
        //     e.printStackTrace();
        // }
        
        Customer data = loginAndRegister.getDataLogin();
        try {
            Customer user = service.login(data);
            if (user != null) {
                currentUser = user;
                
                login();
                showForm(mainForm);
                setSelectedMenu(0, 0);
                
                showMessage(Notifications.Type.SUCCESS, "Logged in as " + currentUser.getUserName());
            } else {
                showMessage(Notifications.Type.ERROR, "Email and Password incorrect");
            }
        } catch (SQLException e) {
            showMessage(Notifications.Type.ERROR, "Error during login");
        }
    }

    public static void showMessage(Notifications.Type type, String message) {
        System.out.println("Showing notification: " + message + " (Type: " + type + ")");
        
        SwingUtilities.invokeLater(() -> {
            Notifications.getInstance().show(type, Notifications.Location.TOP_CENTER, message);
        });
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());

        System.out.println(component.getClass().getSimpleName());

        if (component instanceof FormDashboard) {
            BreadcrumbManager.getInstance().navigateToHome();
        }
        
        if (!(component instanceof MainForm)) {
            app.mainForm.showForm(component);
        }
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        
        if (currentUser != null) {
            Menu menu = app.mainForm.getMenu();

            if (menu != null) {
                menu.setUsername(currentUser.getUserName());
                
                System.out.println("Setting profile picture on login. Current user img: " + (currentUser.getImg() != null ? currentUser.getImg() : "null"));
                
                if (currentUser.getImg() != null && !currentUser.getImg().isEmpty()) {
                    menu.setUserProfileIconFromFile(currentUser.getImg());
                } else {
                    menu.setUserProfileIconFromFile("0.png");
                }

                menu.updateMenu(currentUser);
            }

            System.out.println("Login successful");
        } else {
            System.out.println("Login error: No current user");
        }
        
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        app.loginAndRegister.clearSavedCredentials();
        app.loginAndRegister.clearFormFields();
        app.setContentPane(app.bg);
        app.loginAndRegister.showRegister(false);
        SwingUtilities.updateComponentTreeUI(app.bg);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            DBcontext.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("com/pheobe/theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        
        FlatMacLightLaf.setup();
        
        java.awt.EventQueue.invokeLater(() -> {
            app = new Application();
            app.setVisible(true);
            SwingUtilities.invokeLater(() -> {
                app.autoLoginIfRemembered();
            });
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    // Getter for the current user
    public static Customer getCurrentUser() {
        return currentUser;
    }

    public static MainForm getMainForm() {
        return app.mainForm;
    }

    public static void refreshCurrentUser() {
        if (currentUser != null) {
            Customer_DAO customerDAO = new Customer_DAO();
            int userId = currentUser.getIdCustomer();
            Customer refreshedUser = customerDAO.selectById(userId);
            
            if (refreshedUser != null) {
                String originalImg = currentUser.getImg();
                currentUser = refreshedUser;
                System.out.println("Current user refreshed. Image from DB: " + currentUser.getImg());
                
                Menu menu = app.mainForm.getMenu();
                if (menu != null) {
                    menu.setUsername(currentUser.getUserName());
                    
                    if (currentUser.getImg() != null && !currentUser.getImg().isEmpty()) {
                        System.out.println("Setting menu profile picture to: " + currentUser.getImg());
                        menu.setUserProfileIconFromFile(currentUser.getImg());
                    } else if (originalImg != null && !originalImg.isEmpty()) {
                        System.out.println("Using original image: " + originalImg);
                        currentUser.setImg(originalImg);
                        menu.setUserProfileIconFromFile(originalImg);
                    } else {
                        System.out.println("Setting default profile picture");
                        menu.setUserProfileIconFromFile("0.png");
                    }
                }
            }
        }
    }
}
