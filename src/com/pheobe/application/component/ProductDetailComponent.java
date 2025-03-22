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

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(5, 0, 10, 0));

        backButton = new JButton("Back");
        backButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        backButton.addActionListener(e -> {
            BreadcrumbManager.getInstance().navigateBack();
        });
        headerPanel.add(backButton, BorderLayout.EAST);
        headerPanel.add(BreadcrumbManager.getInstance().getBreadcrumb(), BorderLayout.NORTH);
        add(headerPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(300, 300));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(imageLabel, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(10, 20, 10, 10));

        nameLabel = new JLabel(product.getName());
        nameLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h2.font");
        nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold $h3.font");
        priceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        brandLabel = new JLabel(brand.getName() + " | " + category.getName());
        brandLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h4.font");
        brandLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        stockLabel = new JLabel("In Stock: " + product.getStock());
        stockLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold");
        descriptionLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(getBackground());
        descriptionArea.setRows(5);
        descriptionArea.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        quantityPanel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, product.getStock(), 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(60, 25));
        
        quantityPanel.add(quantityLabel);
        quantityPanel.add(quantitySpinner);
        
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10; background: #4CAF50;");
        addToCartButton.setAlignmentX(JButton.LEFT_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(brandLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(descriptionLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descriptionArea);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(quantityPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(addToCartButton);

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
        breadcrumbManager.clear();
        breadcrumbManager.addBreadcrumb(product.getName(), this);
    }
}