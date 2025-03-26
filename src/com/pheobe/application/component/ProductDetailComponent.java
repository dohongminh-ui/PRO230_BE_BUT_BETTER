package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.model.Product;
import com.pheobe.model.Brand;
import com.pheobe.model.Category;
import com.pheobe.model.Cart;
import com.pheobe.model.Cart_detail;
import com.pheobe.service.Cart_DAO;
import com.pheobe.service.Cart_Detail_DAO;
import com.pheobe.service.Product_Color_DAO;
import com.pheobe.model.Product_Color;
import com.pheobe.application.manager.BreadcrumbManager;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import static java.time.LocalDateTime.now;
import java.util.List;

/**
 *
 * @author pheoebe
 */
public class ProductDetailComponent extends javax.swing.JPanel {

    private Product product;
    private Brand brand;
    private Category category;
    private int existingCartQuantity = 0;
    private JPanel headerPanel;
    private JButton backButton;

    public ProductDetailComponent(String text) {
        initComponents();
//        lb.putClientProperty(FlatClientProperties.STYLE, ""
//                + "font:$h1.font");
//        lb.setText(text);
    }

    public ProductDetailComponent(Product product, Brand brand, Category category) {
        this.product = product;
        this.brand = brand;
        this.category = category;
        
        initComponents();
        initBreadcrumb();
        checkExistingCartQuantity();
        loadProductData();
        loadImage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        addToCart = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();

        imagePanel.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblName.setText("Name");

        lblPrice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrice.setText("$69");

        lblCategory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCategory.setText("Intel | CPU");

        lblStock.setText("In Stock: 0");

        lblDescription.setText("Description");

        quantityLabel.setText("Quantity:");

        addToCart.setBackground(new java.awt.Color(128, 239, 128));
        addToCart.setForeground(new java.awt.Color(0, 0, 0));
        addToCart.setText("Add To Cart");
        addToCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartActionPerformed(evt);
            }
        });

        topPanel.setPreferredSize(new java.awt.Dimension(0, 101));
        topPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(lblPrice)
                    .addComponent(lblCategory)
                    .addComponent(lblStock)
                    .addComponent(lblDescription)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quantityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addToCart))
                .addContainerGap(410, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPrice)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStock)
                        .addGap(18, 18, 18)
                        .addComponent(lblDescription)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityLabel)
                            .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToCart))
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartActionPerformed
        if (product == null) return;
        
        try {
            if (Application.getCurrentUser() == null) {
                Application.showMessage(Notifications.Type.WARNING, "Please login to add items to cart");
                return;
            }

            int quantity = (Integer) quantitySpinner.getValue();
            int totalQuantity = quantity + existingCartQuantity;

            if (totalQuantity > product.getStock()) {
                Application.showMessage(Notifications.Type.WARNING, 
                    "Total quantity (" + totalQuantity + ") exceeds available stock (" + product.getStock() + ")");
                return;
            }

            int cartID = getCurrentCartID();
            if (cartID == -1) {
                Application.showMessage(Notifications.Type.ERROR, "Failed to get cart information");
                return;
            }

            Cart_Detail_DAO cartDetailDAO = new Cart_Detail_DAO();
            List<Cart_detail> cartDetails = cartDetailDAO.selectAll();

            boolean productFound = false;
            for (Cart_detail detail : cartDetails) {
                if (detail.getCartID() == cartID && 
                    detail.getProductId() == product.getIdProduct() && 
                    "Active".equals(detail.getStatus())) {

                    detail.setQuantity(detail.getQuantity() + quantity);
                    boolean updated = cartDetailDAO.update(detail);
                    if (updated) {
                        Application.showMessage(Notifications.Type.SUCCESS, 
                            quantity + " \"" + product.getName() + "\" added to cart");
                        refreshCartForm();
                        
                        existingCartQuantity += quantity;
                        refreshProductView();
                    } else {
                        Application.showMessage(Notifications.Type.ERROR, 
                            "Failed to update \"" + product.getName() + "\" in cart");
                    }
                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                Cart_detail cartItem = new Cart_detail();
                cartItem.setCartID(cartID); 
                cartItem.setProductId(product.getIdProduct());
                cartItem.setPrice(product.getPrice());
                cartItem.setQuantity(quantity);
                cartItem.setStatus("Active");
                cartItem.setCreateDate(java.time.LocalDateTime.now());

                boolean success = cartDetailDAO.insert(cartItem);
                if (success) {
                    Application.showMessage(Notifications.Type.SUCCESS, 
                        quantity + " \"" + product.getName() + "\" added to cart");
                    refreshCartForm();
                    
                    existingCartQuantity += quantity;
                    refreshProductView();
                } else {
                    Application.showMessage(Notifications.Type.ERROR, 
                        "Failed to add \"" + product.getName() + "\" to cart");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, 
                "Error adding product to cart: " + e.getMessage());
        }
    }//GEN-LAST:event_addToCartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCart;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables

    private void loadProductData() {
        if (product == null) return;
        lblName.setText(product.getName());
        lblPrice.setText("$" + product.getPrice());
        lblCategory.setText(brand.getName() + " | " + category.getName());
        lblStock.setText("In Stock: " + product.getStock());
        lblDescription.setText(product.getDescription());
        
        boolean isOutOfStock = product.getStock() <= 0;
        
        if (isOutOfStock) {
            lblStock.setText("OUT OF STOCK");
            lblStock.setForeground(Color.RED);
            lblStock.setFont(lblStock.getFont().deriveFont(Font.BOLD));
            
            quantitySpinner.setVisible(false);
            addToCart.setVisible(false);
            quantityLabel.setForeground(Color.RED);
            quantityLabel.setText("OUT OF STOCK");
        } else {
            int availableStock = product.getStock() - existingCartQuantity;
            
            if (availableStock > 0) {
                SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, availableStock, 1);
                quantitySpinner.setModel(spinnerModel);
                
                quantitySpinner.setVisible(true);
                addToCart.setVisible(true);
                quantityLabel.setForeground(UIManager.getColor("Label.foreground"));
                
                if (existingCartQuantity > 0) {
                    quantityLabel.setText("Quantity: (Already in cart: " + existingCartQuantity + ")");
                } else {
                    quantityLabel.setText("Quantity:");
                }
            } else {
                quantitySpinner.setVisible(false);
                addToCart.setVisible(false);
                
                quantityLabel.setForeground(Color.RED);
                
                if (existingCartQuantity > 0) {
                    quantityLabel.setText("Maximum quantity already in cart: " + existingCartQuantity);
                } else {
                    quantityLabel.setText("No stock available");
                }
            }
        }
    }
    
    private void loadImage() {
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<>() {
            @Override
            protected ImageIcon doInBackground() {
                try {
                    Product_Color_DAO productColorDAO = new Product_Color_DAO();
                    List<Product_Color> productColors = productColorDAO.getProductColorsByProductId(product.getIdProduct());

                    if (!productColors.isEmpty()) {
                        byte[] imageData = productColors.get(0).getProductImage();
                        if (imageData != null) {
                            ImageIcon icon = new ImageIcon(imageData);
                            Image scaledImage = icon.getImage().getScaledInstance(imagePanel.getWidth(), 
                                    imagePanel.getHeight(), Image.SCALE_SMOOTH);
                            return new ImageIcon(scaledImage);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return new ImageIcon(getClass().getResource("/com/pheobe/icon/png/logo.png"));
            }

            @Override
            protected void done() {
                try {
                    ImageIcon imageIcon = get();
                    JLabel imageLabel = new JLabel(imageIcon);
                    imageLabel.setHorizontalAlignment(JLabel.CENTER);
                    imagePanel.setLayout(new BorderLayout());
                    imagePanel.add(imageLabel, BorderLayout.CENTER);
                    imagePanel.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void checkExistingCartQuantity() {
        try {
            if (Application.getCurrentUser() == null || product == null) {
                return;
            }
            
            int cartId = getCurrentCartID();
            if (cartId == -1) {
                return;
            }
            
            Cart_Detail_DAO cartDetailDAO = new Cart_Detail_DAO();
            List<Cart_detail> cartDetails = cartDetailDAO.selectAll();
            
            for (Cart_detail detail : cartDetails) {
                if (detail.getCartID() == cartId && 
                    detail.getProductId() == product.getIdProduct() && 
                    "Active".equals(detail.getStatus())) {
                    
                    existingCartQuantity = detail.getQuantity();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int getCurrentCartID() {
        try {
            int userId = getCurrentUserID();
            Cart_DAO cartDao = new Cart_DAO();
            List<Cart> carts = cartDao.selectAll();

            for (Cart c : carts) {
                if (c.getCustomerId() == userId && "Active".equalsIgnoreCase(c.getStatus())) {
                    return c.getId();
                }
            }

            Cart newCart = new Cart();
            newCart.setCustomerId(userId);
            newCart.setCartID("CART-" + userId + "-" + System.currentTimeMillis());
            newCart.setStatus("Active");
            newCart.setCreateDate(now());

            boolean created = cartDao.insert(newCart);
            if (!created) {
                return -1;
            }

            carts = cartDao.selectAll();
            for (Cart c : carts) {
                if (c.getCustomerId() == userId && "Active".equalsIgnoreCase(c.getStatus())) {
                    return c.getId();
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    private int getCurrentUserID() {
        return Application.getCurrentUser().getIdCustomer();
    }

    private void refreshCartForm() {
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            if (window instanceof javax.swing.JFrame) {
                javax.swing.JFrame frame = (javax.swing.JFrame) window;
                for (java.awt.Component comp : frame.getContentPane().getComponents()) {
                    if (comp instanceof com.pheobe.application.form.other.FormCart1) {
                        com.pheobe.application.form.other.FormCart1 cartForm = 
                            (com.pheobe.application.form.other.FormCart1) comp;
                        cartForm.refreshCart();
                        break;
                    }
                }
            }
        }
    }
    
    private void refreshProductView() {
        checkExistingCartQuantity();
        loadProductData();
        this.revalidate();
        this.repaint();
    }

    public void setupBreadcrumb(String previousPageName, Component previousComponent) {
        if (product == null) return;
        
        BreadcrumbManager breadcrumbManager = BreadcrumbManager.getInstance();
        breadcrumbManager.addBreadcrumb(product.getName(), this);
    }
    
    public void addBackButtonListener(ActionListener listener) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            BreadcrumbManager.getInstance().navigateBack();
            if (listener != null) {
                listener.actionPerformed(e);
            }
        });
    }
    
    public Product getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return (Integer) quantitySpinner.getValue();
    }

    private void initBreadcrumb() {
        // Assuming topPanel is the JPanel you added in the designer
        if (topPanel != null) {
            System.out.println("sigma");
            backButton = new JButton("Back");
            backButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
            backButton.addActionListener(e -> {
                BreadcrumbManager.getInstance().navigateBack();
            });
            
            JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            backButtonPanel.add(backButton);
            topPanel.add(backButtonPanel, BorderLayout.NORTH);
            
            // Add breadcrumb
            JPanel breadcrumbPanel = new JPanel(new BorderLayout());
            breadcrumbPanel.add(BreadcrumbManager.getInstance().getBreadcrumb(), BorderLayout.CENTER);
            topPanel.add(breadcrumbPanel, BorderLayout.CENTER);
        }
    }
}
