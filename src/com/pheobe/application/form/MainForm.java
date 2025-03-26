package com.pheobe.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.pheobe.application.Application;
import com.pheobe.application.form.other.FormCart1;
import com.pheobe.application.form.other.FormCustomerInfromation;
import com.pheobe.application.form.other.FormDashboard;
import com.pheobe.application.form.other.FormHistory;
import com.pheobe.application.menu.Menu;
import com.pheobe.application.menu.MenuAction;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import javax.swing.Timer;

/**
 *
 * @author pheoebe
 */
public class MainForm extends JLayeredPane {

    public MainForm() {
        init();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowActivated(java.awt.event.WindowEvent evt) {
                Application.refreshCurrentUser();
            }
        });
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        menu = new Menu();
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("com/pheobe/icon/svg/" + icon, 0.8f));
    }

    private void initMenuEvent() {
        menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
            // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
            if (index == 0) {
                Application.showForm(new FormDashboard());
            } else if (index == 1) {
               if (subIndex == 1) {
                Application.showForm(new FormDashboard(2));
               } else if (subIndex == 2) {
                Application.showForm(new FormDashboard(3));
               } else if (subIndex == 3) {
                Application.showForm(new FormDashboard(4));
               }
            } else if (index == 2) {
                if (subIndex == 1) {
                    Application.showForm(new FormDashboard(2));
                } else if (subIndex == 2) {
                Application.showForm(new FormDashboard(3));
                } else if (subIndex == 3) {
                Application.showForm(new FormDashboard(4));
                }
            } else if (index == 3) {
                Application.showForm(new FormCart1());
            } else if (index == 4) {
                Application.showForm(new FormHistory());
            } else if (index == 5) {
                if (subIndex == 1) {
                    Application.showForm(new FormCustomerInfromation());
                } else if (subIndex == 2) {
                    Application.logout();
                }
            } else {
                action.cancel();
            }
        });
    }

    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("com/pheobe/icon/svg/" + icon, 0.8f));
        
        menu.setMenuFull(full);
        
        Timer animationUpdateTimer = new Timer(16, e -> {
            revalidate();
            repaint();
            
            if (!menu.isAnimating()) {
                ((Timer)e.getSource()).stop();
            }
        });
        animationUpdateTimer.start();
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    public Menu getMenu() {
        return menu;
    }

    private Menu menu;
    private JPanel panelBody;
    private JButton menuButton;

    private class MainFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                
                int menuWidth = UIScale.scale(menu.getCurrentWidth());
                
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                
                int menubX;
                float animationProgress = (float)(menuWidth - UIScale.scale(menu.getMenuMinWidth())) / 
                                         (float)(UIScale.scale(menu.getMenuMaxWidth() - menu.getMenuMinWidth()));
                if (ltr) {
                    float offsetFactor = 0.5f - (0.2f * (1.0f - animationProgress));
                    menubX = (int) (x + menuWidth - (menuButtonWidth * offsetFactor));
                } else {
                    float offsetFactor = 0.5f + (0.2f * (1.0f - animationProgress));
                    menubX = (int) (menuX - (menuButtonWidth * offsetFactor));
                }
                
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
                
                parent.repaint();
            }
        }
    }
}
