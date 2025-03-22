package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.model.Product;
import com.pheobe.model.Brand;
import com.pheobe.model.Category;
import com.pheobe.model.Product_Color;
import com.pheobe.service.Product_Color_DAO;
import com.pheobe.application.manager.BreadcrumbManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductDetailComponent extends JPanel {
    private final Product product;
    private final Brand brand;
    private final Category category;
    
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel brandLabel;
    private JLabel stockLabel;
    private JTextArea descriptionArea;
    private JButton addToCartButton;
    private JButton backButton;
    private JSpinner quantitySpinner;

    public ProductDetailComponent(Product product, Brand brand, Category category) {
        this.product = product;
        this.brand = brand;
        this.category = category;

        init();
        loadImage();
    }

    private void init() {
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headerPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
        headerPanel.setOpaque(false);
        
        JPanel backButtonRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backButtonRow.setOpaque(false);
        
        backButton = new JButton("Back");
        backButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        backButton.addActionListener(e -> {
            BreadcrumbManager.getInstance().navigateBack();
        });
        
        backButtonRow.add(backButton);
        headerPanel.add(backButtonRow);
        
        JPanel breadcrumbRow = new JPanel(new BorderLayout());
        breadcrumbRow.setOpaque(false);
        breadcrumbRow.add(BreadcrumbManager.getInstance().getBreadcrumb(), BorderLayout.CENTER);
        headerPanel.add(breadcrumbRow);
        
        add(headerPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(imageLabel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.weightx = 1.0;
        
        nameLabel = new JLabel(product.getName());
        nameLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h2.font");
        infoPanel.add(nameLabel, gbc);
        
        gbc.gridy++;
        priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold $h3.font");
        infoPanel.add(priceLabel, gbc);
        
        gbc.gridy++;
        brandLabel = new JLabel(brand.getName() + " | " + category.getName());
        brandLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h4.font");
        infoPanel.add(brandLabel, gbc);
        
        gbc.gridy++;
        stockLabel = new JLabel("In Stock: " + product.getStock());
        infoPanel.add(stockLabel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 3, 0);
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold");
        infoPanel.add(descriptionLabel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(getBackground());
        descriptionArea.setRows(5);
        infoPanel.add(descriptionArea, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JLabel quantityLabel = new JLabel("Quantity:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, product.getStock(), 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(60, 25));
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        infoPanel.add(quantityPanel, gbc);
        
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10; background: #4CAF50;");
        addToCartButton.setPreferredSize(new Dimension(120, 30));
        infoPanel.add(addToCartButton, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridy++;
        gbc.weighty = 1.0;
        JPanel fillerPanel = new JPanel();
        fillerPanel.setOpaque(false);
        infoPanel.add(fillerPanel, gbc);

        add(infoPanel, BorderLayout.CENTER);
    }

    private void loadImage() {
        SwingWorker<ImageIcon, Void> worker = new SwingWorker<>() {
            @Override
            protected ImageIcon doInBackground() {
                Product_Color_DAO productColorDAO = new Product_Color_DAO();
                List<Product_Color> productColors = productColorDAO.getProductColorsByProductId(product.getIdProduct());

                if (!productColors.isEmpty()) {
                    byte[] imageData = productColors.get(0).getProductImage();
                    if (imageData != null) {
                        ImageIcon icon = new ImageIcon(imageData);
                        Image fatImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        return new ImageIcon(fatImage);
                    }
                }

                return new ImageIcon(getClass().getResource("/com/pheobe/icon/png/logo.png"));
            }

            @Override
            protected void done() {
                try {
                    ImageIcon imageIcon = get();
                    imageLabel.setIcon(imageIcon);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    public void addAddToCartListener(ActionListener listener) {
        addToCartButton.addActionListener(listener);
    }
    
    public void addBackButtonListener(ActionListener listener) {
        for (ActionListener al : backButton.getActionListeners()) {
            backButton.removeActionListener(al);
        }
        
        backButton.addActionListener(e -> {
            BreadcrumbManager.getInstance().navigateBack();
            
            if (listener != null) {
                listener.actionPerformed(e);
            }
        });
    }
    
    public int getQuantity() {
        return (Integer) quantitySpinner.getValue();
    }

    public Product getProduct() {
        return product;
    }

    public void setupBreadcrumb(String previousPageName, Component previousComponent) {
        BreadcrumbManager breadcrumbManager = BreadcrumbManager.getInstance();
        
        breadcrumbManager.addBreadcrumb(product.getName(), this);
    }
}