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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProductCardComponent extends JPanel {
    private final Product product;
    private final Brand brand;
    private final Category category;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel brandLabel;
    private JLabel stockLabel;
    private JButton addToCart;
    private ActionListener viewDetailsListener;

    public ProductCardComponent(Product product, Brand brand, Category category) {
        this.product = product;
        this.brand = brand;
        this.category = category;

        init();
        loadImage();
        setupClickableCard();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        setPreferredSize(new Dimension(220, 320));
        putClientProperty(FlatClientProperties.STYLE, "arc: 10");

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

        addToCart = new JButton("Add to cart");
        addToCart.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
        addToCart.setAlignmentX(JButton.LEFT_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(brandLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(addToCart);

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

    private void setupClickableCard() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (viewDetailsListener != null) {
                    viewDetailsListener.actionPerformed(
                        new java.awt.event.ActionEvent(this, ActionEvent.ACTION_PERFORMED, "cardClicked")
                    );
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(245, 245, 245));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(UIManager.getColor("Panel.background"));
            }
        });
    }

    public void addAddToCartListener(ActionListener listener) {
        addToCart.addActionListener(listener);
    }

    public void addViewDetailsListener(ActionListener listener) {
        this.viewDetailsListener = listener;
    }

    public Product getProduct() {
        return product;
    }
}