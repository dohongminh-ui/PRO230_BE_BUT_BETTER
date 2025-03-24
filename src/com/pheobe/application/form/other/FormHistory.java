package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.model.Order;
import com.pheobe.model.Order_Detail;
import com.pheobe.model.Product;
import com.pheobe.service.Order_DAO;
import com.pheobe.service.Order_Detail_DAO;
import com.pheobe.service.Product_DAO;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pheobeo
 */
public class FormHistory extends javax.swing.JPanel {
    private DefaultTableModel historyTableModel;
    private Order_DAO orderDao;
    private Order_Detail_DAO orderDetailDao;
    private Product_DAO productDao;
    
    public FormHistory() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        tbtHistory.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        tbtHistory.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);
        
        tbtHistory.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);
        lb.setAlignmentX(CENTER_ALIGNMENT);

        orderDao = new Order_DAO();
        orderDetailDao = new Order_Detail_DAO();
        productDao = new Product_DAO();

        String[] columnNames = {"Order ID", "Product", "Date", "Quantity", "Price", "Total Price"};
        historyTableModel = new DefaultTableModel(columnNames, 0);
        tbtHistory.setModel(historyTableModel);

        loadOrderHistory();
    }
    
    private void loadOrderHistory() {
        historyTableModel.setRowCount(0);

        try {
            int userId = Application.getCurrentUser().getIdCustomer();
            List<Order> orders = orderDao.getOrdersByCustomerId(userId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            for (Order order : orders) {
                List<Order_Detail> orderDetails = orderDetailDao.getOrderDetailsByOrderId(order.getOrderID());

                for (Order_Detail detail : orderDetails) {
                    Product product = productDao.getProductById(detail.getProductID());
                    String productName = (product != null) ? product.getName() : "Product " + detail.getProductID();
                    String orderDate = (order.getCreateDate() != null) ? order.getCreateDate().format(formatter) : "N/A";
                    
                    historyTableModel.addRow(new Object[]{
                        order.getOrderID(),
                        productName,
                        orderDate,
                        detail.getQuantity(),
                        "$" + detail.getPrice(),
                        "$" + detail.getTotalMoney()
                    });
                }
            }

            if (historyTableModel.getRowCount() == 0) {
                lb.setText("No order history found");
            } else {
                lb.setText("HISTORY");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lb.setText("Error loading order history");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtHistory = new javax.swing.JTable();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("HISTORY");

        tbtHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Product", "Date", "Quantity", "Price", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(tbtHistory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbtHistory;
    // End of variables declaration//GEN-END:variables
}