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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.WindowConstants;
import raven.toast.Notifications;
import java.util.ArrayList;

/**
 *
 * @author pheobeo
 */
public class FormHistory extends javax.swing.JPanel {
    private DefaultTableModel historyTableModel;
    private Order_DAO orderDao;
    private Order_Detail_DAO orderDetailDao;
    private Product_DAO productDao;
    private Map<Integer, Boolean> expandedOrders = new HashMap<>();
    
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

        String[] columnNames = {"Order ID", "Date", "Total Items", "Total Price", "Action"};
        historyTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        tbtHistory.setModel(historyTableModel);

        tbtHistory.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tbtHistory.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor());
        
        tbtHistory.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(c.getFont().deriveFont(Font.BOLD));
                return c;
            }
        });

        loadOrderHistory();
    }
    
    private void loadOrderHistory() {
        historyTableModel.setRowCount(0);
        expandedOrders.clear();

        try {
            int userId = Application.getCurrentUser().getIdCustomer();
            List<Order> orders = orderDao.getOrdersByCustomerId(userId);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            
            Map<Integer, OrderSummary> orderSummaries = new HashMap<>();
            
            for (Order order : orders) {
                int orderId = order.getOrderID();
                String orderDate = (order.getCreateDate() != null) ? order.getCreateDate().format(formatter) : "N/A";
                
                List<Order_Detail> orderDetails = orderDetailDao.getOrderDetailsByOrderId(orderId);
                
                int totalItems = 0;
                BigDecimal totalPrice = BigDecimal.ZERO;
                
                for (Order_Detail detail : orderDetails) {
                    totalItems += detail.getQuantity();
                    totalPrice = totalPrice.add(detail.getTotalMoney());
                }
                
                OrderSummary summary = new OrderSummary(orderId, orderDate, totalItems, totalPrice, orderDetails);
                orderSummaries.put(orderId, summary);
            }
            
            List<OrderSummary> sortedSummaries = new ArrayList<>(orderSummaries.values());
            sortedSummaries.sort((a, b) -> {
                return b.getOrderDate().compareTo(a.getOrderDate());
            });
            
            for (OrderSummary summary : sortedSummaries) {
                historyTableModel.addRow(new Object[]{
                    summary.getOrderId(),
                    summary.getOrderDate(),
                    summary.getTotalItems(),
                    "$" + summary.getTotalPrice(),
                    summary
                });
            }

            if (historyTableModel.getRowCount() == 0) {
                lb.setText("No order history found");
            } else {
                lb.setText("History");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lb.setText("Error loading order history");
        }
    }
    
    private void showOrderDetails(OrderSummary summary) {
        try {
            DetailsDialog dialog = new DetailsDialog(SwingUtilities.getWindowAncestor(this), true, summary, productDao);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, "Error showing order details");
        }
    }
    
    private class OrderSummary {
        private int orderId;
        private String orderDate;
        private int totalItems;
        private BigDecimal totalPrice;
        private List<Order_Detail> details;
        
        public OrderSummary(int orderId, String orderDate, int totalItems, BigDecimal totalPrice, List<Order_Detail> details) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.totalItems = totalItems;
            this.totalPrice = totalPrice;
            this.details = details;
        }
        
        public int getOrderId() { return orderId; }
        public String getOrderDate() { return orderDate; }
        public int getTotalItems() { return totalItems; }
        public BigDecimal getTotalPrice() { return totalPrice; }
        public List<Order_Detail> getDetails() { return details; }
    }
    
    private class DetailsDialog extends JDialog {
        private JTable detailsTable;
        
        public DetailsDialog(java.awt.Window parent, boolean modal, OrderSummary summary, Product_DAO productDao) {
            super();
            setModal(modal);
            initializeUI(summary, productDao);
        }
        
        private void initializeUI(OrderSummary summary, Product_DAO productDao) {
            setTitle("Order #" + summary.getOrderId() + " Details");
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setSize(new Dimension(600, 400));
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
            
            JLabel orderIdLabel = new JLabel("Order #" + summary.getOrderId());
            orderIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            orderIdLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            JLabel dateLabel = new JLabel("Date: " + summary.getOrderDate());
            dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            dateLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            headerPanel.add(orderIdLabel);
            headerPanel.add(Box.createVerticalStrut(5));
            headerPanel.add(dateLabel);
            headerPanel.add(Box.createVerticalStrut(15));
            
            String[] columnNames = {"Product", "Quantity", "Price", "Total"};
            DefaultTableModel detailsModel = new DefaultTableModel(columnNames, 0);
            
            for (Order_Detail detail : summary.getDetails()) {
                Product product = productDao.getProductById(detail.getProductID());
                String productName = (product != null) ? product.getName() : "Product " + detail.getProductID();
                
                detailsModel.addRow(new Object[]{
                    productName,
                    detail.getQuantity(),
                    "$" + detail.getPrice(),
                    "$" + detail.getTotalMoney()
                });
            }
            
            detailsTable = new JTable(detailsModel);
            detailsTable.setRowHeight(25);
            detailsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            
            JScrollPane scrollPane = new JScrollPane(detailsTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            
            JPanel footerPanel = new JPanel(new BorderLayout());
            JLabel totalLabel = new JLabel("Total: $" + summary.getTotalPrice());
            totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            totalLabel.setHorizontalAlignment(JLabel.RIGHT);
            footerPanel.add(totalLabel, BorderLayout.EAST);
            
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            footerPanel.add(closeButton, BorderLayout.WEST);
            
            mainPanel.add(headerPanel, BorderLayout.NORTH);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(footerPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("View Details");
            return this;
        }
    }
    
    class ButtonEditor extends javax.swing.DefaultCellEditor {
        private JButton button;
        private OrderSummary summary;
        private boolean isPushed;
        
        public ButtonEditor() {
            super(new javax.swing.JCheckBox());
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
            if (value instanceof OrderSummary) {
                summary = (OrderSummary) value;
            }
            button.setText("View Details");
            isPushed = true;
            return button;
        }
        
        @Override
        public Object getCellEditorValue() {
            if (isPushed && summary != null) {
                showOrderDetails(summary);
            }
            isPushed = false;
            return summary;
        }
        
        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Date", "Total Items", "Total Price", "Action"
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