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
import com.pheobe.model.Evaluate;
import com.pheobe.service.Evaluate_DAO;
import raven.toast.Notifications;
import com.pheobe.model.Customer;
import com.pheobe.service.Customer_DAO;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionListener;
import static java.time.LocalDateTime.now;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author pheoebe
 */
public class ProductDetailComponent extends javax.swing.JPanel {

    private Product product;
    private Brand brand;
    private Category category;
    private int existingCartQuantity = 0;
    private JButton backButton;
    private JPanel commentsPanel;
    private JTextArea commentTextArea;
    private JComboBox<Integer> ratingComboBox;
    private int currentPage = 0;
    private int commentsPerPage = 5;
    private List<Evaluate> allReviews = new ArrayList<>();
    private JLabel pageInfoLabel;
    private JPanel mainContentPanel;
    private JScrollPane mainScrollPane;

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

        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        mainScrollPane = new JScrollPane(mainContentPanel);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setBorder(null);

        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainScrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        setLayout(new BorderLayout());
        add(mainScrollPane, BorderLayout.CENTER);

        JPanel productInfoPanel = new JPanel();
        
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
        commentsPanel = new javax.swing.JPanel();

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

        JLabel commentsTitle = new JLabel("Reviews");
        commentsTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        commentsTitle.setHorizontalAlignment(JLabel.CENTER);
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(commentsTitle, BorderLayout.CENTER);
        commentsPanel.setLayout(new BorderLayout());
        commentsPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel commentsContentPanel = new JPanel();
        commentsContentPanel.setLayout(new BorderLayout());

        JPanel reviewsContainer = new JPanel();
        reviewsContainer.setLayout(new BoxLayout(reviewsContainer, BoxLayout.Y_AXIS));
        reviewsContainer.setBorder(null);
        reviewsContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JPanel addReviewPanel = new JPanel(new BorderLayout());
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel ratingLabel = new JLabel("Rating:");
        ratingComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratingPanel.add(ratingLabel);
        ratingPanel.add(ratingComboBox);
        
        commentTextArea = new JTextArea();
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.setRows(3);
        JScrollPane commentScrollPane = new JScrollPane(commentTextArea);
        
        JButton submitButton = new JButton("Submit Review");
        submitButton.addActionListener(e -> submitReview());
        
        addReviewPanel.add(ratingPanel, BorderLayout.NORTH);
        addReviewPanel.add(commentScrollPane, BorderLayout.CENTER);
        addReviewPanel.add(submitButton, BorderLayout.SOUTH);

        JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("◀ Previous");
        pageInfoLabel = new JLabel("Page 1");
        JButton nextButton = new JButton("Next ▶");
        
        prevButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                loadCommentsPage();
            }
        });

        nextButton.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) allReviews.size() / commentsPerPage);
            if (currentPage < totalPages - 1) {
                currentPage++;
                loadCommentsPage();
            }
        });

        paginationPanel.add(prevButton);
        paginationPanel.add(pageInfoLabel);
        paginationPanel.add(nextButton);

        addReviewPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        commentsContentPanel.add(addReviewPanel, BorderLayout.NORTH);
        
        JPanel reviewsWithPaginationPanel = new JPanel(new BorderLayout());
        reviewsWithPaginationPanel.add(reviewsContainer, BorderLayout.CENTER);
        reviewsWithPaginationPanel.add(paginationPanel, BorderLayout.SOUTH);
        reviewsWithPaginationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        commentsContentPanel.add(reviewsWithPaginationPanel, BorderLayout.CENTER);
        
        commentsPanel.add(commentsContentPanel, BorderLayout.CENTER);
        
        commentsPanel.setBorder(null);
        
        commentsContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        javax.swing.GroupLayout productInfoLayout = new javax.swing.GroupLayout(productInfoPanel);
        productInfoPanel.setLayout(productInfoLayout);
        productInfoLayout.setHorizontalGroup(
            productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productInfoLayout.createSequentialGroup()
                .addGroup(productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productInfoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productInfoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblPrice)
                            .addComponent(lblCategory)
                            .addComponent(lblStock)
                            .addComponent(lblDescription)
                            .addGroup(productInfoLayout.createSequentialGroup()
                                .addComponent(quantityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addToCart))
                        .addGap(50, 50, 398)))
                .addContainerGap())
        );
        productInfoLayout.setVerticalGroup(
            productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(productInfoLayout.createSequentialGroup()
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
                        .addGroup(productInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityLabel)
                            .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addToCart))
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        // Add components to main content panel
        mainContentPanel.add(productInfoPanel);
        
        // Add a rigid area for spacing
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Add comments panel
        mainContentPanel.add(commentsPanel);
        
        // Add padding at the bottom for better scrolling
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
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
        
        loadComments();
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
            
            JPanel breadcrumbPanel = new JPanel(new BorderLayout());
            breadcrumbPanel.add(BreadcrumbManager.getInstance().getBreadcrumb(), BorderLayout.CENTER);
            topPanel.add(breadcrumbPanel, BorderLayout.CENTER);
        }
    }

    private void loadComments() {
        if (product == null) return;

        try {
            Evaluate_DAO evaluateDAO = new Evaluate_DAO();
            allReviews = evaluateDAO.getEvaluatesByProductId(product.getIdProduct());

            currentPage = 0;
            loadCommentsPage();

            updatePaginationVisibility();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePaginationVisibility() {
        JPanel commentsContentPanel = (JPanel) commentsPanel.getComponent(1);
        JPanel reviewsWithPaginationPanel = (JPanel) commentsContentPanel.getComponent(1);
        JPanel paginationPanel = (JPanel) reviewsWithPaginationPanel.getComponent(1);

        boolean needsPagination = allReviews.size() > commentsPerPage;
        paginationPanel.setVisible(needsPagination);
    }

    private void loadCommentsPage() {
        JPanel commentsContentPanel = (JPanel) commentsPanel.getComponent(1);
        JPanel reviewsWithPaginationPanel = (JPanel) commentsContentPanel.getComponent(1);
        JPanel reviewsContainer = (JPanel) reviewsWithPaginationPanel.getComponent(0);
        reviewsContainer.removeAll();

        if (allReviews.isEmpty()) {
            JLabel noReviewsLabel = new JLabel("No reviews yet. Be the first to review!");
            noReviewsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            reviewsContainer.add(noReviewsLabel);

            int totalPages = 1;
            pageInfoLabel.setText("Page 1 of 1");
        } else {
            int totalPages = (int) Math.ceil((double) allReviews.size() / commentsPerPage);
            pageInfoLabel.setText("Page " + (currentPage + 1) + " of " + totalPages);

            List<Evaluate> pageReviews;
            if (allReviews.size() <= commentsPerPage) {
                pageReviews = allReviews;
            } else {
                int startIndex = currentPage * commentsPerPage;
                int endIndex = Math.min(startIndex + commentsPerPage, allReviews.size());
                pageReviews = allReviews.subList(startIndex, endIndex);
            }

            for (Evaluate review : pageReviews) {
                JPanel reviewPanel = createReviewPanel(review);
                reviewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                reviewsContainer.add(reviewPanel);
                reviewsContainer.add(Box.createVerticalStrut(10));
            }
        }

        reviewsContainer.revalidate();
        reviewsContainer.repaint();
    }

    private JPanel createReviewPanel(Evaluate review) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        ));
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        // Customer info and date first
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // Get username instead of showing customer ID
        String username = getUsernameById(review.getCustomerId());
        JLabel customerLabel = new JLabel(username);
        JLabel dateLabel = new JLabel(" | " + review.getDate().toLocalDate().toString());
        infoPanel.add(customerLabel);
        infoPanel.add(dateLabel);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Rating stars - now below username
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel ratingLabel = new JLabel("Rating: ");
        
        JLabel starsLabel = new JLabel(getStarRating(review.getRating()));
        Font starFont = new Font("Arial", Font.PLAIN, 14);
        starsLabel.setFont(starFont);
        starsLabel.setForeground(new Color(255, 204, 0));
        
        ratingPanel.add(ratingLabel);
        ratingPanel.add(starsLabel);
        ratingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add in the new order: username first, then rating
        topPanel.add(infoPanel);
        topPanel.add(ratingPanel);
        
        JTextArea commentArea = new JTextArea();
        commentArea.setText(review.getComment());
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentArea.setEditable(false);
        commentArea.setBackground(panel.getBackground());
        
        Font largerFont = new Font("Segoe UI", Font.PLAIN, 14);
        commentArea.setFont(largerFont);
        
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.setBackground(panel.getBackground());
        commentPanel.add(commentArea, BorderLayout.CENTER);
        commentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(commentPanel, BorderLayout.CENTER);
        
        return panel;
    }

    private String getStarRating(Integer rating) {
        if (rating == null) return "N/A";
        
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < rating; i++) {
            stars.append("★");
        }
        for (int i = rating; i < 5; i++) {
            stars.append("☆");
        }
        return stars.toString();
    }

    private void submitReview() {
        if (product == null) return;

        try {
            if (Application.getCurrentUser() == null) {
                Application.showMessage(Notifications.Type.WARNING, "Please login to add a review");
                return;
            }

            String comment = commentTextArea.getText().trim();
            Integer rating = (Integer) ratingComboBox.getSelectedItem();

            if (comment.isEmpty()) {
                Application.showMessage(Notifications.Type.WARNING, "Please enter a comment");
                return;
            }

            Evaluate review = new Evaluate();
            review.setProductId(product.getIdProduct());
            review.setCustomerId(getCurrentUserID());
            review.setComment(comment);
            review.setRating(rating);
            review.setDate(now());
            
            Evaluate_DAO evaluateDAO = new Evaluate_DAO();
            boolean success = evaluateDAO.insertEvaluate(review);

            if (success) {
                Application.showMessage(Notifications.Type.SUCCESS, "Review submitted successfully");
                commentTextArea.setText("");
                loadComments();

                if (allReviews.size() > commentsPerPage) {
                    currentPage = (int) Math.ceil((double) allReviews.size() / commentsPerPage) - 1;
                    if (currentPage < 0) currentPage = 0;
                    loadCommentsPage();
                }

                updatePaginationVisibility();
            } else {
                Application.showMessage(Notifications.Type.ERROR, "Failed to submit review");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, "Error submitting review: " + e.getMessage());
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        loadComments();
    }

    // Helper method to get username by customer ID
    private String getUsernameById(int customerId) {
        try {
            Customer_DAO customerDAO = new Customer_DAO();
            Customer customer = customerDAO.selectById(customerId);
            if (customer != null && customer.getUserName() != null) {
                return customer.getUserName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User #" + customerId; // Fallback if we can't get the username
    }
}
