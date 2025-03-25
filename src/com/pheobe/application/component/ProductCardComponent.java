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
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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
        int radius = 15;
        setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(Color.LIGHT_GRAY, 1, radius),
            new EmptyBorder(10, 10, 10, 10)
        ));
        setPreferredSize(new Dimension(220, 320));
        putClientProperty(FlatClientProperties.STYLE, "arc: 10");

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setOpaque(false);
        
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        infoPanel.setOpaque(false);

        nameLabel = new JLabel(product.getName());
        nameLabel.putClientProperty(FlatClientProperties.STYLE, "font: $h3.font");
        nameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        nameLabel.setOpaque(false);

        priceLabel = new JLabel("$" + product.getPrice());
        priceLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold $h4.font");
        priceLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        priceLabel.setOpaque(false);

        brandLabel = new JLabel(brand.getName() + " | " + category.getName());
        brandLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        brandLabel.setOpaque(false);

        boolean isOutOfStock = product.getStock() <= 0;
        
        if (isOutOfStock) {
            stockLabel = new JLabel("OUT OF STOCK");
            stockLabel.setForeground(Color.RED);
            stockLabel.setFont(stockLabel.getFont().deriveFont(Font.BOLD));
        } else {
            stockLabel = new JLabel("In Stock: " + product.getStock());
        }
        stockLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        stockLabel.setOpaque(false);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(brandLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(stockLabel);
        
        if (!isOutOfStock) {
            infoPanel.add(Box.createVerticalStrut(10));
            addToCart = new JButton("Add to cart");
            addToCart.putClientProperty(FlatClientProperties.STYLE, "arc: 10");
            addToCart.setAlignmentX(JButton.LEFT_ALIGNMENT);
            infoPanel.add(addToCart);
        }

        add(imagePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        
        setOpaque(false);
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
                        return createRoundedImage(new ImageIcon(fatImage), 15);
                    }
                }

                ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/com/pheobe/icon/png/logo.png"));
                return createRoundedImage(defaultIcon, 15);
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
        
        final Color DEFAULT_BG = UIManager.getColor("Panel.background");
        final Color HOVER_BG = new Color(240, 240, 250);
        final int ANIMATION_DURATION = 150;
        final Timer[] animationTimer = new Timer[1];
        
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
                if (animationTimer[0] != null && animationTimer[0].isRunning()) {
                    animationTimer[0].stop();
                }
                
                Color currentBg = getBackground();
                
                animationTimer[0] = new Timer(10, null);
                final int steps = ANIMATION_DURATION / animationTimer[0].getDelay();
                final int[] currentStep = {0};
                
                animationTimer[0].addActionListener(evt -> {
                    currentStep[0]++;
                    
                    if (currentStep[0] >= steps) {
                        setBackground(HOVER_BG);
                        animationTimer[0].stop();
                    } else {
                        float ratio = (float) currentStep[0] / steps;
                        int r = (int) (currentBg.getRed() + ratio * (HOVER_BG.getRed() - currentBg.getRed()));
                        int g = (int) (currentBg.getGreen() + ratio * (HOVER_BG.getGreen() - currentBg.getGreen()));
                        int b = (int) (currentBg.getBlue() + ratio * (HOVER_BG.getBlue() - currentBg.getBlue()));
                        
                        setBackground(new Color(r, g, b));
                    }
                });
                
                animationTimer[0].start();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (animationTimer[0] != null && animationTimer[0].isRunning()) {
                    animationTimer[0].stop();
                }
                
                Color currentBg = getBackground();
                
                animationTimer[0] = new Timer(10, null);
                final int steps = ANIMATION_DURATION / animationTimer[0].getDelay();
                final int[] currentStep = {0};
                
                animationTimer[0].addActionListener(evt -> {
                    currentStep[0]++;
                    
                    if (currentStep[0] >= steps) {
                        setBackground(DEFAULT_BG);
                        animationTimer[0].stop();
                    } else {
                        float ratio = (float) currentStep[0] / steps;
                        int r = (int) (currentBg.getRed() + ratio * (DEFAULT_BG.getRed() - currentBg.getRed()));
                        int g = (int) (currentBg.getGreen() + ratio * (DEFAULT_BG.getGreen() - currentBg.getGreen()));
                        int b = (int) (currentBg.getBlue() + ratio * (DEFAULT_BG.getBlue() - currentBg.getBlue()));
                        
                        setBackground(new Color(r, g, b));
                    }
                });
                
                animationTimer[0].start();
            }
        });
    }

    public void addAddToCartListener(ActionListener listener) {
        if (addToCart != null) {
            addToCart.addActionListener(listener);
        }
    }

    public void addViewDetailsListener(ActionListener listener) {
        this.viewDetailsListener = listener;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int radius = 15;
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.dispose();
    }

    private ImageIcon createRoundedImage(ImageIcon icon, int cornerRadius) {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, width, height);
        
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
        g2.setClip(roundedRect);
        g2.drawImage(icon.getImage(), 0, 0, null);
        
        g2.setClip(null);
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(1));
        g2.draw(roundedRect);
        
        g2.dispose();
        
        return new ImageIcon(output);
    }

    private static class RoundedBorder extends AbstractBorder {
        private final Color color;
        private final int thickness;
        private final int radius;

        public RoundedBorder(Color color, int thickness, int radius) {
            this.color = color;
            this.thickness = thickness;
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}