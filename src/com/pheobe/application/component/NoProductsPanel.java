package com.pheobe.application.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class NoProductsPanel extends JPanel {
    private String message;
    private Image backgroundImage;
    
    public NoProductsPanel(String message) {
        this.message = message;
        setOpaque(false);
        setPreferredSize(new Dimension(600, 400));
        
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/com/pheobe/icon/png/bgfiller.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        if (backgroundImage != null) {
            int imgWidth = (int)(backgroundImage.getWidth(this) * 0.7);
            int imgHeight = (int)(backgroundImage.getHeight(this) * 0.7);
            
            int x = (getWidth() - imgWidth) / 2;
            int y = (getHeight() - imgHeight) / 2 - 50;
            
            g2d.drawImage(backgroundImage, x, y, imgWidth, imgHeight, this);
        }
        
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        g2d.setColor(new Color(0, 0, 0));
        
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(message);
        int textX = (getWidth() - textWidth) / 2;
        
        int textY = getHeight() / 2 + 100;
        
        g2d.drawString(message, textX, textY);
        
        g2d.dispose();
    }
}