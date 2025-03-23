package com.pheobe.application.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import com.pheobe.application.menu.mode.ToolBarAccentColor;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pheoebe
 */
public class Menu extends JPanel {

    private final String menuItems[][] = {
        {"Home"},
        {"Products", "VGA", "CPU", "Mainboard"},
        {"Cart"},
        {"Shopping History"},
        {"~OTHER~"},
        {"User", "Personal Information", "Logout"}
    };

    private static final int USER_MENU_INDEX = 4;

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        if (this.menuFull == menuFull || isAnimating) {
            return;
        }
        
        this.menuFull = menuFull;
        
        if (menuFull) {
            header.setText(headerName);
            header.setHorizontalAlignment(JLabel.CENTER);
            header.setHorizontalTextPosition(JLabel.RIGHT);
            header.setIconTextGap(10);
        } else {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        
        toolBarAccentColor.setMenuFull(menuFull);
        
        startAnimation();
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerName = "Beelectronic";

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    private JLabel gigi;
    private final int gigiSize = 250;

    private boolean isAnimating = false;
    private int currentWidth;
    private Timer animationTimer;
    private final int ANIMATION_DURATION = 200;
    private final int ANIMATION_STEPS = 20;

    public Menu() {
        init();
        currentWidth = menuMaxWidth;
    }

    private void init() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:20,2,2,2;"
                + "background:$Menu.background;"
                + "arc:10");
        header = new JLabel(headerName);
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/pheobe/icon/png/logo.png"));
        int size = UIScale.scale(32);
        
        BufferedImage circleBuffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new Ellipse2D.Double(0, 0, size, size));
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(originalIcon.getImage(), 0, 0, size, size, null);
        g2.dispose();
        
        header.setIcon(new ImageIcon(circleBuffer));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setHorizontalTextPosition(JLabel.RIGHT);
        header.setIconTextGap(10);
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.header.font;"
                + "foreground:$Menu.foreground");

        scroll = new JScrollPane();
        panelMenu = new JPanel(new MenuItemLayout(this));
        panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Menu.background");

        scroll.setViewportView(panelMenu);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:null");
        JScrollBar vscroll = scroll.getVerticalScrollBar();
        vscroll.setUnitIncrement(10);
        vscroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "width:$Menu.scroll.width;"
                + "trackInsets:$Menu.scroll.trackInsets;"
                + "thumbInsets:$Menu.scroll.thumbInsets;"
                + "background:$Menu.ScrollBar.background;"
                + "thumb:$Menu.ScrollBar.thumb");

        ImageIcon gigiMurin = new ImageIcon(getClass().getResource("/com/pheobe/icon/png/gigi.png"));
        Image fatGigiMurin = gigiMurin.getImage().getScaledInstance(gigiSize, gigiSize, Image.SCALE_SMOOTH);
        gigi = new JLabel(new ImageIcon(fatGigiMurin));
        gigi.setHorizontalAlignment(JLabel.RIGHT);
        add(gigi);

        createMenu();
        toolBarAccentColor = new ToolBarAccentColor(this);
        toolBarAccentColor.setVisible(FlatUIUtils.getUIBoolean("AccentControl.show", false));
        add(header);
        add(scroll);
        add(toolBarAccentColor);
    }

    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
            } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

    private Component createTitle(String title) {
        JSeparator separator = new JSeparator();
        
        separator.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.foreground;"
                + "height:1");
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(separator, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        separator.setForeground(getForeground());
        separator.setOpaque(true);
        
        return panel;
    }

    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

    protected void setSelected(int index, int subIndex) {
        int size = panelMenu.getComponentCount();
        for (int i = 0; i < size; i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == index) {
                    item.setSelectedIndex(subIndex);
                } else {
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction menuAction = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, menuAction);
        }
        if (!menuAction.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void hideMenuItem() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).hideMenuItem();
            }
        }
        revalidate();
    }

    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private ToolBarAccentColor toolBarAccentColor;

    public boolean isAnimating() {
        return isAnimating;
    }

    private void startAnimation() {
        isAnimating = true;
        final int targetWidth = menuFull ? menuMaxWidth : menuMinWidth;
        final int startWidth = currentWidth;
        final int difference = targetWidth - startWidth;
        final int steps = ANIMATION_STEPS;
        final int delay = ANIMATION_DURATION / steps;
        
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
        
        final int[] step = {0};
        
        animationTimer = new Timer(delay, e -> {
            step[0]++;
            
            if (step[0] >= steps) {
                currentWidth = targetWidth;
                isAnimating = false;
                animationTimer.stop();
            } else {
                float progress = (float) step[0] / steps;
                float easedProgress = cubicEaseInOut(progress);
                currentWidth = startWidth + (int) (difference * easedProgress);
            }
            
            revalidate();
            repaint();
        });
        
        animationTimer.start();
    }
    
    private float cubicEaseInOut(float t) {
        return t < 0.5f ? 4 * t * t * t : 1 - (float)Math.pow(-2 * t + 2, 3) / 2;
    }

    private class MenuLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int gap = UIScale.scale(5);
                int sheaderFullHgap = UIScale.scale(headerFullHgap);
                
                int width = isAnimating ? currentWidth : (menuFull ? menuMaxWidth : menuMinWidth);
                
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int iconWidth = width - (insets.left + insets.right);
                int iconHeight = header.getPreferredSize().height;
                int hgap = menuFull ? sheaderFullHgap : 0;
                int accentColorHeight = 0;
                if (toolBarAccentColor.isVisible()) {
                    accentColorHeight = toolBarAccentColor.getPreferredSize().height+gap;
                }

                header.setBounds(x, y, iconWidth, iconHeight);
                
                int ldgap = UIScale.scale(10);
                int menux = x;
                int menuy = y + iconHeight + gap;
                int menuWidth = width - (insets.left + insets.right);
                int menuHeight = height - (iconHeight + gap) - accentColorHeight - gigiSize;
                scroll.setBounds(menux, menuy, menuWidth, menuHeight);

                if (toolBarAccentColor.isVisible()) {
                    int tbheight = toolBarAccentColor.getPreferredSize().height;
                    int tbwidth = Math.min(toolBarAccentColor.getPreferredSize().width, width - ldgap * 2);
                    int tby = y + height - tbheight - ldgap - gigiSize;
                    int tbx = x + ldgap + (((width - ldgap * 2) - tbwidth) / 2);
                    toolBarAccentColor.setBounds(tbx, tby, tbwidth, tbheight);
                }

                gigi.setBounds(x, height - gigiSize + insets.top, width - (insets.left + insets.right), gigiSize);
                
                parent.setPreferredSize(new Dimension(width, parent.getHeight()));
            }
        }
    }

    public int getCurrentWidth() {
        return isAnimating ? currentWidth : (menuFull ? menuMaxWidth : menuMinWidth);
    }

    public void setUsername(String username) {
        for (int i = 0; i < panelMenu.getComponentCount(); i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == USER_MENU_INDEX) {
                    item.updateMenuName(username);
                    break;
                }
            }
        }
        revalidate();
        repaint();
    }

    public void setUserProfileIcon(byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            return;
        }
        
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));
            if (image != null) {
                setUserProfileIcon(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setUserProfileIconFromFile(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            if (image != null) {
                setUserProfileIcon(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setUserProfileIcon(BufferedImage image) {
        int size = UIScale.scale(24);
        
        BufferedImage circleBuffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new Ellipse2D.Double(0, 0, size, size));
        g2.setComposite(AlphaComposite.SrcIn);
        
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        g2.drawImage(scaledImage, 0, 0, null);
        g2.dispose();
        
        ImageIcon icon = new ImageIcon(circleBuffer);
        
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == USER_MENU_INDEX) {
                    item.setMenuIcon(icon);
                    break;
                }
            }
        }
    }
}
