package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.model.Brand;
import com.pheobe.model.Category;
import com.pheobe.service.Brand_DAO;
import com.pheobe.service.Category_DAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author pheoebe
 */
public class CategoryAndBrandManagement extends javax.swing.JPanel {

    private DefaultTableModel dtmCategory = new DefaultTableModel();
    private DefaultTableModel dtmBrand = new DefaultTableModel();
    private Brand_DAO ServiceBrand = new Brand_DAO();
    private Category_DAO ServiceCategory = new Category_DAO();

    public CategoryAndBrandManagement(String text) {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lb.setText(text);
        tbtBrand.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        tbtBrand.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);

        tbtBrand.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);

        jScrollPane2.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);

        String[] columnNames = {"ID", "Name", "Delete"};
        dtmBrand = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        tbtCategory.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        tbtCategory.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);

        tbtCategory.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);

        String[] columnNames1 = {"ID", "Name", "Delete"};
        dtmCategory = new DefaultTableModel(columnNames1, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        for (int i = 0; i < tbtBrand.getColumnCount(); i++) {
            if (i != 2 && i != 3) {
                tbtBrand.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        for (int i = 0; i < tbtCategory.getColumnCount(); i++) {
            if (i != 2 && i != 3) {
                tbtCategory.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        setupPlaceholder(txtBrand, "- Brand");
        setupPlaceholder(txtCategory, "- Category");

        tbtBrand.setModel(dtmBrand);
        tbtCategory.setModel(dtmCategory);

        tbtCategory.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        tbtCategory.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor("category"));

        tbtBrand.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        tbtBrand.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor("brand"));

        loadBrandTable(ServiceBrand.selectAll());
        loadCategoryTable(ServiceCategory.selectAll());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtBrand = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbtCategory = new javax.swing.JTable();
        txtCategory = new javax.swing.JTextField();
        btnAddCategory = new javax.swing.JButton();
        txtBrand = new javax.swing.JTextField();
        btnAddBrand = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Category and Brand");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tbtBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Delete"
            }
        ));
        tbtBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtBrandMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtBrand);

        tbtCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "Delete"
            }
        ));
        tbtCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtCategoryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbtCategory);

        btnAddCategory.setText("Add");
        btnAddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCategoryActionPerformed(evt);
            }
        });

        btnAddBrand.setText("Add");
        btnAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBrandActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddCategory)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddBrand)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddCategory))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAddBrand)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbtCategoryMouseClicked(java.awt.event.MouseEvent evt) {
        int index = tbtCategory.getSelectedRow();
        if (index >= 0 && index < ServiceCategory.selectAll().size()) {
            Category c = ServiceCategory.selectAll().get(index);
            txtCategory.setText(c.getName());
            txtCategory.setForeground(java.awt.Color.BLACK);
        }
    }

    private void tbtBrandMouseClicked(java.awt.event.MouseEvent evt) {
        int index = tbtBrand.getSelectedRow();
        if (index >= 0 && index < ServiceBrand.selectAll().size()) {
            Brand c = ServiceBrand.selectAll().get(index);
            txtBrand.setText(c.getName());
            txtBrand.setForeground(java.awt.Color.BLACK);
        }
    }

    private boolean validateCategory(String category) {
        if (category.isEmpty() || category.equals("- Category")) {
            Application.showMessage(Notifications.Type.WARNING, "Category name cannot be empty");
            return false;
        }
        if (category.length() > 50) {
            Application.showMessage(Notifications.Type.WARNING, "Category name cannot exceed 50 characters");
            return false;
        }
        if (!category.matches("^[a-zA-Z0-9\\s-]+$")) {
            Application.showMessage(Notifications.Type.WARNING, "Category name can only contain letters, numbers, spaces and hyphens");
            return false;
        }
        return true;
    }

    private boolean validateBrand(String brand) {
        if (brand.isEmpty() || brand.equals("- Brand")) {
            Application.showMessage(Notifications.Type.WARNING, "Brand name cannot be empty");
            return false;
        }
        if (brand.length() > 50) {
            Application.showMessage(Notifications.Type.WARNING, "Brand name cannot exceed 50 characters");
            return false;
        }
        if (!brand.matches("^[a-zA-Z0-9\\s-]+$")) {
            Application.showMessage(Notifications.Type.WARNING, "Brand name can only contain letters, numbers, spaces and hyphens");
            return false;
        }
        return true;
    }

    private void btnAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {
        String categoryName = txtCategory.getText();
        if (!validateCategory(categoryName)) {
            return;
        }
        boolean addCategorySuccsess = ServiceCategory.insertCategory(getCategoryData());
        if (addCategorySuccsess) {
            loadCategoryTable(ServiceCategory.selectAll());
            Application.showMessage(Notifications.Type.SUCCESS, "Add Category Completed");
            txtCategory.setText("- Category");
            txtCategory.setForeground(java.awt.Color.GRAY);
        } else {
            Application.showMessage(Notifications.Type.ERROR, "Add Fail");
        }
    }

    private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {
        String brandName = txtBrand.getText();
        if (!validateBrand(brandName)) {
            return;
        }
        boolean addBrandSuccsess = ServiceBrand.insert(getBrandData());
        if (addBrandSuccsess) {
            loadBrandTable(ServiceBrand.selectAll());
            Application.showMessage(Notifications.Type.SUCCESS, "Add Brand Completed");
            txtBrand.setText("- Brand");
            txtBrand.setForeground(java.awt.Color.GRAY);
        } else {
            Application.showMessage(Notifications.Type.ERROR, "Add Fail");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBrand;
    private javax.swing.JButton btnAddCategory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbtBrand;
    private javax.swing.JTable tbtCategory;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCategory;
    // End of variables declaration//GEN-END:variables

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            String buttonText = table.getColumnName(column);
            setText(buttonText);
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private int itemId;
        private boolean isPushed;
        private String action;
        private String typeTable;
        private int selectedRow;

        public ButtonEditor(String type) {
            button = new JButton();
            button.setOpaque(true);
            this.typeTable = type;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            itemId = (int) value;
            action = table.getColumnName(column);
            button.setText(action);
            isPushed = true;
            selectedRow = row;
            
            if ("Edit".equals(action)) {
                if (typeTable.equals("category")) {
                    if (selectedRow >= 0 && selectedRow < ServiceCategory.selectAll().size()) {
                        Category c = ServiceCategory.selectAll().get(selectedRow);
                        txtCategory.setText(c.getName());
                        txtCategory.setForeground(java.awt.Color.BLACK);
                    }
                } else if (typeTable.equals("brand")) {
                    if (selectedRow >= 0 && selectedRow < ServiceBrand.selectAll().size()) {
                        Brand b = ServiceBrand.selectAll().get(selectedRow);
                        txtBrand.setText(b.getName());
                        txtBrand.setForeground(java.awt.Color.BLACK);
                    }
                }
            }
            
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                if (typeTable.equals("category")) {
                    if ("Delete".equals(action)) {
                        removeCategory(itemId);
                    }
                } else if (typeTable.equals("brand")) {
                    if ("Delete".equals(action)) {
                        removeBrand(itemId);
                    }
                }
            }
            isPushed = false;
            return itemId;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    
    private void loadCategoryTable(List<Category> listCategory) {
        dtmCategory.setRowCount(0);
        for (Category c : listCategory) {
            dtmCategory.addRow(new Object[]{
                c.getCategoryId(), 
                c.getName(),
                c.getCategoryId()   // For Delete button only
            });
        }
    }

    private void loadBrandTable(List<Brand> listBrand) {
        dtmBrand.setRowCount(0);
        for (Brand b : listBrand) {
            dtmBrand.addRow(new Object[]{
                b.getBrandId(), 
                b.getName(),
                b.getBrandId()   // For Delete button only
            });
        }
    }

    private Category getCategoryData() {
        String category = txtCategory.getText();
        if (category.equals("- Category")) {
            category = "";
        }
        Category c = new Category();
        c.setName(category);
        return c;
    }

    private Brand getBrandData() {
        String brand = txtBrand.getText();
        if (brand.equals("- Brand")) {
            brand = "";
        }
        Brand c = new Brand();
        c.setName(brand);
        return c;
    }
    
    private void removeCategory(int categoryID){
        boolean succsess = ServiceCategory.delete(categoryID);
        if(succsess){
            loadCategoryTable(ServiceCategory.selectAll());
            Application.showMessage(Notifications.Type.SUCCESS, "Remove category success");
        }else{
            Application.showMessage(Notifications.Type.ERROR, "Remove Fail");
        }
    }
    private void removeBrand(int brandId){
        boolean succsess = ServiceBrand.delete(brandId);
        if(succsess){
            loadBrandTable(ServiceBrand.selectAll());
            Application.showMessage(Notifications.Type.SUCCESS, "Remove brand success");
        }else{
            Application.showMessage(Notifications.Type.ERROR, "Remove Fail");
        }
    }
    
    private void setupPlaceholder(javax.swing.text.JTextComponent textComponent, String placeholder) {
        textComponent.setForeground(java.awt.Color.GRAY);
        textComponent.setText(placeholder);

        textComponent.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textComponent.getText().equals(placeholder)) {
                    textComponent.setText("");
                    textComponent.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setForeground(java.awt.Color.GRAY);
                    textComponent.setText(placeholder);
                }
            }
        });
    }
}
