package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.model.Product;
import com.pheobe.model.Brand;
import com.pheobe.model.Category;
import com.pheobe.model.Product_Color;
import com.pheobe.service.Brand_DAO;
import com.pheobe.service.Category_DAO;
import com.pheobe.service.Product_Color_DAO;

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
        setBorder(new EmptyBorder(10, 10, 10, 10));

        backButton = new JButton("← Back");
        backButton.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        add(backButton, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(400, 400));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(imageLabel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        nameLabel = new JLabel(product.getName());
        nameLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h3.font");
        nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold $h4.font");
        priceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        brandLabel = new JLabel(brand.getName() + " | " + category.getName());
        brandLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        stockLabel = new JLabel("In Stock: " + product.getStock());
        stockLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

        // addToCart = new JButton("Add to cart");
        // addToCart.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        // addToCart.setAlignmentX(JButton.LEFT_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(brandLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        // infoPanel.add(addToCart);

        add(imageLabel, BorderLayout.NORTH);
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
        // addToCart.addActionListener(listener);
    }

    public Product getProduct() {
        return product;
    }
}