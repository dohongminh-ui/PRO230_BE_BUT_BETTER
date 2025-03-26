package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.model.Cart;
import com.pheobe.model.Cart_detail;
import com.pheobe.model.Product;
import com.pheobe.service.Cart_DAO;
import com.pheobe.service.Cart_Detail_DAO;
import com.pheobe.service.Product_DAO;
import com.pheobe.application.Application;
import com.pheobe.model.Customer;
import com.pheobe.model.Order;
import com.pheobe.model.Order_Detail;
import com.pheobe.model.Payment;
import com.pheobe.service.Customer_DAO;
import com.pheobe.service.Order_DAO;
import com.pheobe.service.Order_Detail_DAO;
import com.pheobe.service.Payment_DAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;

/**
 *
 * @author pheobeo
 */
public class FormCart1 extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private Cart_DAO serviceCart = new Cart_DAO();
    private Cart_Detail_DAO serviceCartDao = new Cart_Detail_DAO();
    private Customer_DAO serviceCustomer = new Customer_DAO();

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
        
        String[] columnNames = {"Product", "Quantity", "Price", "Action"};
        dtm = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 3;
            }
        };
        tbtCart.setModel(dtm);
        
        tbtCart.setDefaultEditor(Object.class, null);
        
        tbtCart.getColumnModel().getColumn(1).setCellEditor(new SpinnerEditor());
        
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product", "Quantity", "Price", "Action"
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

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {
        if (dtm.getRowCount() == 0) {
            Application.showMessage(Notifications.Type.WARNING, "Your cart is empty!");
            return;
        }

        try {
            int cartId = getCurrentCartID();
            Customer customer = serviceCustomer.selectById(Application.getCurrentUser().getIdCustomer());
            if (cartId == -1) {
                Application.showMessage(Notifications.Type.ERROR, "Failed to get cart information");
                return;
            }

            boolean addressMissing = customer.getAddress() == null || customer.getAddress().trim().isEmpty();
            boolean phoneMissing = customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().isEmpty();
            
            if (addressMissing || phoneMissing) {
                String message;
                if (addressMissing && phoneMissing) {
                    message = "Please update your address and phone number in Personal Information before checkout!";
                } else if (addressMissing) {
                    message = "Please update your address in Personal Information before checkout!";
                } else {
                    message = "Please update your phone number in Personal Information before checkout!";
                }
                Application.showMessage(Notifications.Type.WARNING, message);
                return;
            }

            int customerId = getCurrentUserID();
            Order order = new Order();
            order.setCustomerID(customerId);
            order.setShippingAddress(customer.getAddress());
            order.setShippingPhoneNumber(customer.getPhoneNumber());
            order.setCreateDate(LocalDateTime.now());
            order.setStatus("Completed");

            Order_DAO orderDao = new Order_DAO();
            boolean orderCreated = orderDao.insertOrder(order);

            if (!orderCreated) {
                Application.showMessage(Notifications.Type.ERROR, "Failed to create order");
                return;
            }

            List<Order> customerOrders = orderDao.getOrdersByCustomerId(customerId);
            int newOrderId = -1;
            if (!customerOrders.isEmpty()) {
                customerOrders.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
                newOrderId = customerOrders.get(0).getOrderID();
            }

            if (newOrderId == -1) {
                Application.showMessage(Notifications.Type.ERROR, "Failed to retrieve order information");
                return;
            }

            List<Cart_detail> allCartDetails = serviceCartDao.selectAll();
            List<Cart_detail> cartDetails = new ArrayList<>();

            for (Cart_detail detail : allCartDetails) {
                if (detail.getCartID() == cartId && "Active".equals(detail.getStatus())) {
                    cartDetails.add(detail);
                }
            }

            if (cartDetails.isEmpty()) {
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String productName = (String) dtm.getValueAt(i, 0);
                    int quantity = (int) dtm.getValueAt(i, 1);
                    Object priceObj = dtm.getValueAt(i, 2);
                    int productId = (int) dtm.getValueAt(i, 3);

                    Cart_detail cd = new Cart_detail();
                    cd.setCartID(cartId);
                    cd.setProductId(productId);
                    cd.setQuantity(quantity);

                    if (priceObj instanceof BigDecimal) {
                        cd.setPrice((BigDecimal) priceObj);
                    } else {
                        String priceStr = priceObj.toString().replace("$", "").trim();
                        cd.setPrice(new BigDecimal(priceStr));
                    }

                    cartDetails.add(cd);
                }
            }

            Order_Detail_DAO orderDetailDao = new Order_Detail_DAO();
            Product_DAO productDao = new Product_DAO();

            BigDecimal totalAmount = BigDecimal.ZERO;

            for (Cart_detail cartDetail : cartDetails) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderID(newOrderId);
                orderDetail.setProductID(cartDetail.getProductId());
                orderDetail.setPrice(cartDetail.getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetail.setStatus("Completed");

                orderDetailDao.insertOrderDetail(orderDetail);

                totalAmount = totalAmount.add(cartDetail.getPrice().multiply(new BigDecimal(cartDetail.getQuantity())));

                Product product = productDao.getProductById(cartDetail.getProductId());
                if (product != null) {
                    product.setStock(product.getStock() - cartDetail.getQuantity());
                    productDao.updateProduct(product.getIdProduct(), product);
                }
            }

            Payment payment = new Payment();
            payment.setOrderID(newOrderId);
            payment.setCustomerID(customerId);
            payment.setPaymentType("Direct");
            payment.setTotalMoney(totalAmount);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setStatus("Completed");

            Payment_DAO paymentDao = new Payment_DAO();
            paymentDao.addPayment(payment);

            for (Cart_detail cartDetail : cartDetails) {
                cartDetail.setStatus("Ordered");
                serviceCartDao.update(cartDetail);
            }

            Cart_DAO cartDao = new Cart_DAO();
            Cart cart = cartDao.selectById(cartId);
            if (cart != null) {
                cart.setStatus("Completed");
                cartDao.update(cart);
            }

            Application.showMessage(Notifications.Type.SUCCESS, "Order placed successfully!");

            refreshCart();

            Application.showForm(new FormHistory());
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, "Error processing order: " + e.getMessage());
        }
    }


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

            productId = (int) value;
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
            if (cd.getCartID() == cartId) {
                Product product = productDao.getProductById(cd.getProductId());
                String productName = (product != null) ? product.getName() : "product" + product.getIdProduct();
                String formattedPrice = "$" + cd.getPrice().toString();
                int productId = product.getIdProduct();

                dtm.addRow(new Object[]{
                    productName, cd.getQuantity(), formattedPrice, productId
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
        lblMoney.repaint();
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

    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private JSpinner spinner;
        private int productId;
        private int initialValue;
        private int maxStock;
        
        public SpinnerEditor() {
            spinner = new JSpinner();
            spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1, 1));
            
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
            editor.getTextField().setEditable(true);
            editor.getTextField().addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent evt) {
                    validateQuantity();
                }
            });
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, 
                boolean isSelected, int row, int column) {
            initialValue = (Integer) value;
            productId = (int) dtm.getValueAt(row, 3);
            
            Product_DAO productDao = new Product_DAO();
            Product product = productDao.getProductById(productId);
            maxStock = 1;
            
            if (product != null) {
                maxStock = product.getStock();
                if (initialValue > maxStock) {
                    maxStock = initialValue;
                }
            }
            
            spinner.setModel(new javax.swing.SpinnerNumberModel(
                initialValue,
                1,
                Integer.MAX_VALUE,
                1
            ));
            
            return spinner;
        }
        
        @Override
        public Object getCellEditorValue() {
            validateQuantity();
            
            int newValue = (Integer) spinner.getValue();
            
            if (newValue != initialValue) {
                updateCartItemQuantity(productId, newValue);
                SwingUtilities.invokeLater(() -> {
                    updateTotalAmount();
                });
            }
            
            return newValue;
        }
        
        private void validateQuantity() {
            try {
                JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
                String text = editor.getTextField().getText();
                int value = Integer.parseInt(text);
                
                if (value > maxStock) {
                    spinner.setValue(maxStock);
                    Application.showMessage(Notifications.Type.WARNING, 
                        "Quantity adjusted to maximum available stock: " + maxStock);
                } else if (value < 1) {
                    spinner.setValue(1);
                    Application.showMessage(Notifications.Type.WARNING, 
                        "Quantity must be at least 1");
                }
            } catch (NumberFormatException e) {
                spinner.setValue(initialValue);
                Application.showMessage(Notifications.Type.WARNING, 
                    "Invalid quantity. Reset to " + initialValue);
            }
        }
        
        @Override
        public boolean stopCellEditing() {
            validateQuantity();
            return super.stopCellEditing();
        }
    }

    private void updateCartItemQuantity(int productId, int newQuantity) {
        try {
            int cartId = getCurrentCartID();
            Cart_Detail_DAO cdDao = new Cart_Detail_DAO();
            List<Cart_detail> allCartDetails = cdDao.selectAll();
            
            for (Cart_detail detail : allCartDetails) {
                if (detail.getCartID() == cartId && detail.getProductId() == productId) {
                    detail.setQuantity(newQuantity);
                    boolean updated = cdDao.update(detail);
                    
                    if (updated) {
                    } else {
                        Application.showMessage(Notifications.Type.ERROR, "Failed to update quantity");
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, "Failed to update quantity");
        }
    }
}
