package com.pheobe.application.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class SearchComponent extends JPanel {
    
    private JTextField searchField;
    private JButton searchButton;
    
    public SearchComponent() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        
        centerPanel.add(Box.createHorizontalGlue());
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        searchField = new JTextField();
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        searchField.setPreferredSize(new Dimension(300, 30));
        searchField.setMaximumSize(new Dimension(400, 30));
        
        searchButton = new JButton("Search");
        
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        
        centerPanel.add(searchPanel);
        
        centerPanel.add(Box.createHorizontalGlue());
        
        add(centerPanel, BorderLayout.CENTER);
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
    
    public interface SearchTextChangeListener {
        void textChanged(String text);
    }
}