package com.pheobe.application.component;

import com.pheobe.model.Customer;
import com.pheobe.swing.Button;
import com.pheobe.swing.MyPasswordField;
import com.pheobe.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.util.prefs.Preferences;
import java.util.ArrayList;
import java.util.List;
import raven.toast.Notifications;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public Customer getDataLogin() {
        return dataLogin;
    }

    public Customer getUser() {
        return user;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    private Customer user;
    private Customer dataLogin;
    private MyTextField txtEmailLogin;
    private MyPasswordField txtPassLogin;
    private MyTextField txtUserRegister;
    private MyTextField txtEmailRegister;
    private MyPasswordField txtPassRegister;
    private JCheckBox chkRememberMe;
    private boolean rememberMe;
    private Preferences prefs;

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        login.setVisible(false);
        register.setVisible(true);
        
        prefs = Preferences.userNodeForPackage(PanelLoginAndRegister.class);
        if (prefs.getBoolean("remember_me", false)) {
            loadSavedCredentials();
        }
    }

    private void initRegister(ActionListener eventRegister) {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 179, 72));
        register.add(label);
        txtUserRegister = new MyTextField();
        txtUserRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/com/pheobe/icon/user.png")));
        txtUserRegister.setHint("Name");
        register.add(txtUserRegister, "w 60%");
        txtEmailRegister = new MyTextField();
        txtEmailRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/com/pheobe/icon/mail.png")));
        txtEmailRegister.setHint("Email");
        register.add(txtEmailRegister, "w 60%");
        txtPassRegister = new MyPasswordField();
        txtPassRegister.setPrefixIcon(new ImageIcon(getClass().getResource("/com/pheobe/icon/pass.png")));
        txtPassRegister.setHint("Password");
        register.add(txtPassRegister, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 179, 72));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (validateRegisterForm()) {
                    String userName = txtUserRegister.getText().trim();
                    String email = txtEmailRegister.getText().trim();
                    String password = String.valueOf(txtPassRegister.getPassword());
                    user = new Customer();
                    user.setIdCustomer(0);
                    user.setUserName(userName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setName(userName);
                    
                    // Call the external register event after validation
                    if (eventRegister != null) {
                        eventRegister.actionPerformed(ae);
                    }
                }
            }
        });
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
    }

    private void initLogin(ActionListener eventLogin) {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]5[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(255, 179, 72));
        login.add(label);
        txtEmailLogin = new MyTextField();
        txtEmailLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/com/pheobe/icon/mail.png")));
        txtEmailLogin.setHint("Email");
        login.add(txtEmailLogin, "w 60%");
        txtPassLogin = new MyPasswordField();
        txtPassLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/com/pheobe/icon/pass.png")));
        txtPassLogin.setHint("Password");
        login.add(txtPassLogin, "w 60%");
        
        chkRememberMe = new JCheckBox("Remember Me");
        chkRememberMe.setFont(new Font("sansserif", 0, 12));
        chkRememberMe.setForeground(new Color(100, 100, 100));
        chkRememberMe.setBackground(new Color(255, 255, 255));
        chkRememberMe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(chkRememberMe, "w 60%, left");
        
        Button cmd = new Button();
        cmd.setBackground(new Color(255, 179, 72));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (validateLoginForm()) {
                    String email = txtEmailLogin.getText().trim();
                    String password = String.valueOf(txtPassLogin.getPassword());
                    rememberMe = chkRememberMe.isSelected();
                    
                    dataLogin = new Customer();
                    dataLogin.setEmail(email);
                    dataLogin.setPassword(password);
                    
                    if (rememberMe) {
                        saveCredentials(email, password);
                    }
                    
                    if (eventLogin != null) {
                        eventLogin.actionPerformed(ae);
                    }
                }
            }
        });
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");
    }
    
    private boolean validateLoginForm() {
        resetFormErrors();
        List<String> errors = new ArrayList<>();
        
        if (txtEmailLogin.getText().trim().isEmpty()) {
            txtEmailLogin.setError(true);
            errors.add("Email is required");
        }
        
        if (txtPassLogin.getPassword().length == 0) {
            txtPassLogin.setError(true);
            errors.add("Password is required");
        }
        
        if (!errors.isEmpty()) {
            showValidationErrors(errors);
            return false;
        }
        
        return true;
    }
    
    private boolean validateRegisterForm() {
        resetFormErrors();
        List<String> errors = new ArrayList<>();
        
        if (txtUserRegister.getText().trim().isEmpty()) {
            txtUserRegister.setError(true);
            errors.add("Username is required");
        }
        
        if (txtEmailRegister.getText().trim().isEmpty()) {
            txtEmailRegister.setError(true);
            errors.add("Email is required");
        }
        
        if (txtPassRegister.getPassword().length == 0) {
            txtPassRegister.setError(true);
            errors.add("Password is required");
        }
        
        if (!errors.isEmpty()) {
            showValidationErrors(errors);
            return false;
        }
        
        return true;
    }
    
    private void resetFormErrors() {
        if (txtEmailLogin != null) {
            txtEmailLogin.setError(false);
        }
        if (txtPassLogin != null) {
            txtPassLogin.setError(false);
        }
        
        if (txtUserRegister != null) {
            txtUserRegister.setError(false);
        }
        if (txtEmailRegister != null) {
            txtEmailRegister.setError(false);
        }
        if (txtPassRegister != null) {
            txtPassRegister.setError(false);
        }
    }
    
    private void showValidationErrors(List<String> errors) {
        StringBuilder errorMsg = new StringBuilder();
        
        if (!errors.isEmpty()) {
            errorMsg.append(errors.get(0));
            
            Notifications.getInstance().show(
                Notifications.Type.ERROR,
                Notifications.Location.TOP_CENTER,
                errorMsg.toString()
            );
        }
    }

    private void saveCredentials(String email, String password) {
        prefs.put("saved_email", email);
        prefs.put("saved_password", password);  // need encryption
        prefs.putBoolean("remember_me", true);
        try {
            prefs.flush();
        } catch (java.util.prefs.BackingStoreException e) {
            System.err.println("Error saving preferences: " + e.getMessage());
        }
    }
    
    private void loadSavedCredentials() {
        if (txtEmailLogin != null && txtPassLogin != null && chkRememberMe != null) {
            String savedEmail = prefs.get("saved_email", "");
            String savedPassword = prefs.get("saved_password", "");
            
            txtEmailLogin.setText(savedEmail);
            txtPassLogin.setText(savedPassword);
            chkRememberMe.setSelected(true);
            
            dataLogin = new Customer();
            dataLogin.setEmail(savedEmail);
            dataLogin.setPassword(savedPassword);
            rememberMe = true;
        }
    }
    
    public void clearSavedCredentials() {
        prefs.remove("saved_email");
        prefs.remove("saved_password");
        prefs.putBoolean("remember_me", false);
        try {
            prefs.flush();
        } catch (java.util.prefs.BackingStoreException e) {
            System.err.println("Error clearing preferences: " + e.getMessage());
        }
    }

    public void clearFormFields() {
        if (txtEmailLogin != null) {
            txtEmailLogin.setText("");
            txtEmailLogin.setError(false);
        }
        if (txtPassLogin != null) {
            txtPassLogin.setText("");
            txtPassLogin.setError(false);
        }
        if (chkRememberMe != null) {
            chkRememberMe.setSelected(false);
        }
        
        if (txtUserRegister != null) {
            txtUserRegister.setText("");
            txtUserRegister.setError(false);
        }
        if (txtEmailRegister != null) {
            txtEmailRegister.setText("");
            txtEmailRegister.setError(false);
        }
        if (txtPassRegister != null) {
            txtPassRegister.setText("");
            txtPassRegister.setError(false);
        }
        
        dataLogin = null;
        user = null;
    }

    public void showRegister(boolean show) {
        resetFormErrors();
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
