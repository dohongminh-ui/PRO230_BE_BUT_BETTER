package com.pheobe.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.Timer;

public class MyPasswordField extends JPasswordField {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }
    
    public boolean isError() {
        return errorState > 0;
    }
    
    public void setError(boolean error) {
        if (error) {
            startErrorAnimation();
        } else {
            clearError();
        }
    }

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    private int errorState = 0;
    private Color defaultBackground = new Color(230, 245, 241);
    private Color errorBackground = new Color(255, 213, 213);
    private Timer errorFadeInTimer;
    private Timer errorFadeOutTimer;
    private static final int FADE_STEPS = 10;
    private static final int FADE_DELAY = 30;

    public MyPasswordField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
        
        initAnimationTimers();
        
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (errorState > 0) {
                    startErrorFadeOut();
                }
            }
        });
    }
    
    private void initAnimationTimers() {
        errorFadeInTimer = new Timer(FADE_DELAY, e -> {
            if (errorState < FADE_STEPS) {
                errorState++;
                repaint();
            } else {
                errorFadeInTimer.stop();
            }
        });
        
        errorFadeOutTimer = new Timer(FADE_DELAY, e -> {
            if (errorState > 0) {
                errorState--;
                repaint();
            } else {
                errorFadeOutTimer.stop();
            }
        });
    }
    
    private void startErrorAnimation() {
        errorFadeOutTimer.stop();
        errorFadeInTimer.stop();
        
        startErrorFadeIn();
    }
    
    private void startErrorFadeIn() {
        errorFadeInTimer.start();
    }
    
    private void startErrorFadeOut() {
        errorFadeInTimer.stop();
        errorFadeOutTimer.start();
    }
    
    private void clearError() {
        errorFadeInTimer.stop();
        errorState = 0;
        repaint();
    }
    
    private Color getAnimatedErrorColor(Color baseColor, Color errorColor) {
        if (errorState == 0) {
            return baseColor;
        }
        
        float ratio = (float) errorState / FADE_STEPS;
        
        int r = (int) (baseColor.getRed() + ratio * (errorColor.getRed() - baseColor.getRed()));
        int g = (int) (baseColor.getGreen() + ratio * (errorColor.getGreen() - baseColor.getGreen()));
        int b = (int) (baseColor.getBlue() + ratio * (errorColor.getBlue() - baseColor.getBlue()));
        
        return new Color(r, g, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color bgColor = getAnimatedErrorColor(defaultBackground, errorBackground);
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        
        if (errorState > 0) {
            float alpha = (float) errorState / FADE_STEPS;
            Color borderColor = new Color(255, 80, 80, (int)(alpha * 255));
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);
        }
        
        paintIcon(g);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            //  suffix is right
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}
