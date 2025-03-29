package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.model.Brand;
import com.pheobe.model.Category;
import com.pheobe.model.Product;
import com.pheobe.service.Brand_DAO;
import com.pheobe.service.Category_DAO;
import com.pheobe.service.Product_DAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author pheoebe
 */
public class ProductManagementForm extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private Product_DAO ProductService = new Product_DAO();
    private Brand_DAO BrandService = new Brand_DAO();
    private Category_DAO CategoryService = new Category_DAO();

    public ProductManagementForm() {
        initComponents();
        tbtProduct.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        tbtProduct.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);

        tbtProduct.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);

        jLabel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        dtm = (DefaultTableModel) tbtProduct.getModel();
        loadTable(ProductService.getAllProducts());
        loadCategoryCB();
        LoadBrandCB();

        txtImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseProductImage();
            }
        });

        setupPlaceholder(btnProductName, "Enter product name");
        setupPlaceholder(txtPrice, "Enter price");
        setupPlaceholder(txtStock, "Enter stock quantity");
        setupPlaceholder(txtImg, "Click to choose image");
        setupPlaceholder(jTextArea1, "Enter product description");
        tbtProduct.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        tbtProduct.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor());
        tbtProduct.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        tbtProduct.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor());
    }

    private void LoadBrandCB() {
        cbBrand.removeAllItems();
        BrandService.selectAll();
        for (Brand b : BrandService.selectAll()) {
            cbBrand.addItem(b);
        }
    }

    private void loadCategoryCB() {
        cbCategory.removeAllItems();
        CategoryService.selectAll();
        for (Category bc : CategoryService.selectAll()) {
            cbCategory.addItem(bc);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnProductName = new javax.swing.JTextField();
        cbBrand = new javax.swing.JComboBox<>();
        btnADD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtProduct = new javax.swing.JTable();
        txtImg = new javax.swing.JTextField();
        cbCategory = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txtStock = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Product Management");

        cbBrand.setModel(new javax.swing.DefaultComboBoxModel<>(new Brand[]{}));

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        tbtProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Picture", "Name", "Description", "Category", "Brand", "Price", "Stock", "Edit", "Delete"
            }
        ));
        tbtProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbtProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbtProduct);

        cbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new Category[] {}));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(txtImg))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(cbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbBrand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtStock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnProductName, txtImg, txtPrice, txtStock});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnADD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnProductName, txtImg, txtPrice, txtStock});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbBrand, cbCategory});

    }// </editor-fold>//GEN-END:initComponents

    private void tbtProductMouseClicked(java.awt.event.MouseEvent evt) {
        int index = tbtProduct.getSelectedRow();
        Product p = ProductService.getAllProducts().get(index);
        txtImg.setText(p.getImg());
        btnProductName.setText(p.getName());
        txtPrice.setText(p.getPrice().toString());
        txtStock.setText(String.valueOf(p.getStock()));
        jTextArea1.setText(p.getDescription());
        
        Category category = CategoryService.selectById(p.getCategoryId());
        for (int i = 0; i < cbCategory.getItemCount(); i++) {
            Category item = cbCategory.getItemAt(i);
            if (item.getCategoryId() == category.getCategoryId()) {
                cbCategory.setSelectedIndex(i);
                break;
            }
        }
        
        Brand brand = BrandService.selectById(p.getBrandId());
        for (int i = 0; i < cbBrand.getItemCount(); i++) {
            Brand item = cbBrand.getItemAt(i);
            if (item.getBrandId() == brand.getBrandId()) {
                cbBrand.setSelectedIndex(i);
                break;
            }
        }
    }

    private boolean validateFields() {
        StringBuilder errorMessage = new StringBuilder("Please fill in the following fields:\n");
        boolean isValid = true;

        if (btnProductName.getText().trim().isEmpty() || btnProductName.getText().equals("Enter product name")) {
            errorMessage.append("- Product Name\n");
            isValid = false;
        }

        if (txtPrice.getText().trim().isEmpty() || txtPrice.getText().equals("Enter price")) {
            errorMessage.append("- Price\n");
            isValid = false;
        } else {
            try {
                new BigDecimal(txtPrice.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("- Price (must be a valid number)\n");
                isValid = false;
            }
        }

        if (txtStock.getText().trim().isEmpty() || txtStock.getText().equals("Enter stock quantity")) {
            errorMessage.append("- Stock\n");
            isValid = false;
        } else {
            try {
                Integer.parseInt(txtStock.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("- Stock (must be a valid number)\n");
                isValid = false;
            }
        }

        if (txtImg.getText().trim().isEmpty() || txtImg.getText().equals("Click to choose image")) {
            errorMessage.append("- Image\n");
            isValid = false;
        }

        if (jTextArea1.getText().trim().isEmpty() || jTextArea1.getText().equals("Enter product description")) {
            errorMessage.append("- Description\n");
            isValid = false;
        }

        if (cbCategory.getSelectedIndex() == -1) {
            errorMessage.append("- Category\n");
            isValid = false;
        }

        if (cbBrand.getSelectedIndex() == -1) {
            errorMessage.append("- Brand\n");
            isValid = false;
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(
                this,
                errorMessage.toString(),
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
            );
        }

        return isValid;
    }

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validateFields()) {
            return;
        }
        
        int i = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this product?", "Confirm Add", JOptionPane.YES_NO_OPTION);
        if(i == JOptionPane.YES_OPTION){
            try {
                ProductService.addProductBB(getData());
                loadTable(ProductService.getAllProducts());
                clearFields();
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error adding product: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        btnProductName.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtImg.setText("");
        jTextArea1.setText("");
        cbCategory.setSelectedIndex(0);
        cbBrand.setSelectedIndex(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JTextField btnProductName;
    private javax.swing.JComboBox<Brand> cbBrand;
    private javax.swing.JComboBox<Category> cbCategory;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tbtProduct;
    private javax.swing.JTextField txtImg;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Get the button text from the column name
            String buttonText = table.getColumnName(column);
            setText(buttonText);
            return this;
        }
    }

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private int productId;
        private boolean isPushed;
        private String action;

        public ButtonEditor() {
            button = new JButton();
            button.setOpaque(true);

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
            productId = (int) value;
            action = table.getColumnName(column);
            button.setText(action);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                if ("Delete".equals(action)) {
                    RemoveProduct(productId);
                } else if ("Edit".equals(action)) {
                    EditProduct(productId);
                }
            }
            isPushed = false;
            return productId;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    private void loadTable(List<Product> product) {
        dtm.setRowCount(0);
        for (Product p : product) {
            
            Category category = CategoryService.selectById(p.getCategoryId());
            String categoryName = category != null ? category.getName() : "N/A";
            
            Brand brand = BrandService.selectById(p.getBrandId());
            String brandName = brand != null ? brand.getName() : "N/A";
            
            dtm.addRow(new Object[]{
                p.getImg(), 
                p.getName(), 
                p.getDescription(), 
                categoryName,  
                brandName,    
                p.getPrice(), 
                p.getStock(),
                p.getIdProduct(),
                p.getIdProduct()
            });
        }
    }

    private Product getData() {
        String img = txtImg.getText();
        String name = btnProductName.getText();
        String description = jTextArea1.getText();
        
        Category selectedCategory = (Category) cbCategory.getSelectedItem();
        Brand selectedBrand = (Brand) cbBrand.getSelectedItem();
        
        int categoryId = selectedCategory.getCategoryId(); 
        int brandId = selectedBrand.getBrandId(); 
        
        if (img.equals("Click to choose image")) img = "";
        if (name.equals("Enter product name")) name = "";
        if (description.equals("Enter product description")) description = "";
        
        BigDecimal price = txtPrice.getText().equals("Enter price") ? 
            BigDecimal.ZERO : new BigDecimal(txtPrice.getText());
        int stock = txtStock.getText().equals("Enter stock quantity") ? 
            0 : Integer.parseInt(txtStock.getText());
        
        Product product = new Product();
        product.setImg(img);
        product.setName(name);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setBrandId(brandId);
        product.setPrice(price);
        product.setStock(stock);
        return product;
    }

    private void chooseProductImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Product Image");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            txtImg.setText(imagePath);
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
    
    private void RemoveProduct(int idProduct){
        boolean success = ProductService.deleteProduct(idProduct);
        if(success){
            loadTable(ProductService.getAllProducts());
            Application.showMessage(Notifications.Type.INFO, "Remove completed");
        }else{
            Application.showMessage(Notifications.Type.ERROR, "remove fail");
        }
    }
    
    private void EditProduct(int productId){
        boolean success = ProductService.updateProduct(productId, getData());
        if(success){
            loadTable(ProductService.getAllProducts());
            Application.showMessage(Notifications.Type.INFO, "Edit completed");
        }else{
            Application.showMessage(Notifications.Type.ERROR, "Edit fail");
        }
    }
    
}
