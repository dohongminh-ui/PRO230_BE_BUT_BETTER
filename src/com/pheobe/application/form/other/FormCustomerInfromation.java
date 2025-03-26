package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.application.menu.Menu;
import com.pheobe.model.Customer;
import com.pheobe.service.Customer_DAO;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.UUID;


/**
 *
 * @author pheobeo
 */
public class FormCustomerInfromation extends javax.swing.JPanel {
    private Customer_DAO serviceCustomer = new Customer_DAO();
    private String selectedImagePath = null;
    private boolean imageChanged = false;

    public FormCustomerInfromation() {
        initComponents();
        panelInformation.setAlignmentY(CENTER_ALIGNMENT);
        javax.swing.border.EmptyBorder margin = new javax.swing.border.EmptyBorder(20, 10, 10, 10);
        panelInformation.setBorder(margin);
        lblUsername.putClientProperty(FlatClientProperties.STYLE, "" + "font:$h1.font");
        setupProfileImagePanel();
        loadCustomerData();
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelInformation = new javax.swing.JPanel();
        lblUserEmail = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblName = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblNumber = new javax.swing.JLabel();
        txtNumber = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblLocation = new javax.swing.JLabel();
        txtLocation = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panelImage = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        lblUserEmail.setText("yourname@gmail.com");

        lblUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsername.setText("your name");

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblName.setText("Name");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEmail.setText("Email account");

        lblNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNumber.setText("Phone number");

        lblLocation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblLocation.setText("Address");

        jButton1.setBackground(new java.awt.Color(255, 179, 72));
        jButton1.setText("Save Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelInformationLayout = new javax.swing.GroupLayout(panelInformation);
        panelInformation.setLayout(panelInformationLayout);
        panelInformationLayout.setHorizontalGroup(
            panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(panelInformationLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addComponent(lblUserEmail))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformationLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformationLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4)
                .addContainerGap())
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformationLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInformationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        panelInformationLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEmail, txtLocation, txtNumber, txtname});

        panelInformationLayout.setVerticalGroup(
            panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInformationLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInformationLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUserEmail))
                    .addGroup(panelInformationLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumber)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocation)
                    .addComponent(txtLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        panelInformationLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEmail, txtLocation, txtNumber, txtname});

        add(panelInformation, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void setupProfileImagePanel() {
        panelImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseProfilePicture();
            }
        });
        
        profileImageLabel = new javax.swing.JLabel();
        profileImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
    }
    
    private void chooseProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Picture");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath();
            imageChanged = true;
            
            displayProfileImage(selectedImagePath);
        }
    }
    
    private void displayProfileImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            profileImageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                    "Error loading image: " + e.getMessage(), 
                    "Image Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayProfileImageFromResource(String imageName) {
        if (imageName == null || imageName.isEmpty()) {
            imageName = "0.png";
        }
        
        try {
            String imagePath = "/com/pheobe/icon/pfp/" + imageName;
            java.net.URL imageUrl = getClass().getResource(imagePath);
            
            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                profileImageLabel.setIcon(new ImageIcon(img));
            } else {
                System.err.println("Could not find image: " + imagePath);
                profileImageLabel.setText("No Image");
            }
        } catch (Exception e) {
            e.printStackTrace();
            profileImageLabel.setText("Error");
        }
    }
    
    private String saveProfileImage() {
        if (!imageChanged || selectedImagePath == null) {
            Customer customer = Application.getCurrentUser();
            return customer.getImg();
        }
        
        try {
            Customer customer = Application.getCurrentUser();
            String oldImageName = customer.getImg();
            
            String fileExtension = selectedImagePath.substring(selectedImagePath.lastIndexOf('.'));
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            
            Path source = Paths.get(selectedImagePath);
            Path targetDir = Paths.get(System.getProperty("user.dir"), "src", "com", "pheobe", "icon", "pfp");
            
            Files.createDirectories(targetDir);
            
            if (oldImageName != null && !oldImageName.isEmpty() && !oldImageName.equals("0.png")) {
                try {
                    Path oldImagePath = targetDir.resolve(oldImageName);
                    if (Files.exists(oldImagePath) && !oldImagePath.getFileName().toString().equals("0.png")) {
                        System.out.println("Deleting old profile picture: " + oldImagePath);
                        Files.delete(oldImagePath);
                    } else {
                        System.out.println("Old image doesn't exist or is the default image, skipping deletion: " + oldImagePath);
                    }
                } catch (IOException e) {
                    System.err.println("Could not delete old profile picture: " + e.getMessage());
                }
            }
            
            Path target = targetDir.resolve(newFileName);
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Successfully saved new profile picture: " + newFileName);
            
            return newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                    "Error saving image: " + e.getMessage(), 
                    "Save Error", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private void loadCustomerData() {
        Customer customer = serviceCustomer.selectById(Application.getCurrentUser().getIdCustomer());
        txtname.setText(customer.getName());
        txtEmail.setText(customer.getEmail());
        txtNumber.setText(customer.getPhoneNumber());
        txtLocation.setText(customer.getAddress());
        
        lblUsername.setText(customer.getName());
        lblUserEmail.setText(customer.getEmail());
        
        if (customer.getImg() != null && !customer.getImg().isEmpty()) {
            debugCheckFileExists(customer.getImg());
        }
        
        displayProfileImageFromResource(customer.getImg());

        System.err.println(customer.getName());
        System.err.println(customer.getEmail());
        System.err.println(customer.getPhoneNumber());
        System.err.println(customer.getAddress());
        System.err.println(customer.getIdCustomer());
        System.err.println(customer.getImg());
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to update your information?", 
                "Confirm Update", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        if(choice == JOptionPane.YES_OPTION) {
            try {
                Customer customer = Application.getCurrentUser();
                String oldImageName = customer.getImg();
                System.out.println("ProfileUpdate: Current profile picture: " + oldImageName);
                
                if (oldImageName != null && !oldImageName.isEmpty()) {
                    debugCheckFileExists(oldImageName);
                }
                
                customer.setName(txtname.getText());
                customer.setEmail(txtEmail.getText());
                customer.setPhoneNumber(txtNumber.getText());
                customer.setAddress(txtLocation.getText());
                
                String imageName = saveProfileImage();
                System.out.println("ProfileUpdate: New profile picture: " + imageName);
                
                if (imageName != null && !imageName.isEmpty()) {
                    debugCheckFileExists(imageName);
                }
                
                customer.setImg(imageName);
                
                if (customer.getName().isEmpty() || customer.getEmail().isEmpty()) {
                    JOptionPane.showMessageDialog(this, 
                            "Name and Email cannot be empty", 
                            "Validation Error", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                boolean success = serviceCustomer.update(customer);
                
                if (success) {
                    JOptionPane.showMessageDialog(this, 
                            "Your information has been updated successfully", 
                            "Update Successful", 
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    lblUsername.setText(customer.getName());
                    lblUserEmail.setText(customer.getEmail());
                    
                    imageChanged = false;
                    
                    System.out.println("ProfileUpdate: Refreshing current user...");
                    Application.refreshCurrentUser();
                    
                    try {
                        Menu menu = Application.getMainForm().getMenu();
                        if (menu != null) {
                            if (customer.getImg() != null && !customer.getImg().isEmpty()) {
                                System.out.println("ProfileUpdate: Updating menu profile picture to: " + customer.getImg());
                                menu.setUserProfileIconFromFile(customer.getImg());
                            } else {
                                System.out.println("ProfileUpdate: No profile image to update in menu");
                            }
                        } else {
                            System.err.println("ProfileUpdate: Menu not available for profile picture update");
                        }
                    } catch (Exception e) {
                        System.err.println("ProfileUpdate: Error updating menu profile picture: " + e.getMessage());
                        e.printStackTrace();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Failed to update your information", 
                            "Update Failed", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                        "Error: " + e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean debugCheckFileExists(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("DEBUG: Attempted to check null or empty filename");
            return false;
        }
        
        try {
            Path targetDir = Paths.get(System.getProperty("user.dir"), "src", "com", "pheobe", "icon", "pfp");
            Path filePath = targetDir.resolve(fileName);
            boolean exists = Files.exists(filePath);
            System.err.println("DEBUG: File " + fileName + " exists in pfp directory: " + exists);
            return exists;
        } catch (Exception e) {
            System.err.println("DEBUG: Error checking if file exists: " + e.getMessage());
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblLocation;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNumber;
    private javax.swing.JLabel lblUserEmail;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel panelImage;
    private javax.swing.JPanel panelInformation;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLocation;
    private javax.swing.JTextField txtNumber;
    private javax.swing.JTextField txtname;
    private javax.swing.JLabel profileImageLabel;
    // End of variables declaration//GEN-END:variables

    public void refreshForm() {
        loadCustomerData();
    }
}
