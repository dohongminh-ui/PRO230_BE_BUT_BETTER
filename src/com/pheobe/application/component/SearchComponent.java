package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class SearchComponent extends JPanel {
    
    private JTextField searchField;
    private JButton searchButton;
    
    public SearchComponent() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new MigLayout("fillx, insets 0", "[fill][]", "[]"));
        
        searchField = new JTextField();
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        // searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new ImageIcon(getClass().getResource("/com/pheobe/icon/search.png")));
        
        searchButton = new JButton("Search");
        
        add(searchField, "growx");
        add(searchButton);
    }
    
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    
    public void addSearchTextChangeListener(SearchTextChangeListener listener) {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.textChanged(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.textChanged(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.textChanged(searchField.getText());
            }
        });
    }
    
    public String getSearchText() {
        return searchField.getText();
    }
    
    public void setSearchText(String text) {
        searchField.setText(text);
    }
    
    // Interface for real-time search text changes
    public interface SearchTextChangeListener {
        void textChanged(String text);
    }
}