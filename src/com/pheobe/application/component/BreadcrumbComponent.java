package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BreadcrumbComponent extends JPanel {
    private final List<BreadcrumbItem> breadcrumbPath = new ArrayList<>();
    private final JPanel breadcrumbPanel;
    private BreadcrumbListener breadcrumbListener;
    
    public BreadcrumbComponent() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 0, 0, 0));

        breadcrumbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        breadcrumbPanel.setOpaque(false);
        add(breadcrumbPanel, BorderLayout.CENTER);
    }

    public void addBreadcrumb(String name, Object data) {
        BreadcrumbItem item = new BreadcrumbItem(name, data);
        breadcrumbPath.add(item);
        updateBreadcrumbDisplay();
    }

    public int getBreadcrumbSize() {
        return breadcrumbPath.size();
    }

    public void navigateToHome() {
        if (!breadcrumbPath.isEmpty()) {
            breadcrumbPath.clear();
            updateBreadcrumbDisplay();
            if (breadcrumbListener != null) {
                breadcrumbListener.onBreadcrumbClick(null, -1);
            }
        }
    }

    public void navigateTo(int index) {
        if (index >= 0 && index < breadcrumbPath.size() - 1) {
            Object data = breadcrumbPath.get(index).getData();

            while (breadcrumbPath.size() > index + 1) {
                breadcrumbPath.remove(breadcrumbPath.size() - 1);
            }
            updateBreadcrumbDisplay();

            if (breadcrumbListener != null) {
                breadcrumbListener.onBreadcrumbClick(data, index);
            }
        }
    }

    private void updateBreadcrumbDisplay() {
        breadcrumbPanel.removeAll();
        
        JButton homeButton = new JButton("Home");
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setForeground(new Color(0, 123, 255));
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.putClientProperty(FlatClientProperties.STYLE, "font: bold");
        homeButton.addActionListener(e -> {
            navigateToHome();

            if (breadcrumbListener != null) {
                breadcrumbListener.onBreadcrumbClick(null, -1);
            }
        });
        breadcrumbPanel.add(homeButton);

        for (int i = 0; i < breadcrumbPath.size(); i++) {
            final int index = i;
            BreadcrumbItem item = breadcrumbPath.get(i);

            JLabel separator = new JLabel(" > ");
            separator.setForeground(Color.GRAY);
            breadcrumbPanel.add(separator);

            if (i == breadcrumbPath.size() - 1) {
                JLabel currentLabel = new JLabel(item.getName());
                currentLabel.setForeground(Color.DARK_GRAY);
                currentLabel.putClientProperty(FlatClientProperties.STYLE, "font: bold");
                breadcrumbPanel.add(currentLabel);
            } else {
                JButton button = new JButton(item.getName());
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                button.putClientProperty(FlatClientProperties.STYLE, "font: bold");
                button.addActionListener(e -> {
                    navigateTo(index);

                    if (breadcrumbListener != null && index < breadcrumbPath.size()) {
                        breadcrumbListener.onBreadcrumbClick(breadcrumbPath.get(index).getData(), index);
                    }
                });
                breadcrumbPanel.add(button);
            }
        }

        revalidate();
        repaint();
    }

    public void setBreadcrumbListener(BreadcrumbListener listener) {
        this.breadcrumbListener = listener;
    }

    private static class BreadcrumbItem {
        private final String name;
        private final Object data;

        public BreadcrumbItem(String name, Object data) {
            this.name = name;
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public Object getData() {
            return data;
        }
    }
    
    public interface BreadcrumbListener {
        void onBreadcrumbClick(Object item, int index);
    }
}