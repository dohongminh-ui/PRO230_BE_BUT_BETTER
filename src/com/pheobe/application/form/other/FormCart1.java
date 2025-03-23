package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.model.Cart;
import com.pheobe.model.Cart_detail;
import com.pheobe.model.Product;
import com.pheobe.service.Cart_DAO;
import com.pheobe.service.Cart_Detail_DAO;
import com.pheobe.service.Product_DAO;
import com.pheobe.application.Application;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.time.LocalDateTime.now;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author pheobeo
 */
public class FormCart1 extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private Cart_DAO serviceCart = new Cart_DAO();
    private Cart_Detail_DAO serviceCartDao = new Cart_Detail_DAO();

    public FormCart1() {
        initComponents();
        lblCart.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        tbtCart.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);

        tbtCart.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);
        dtm = (DefaultTableModel) tbtCart.getModel();

        if (tbtCart.getColumnCount() < 4) {
            String[] columnNames = {"Product", "Quantity", "Price", "Action"};
            dtm = new DefaultTableModel(columnNames, 0);
            tbtCart.setModel(dtm);
        }

        tbtCart.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tbtCart.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor());

        refreshCart();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCart = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnCheckOut = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtCart = new javax.swing.JTable();
        lblMoney = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(806, 455));

        lblCart.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCart.setText("Cart");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Estimate shipping & handling");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total");

        btnCheckOut.setBackground(new java.awt.Color(255, 179, 72));
        btnCheckOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckOut.setText("Checkout");
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Summary");

        tbtCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "Product", "Quantity", "Price", "Action"
            }
        ));
        jScrollPane1.setViewportView(tbtCart);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lblCart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCheckOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMoney)
                        .addGap(117, 117, 117))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblMoney))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblCart, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        
    }//GEN-LAST:event_btnCheckOutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCart;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JTable tbtCart;
    // End of variables declaration//GEN-END:variables

        class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Remove");
            return this;
        }
    }

    // Custom button editor for the table
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;
        private int productId;
        private boolean isPushed;

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
            
            productId = (int)value;
            button.setText("Remove");
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                removeCartItem(productId);
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

    private void loadTableCart(List<Cart_detail> list) {
        dtm.setRowCount(0);
        Product_DAO productDao = new Product_DAO();

        int cartId = getCurrentCartID();
        for (Cart_detail cd : list) {
            if(cd.getCartID()== cartId){
               Product product = productDao.getProductById(cd.getProductId());
            String productName = (product != null) ? product.getName() : "product" + product.getIdProduct();
            int productId = product.getIdProduct();
            dtm.addRow(new Object[]{
                productName, cd.getQuantity(), cd.getPrice(), productId
            }); 
            }
        }
    }

    private void removeCartItem(int productId) {
        Cart_Detail_DAO cdD = new Cart_Detail_DAO();
        boolean success = cdD.delete(productId);
        if (success) {
            List<Cart_detail> updateCart = serviceCartDao.selectAll();
            loadTableCart(updateCart);
            updateTotalAmount();
        } else {
            Application.showMessage(Notifications.Type.ERROR, "Failed to remove item from cart");
        }
    }

    private void updateTotalAmount() {
        double total = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            int quantity = (int) dtm.getValueAt(i, 1);
            Object priceObj = dtm.getValueAt(i, 2);

            if (priceObj instanceof java.math.BigDecimal) {
                java.math.BigDecimal price = (java.math.BigDecimal) priceObj;
                total += price.doubleValue() * quantity;
            } else if (priceObj instanceof String) {
                String priceStr = (String) priceObj;
                priceStr = priceStr.replace("$", "").trim();
                try {
                    double price = Double.parseDouble(priceStr);
                    total += price * quantity;
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing price: " + priceStr);
                }
            }
        }

        lblMoney.setText("$" + String.format("%.2f", total));
    }

    private int getCurrentCartID() {
        try {
            int userId = getCurrentUserID();
            Cart_DAO cartDao = new Cart_DAO();
            List<Cart> carts = cartDao.selectAll();

            for (Cart c : carts) {
                if (c.getCustomerId() == userId && "Active".equalsIgnoreCase(c.getStatus())) {
                    return c.getId();
                }
            }

            Cart newCart = new Cart();
            newCart.setCustomerId(userId);
            newCart.setCartID("CART-" + userId + "-" + System.currentTimeMillis());
            newCart.setStatus("Active");
            newCart.setCreateDate(now());

            boolean created = cartDao.insert(newCart);
            if (created) {
                return -1;
            }

            carts = cartDao.selectAll();
            for (Cart c : carts) {
                if (c.getCustomerId() == userId && "Active".equalsIgnoreCase(c.getStatus())) {
                    return c.getId();
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getCurrentUserID() {
        return Application.getCurrentUser().getIdCustomer();
    }

    public void refreshCart() {
        List<Cart_detail> cartItems = serviceCartDao.selectAll();
        loadTableCart(cartItems);
        updateTotalAmount();
    }
}

