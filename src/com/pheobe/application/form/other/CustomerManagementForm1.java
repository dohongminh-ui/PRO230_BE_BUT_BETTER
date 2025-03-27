package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.Application;
import com.pheobe.model.Cart;
import com.pheobe.model.Customer;
import com.pheobe.service.Cart_DAO;
import com.pheobe.service.Customer_DAO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;
import com.pheobe.connection.DBcontext;
import java.util.ArrayList;

/**
 *
 * @author pheoebe
 */
public class CustomerManagementForm1 extends javax.swing.JPanel {
    private DefaultTableModel dtm = new DefaultTableModel();
    private Customer_DAO servieCustomerDao = new Customer_DAO();
    private List<Customer> CustomerList = new ArrayList<>();
    public CustomerManagementForm1() {
        initComponents();
        tbtCusomerManage.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

                tbtCusomerManage.putClientProperty(FlatClientProperties.STYLE, """
                showHorizontalLines: false; 
                showVerticalLines: false;
                rowHeight: 25
                """);

                tbtCusomerManage.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.putClientProperty(FlatClientProperties.STYLE, """
                border: 0,0,0,0
                """);
        dtm = (DefaultTableModel) tbtCusomerManage.getModel();
        tbtCusomerManage.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tbtCusomerManage.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor());
        loadTable(servieCustomerDao.queryToSelectAllbyAdmin());
        
        // Add action listener to search button
        btnSearchCustomer.addActionListener(e -> searchCustomers());
        
        // Add action listener to status combo box
        cbStatus.addActionListener(e -> searchCustomers());
        
        // Add key listener to search field for real-time searching (optional)
        txtSearchCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    searchCustomers();
                }
            }
        });
    }   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearchCustomer = new javax.swing.JTextField();
        btnSearchCustomer = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbtCusomerManage = new javax.swing.JTable();
        cbStatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        btnSearchCustomer.setText("Search");

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Customer");

        tbtCusomerManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", " Name", "Created Date", "Address", "Status", "Action"
            }
        ));
        jScrollPane1.setViewportView(tbtCusomerManage);

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Offline", "Active" }));

        jLabel2.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSearchCustomer)))
                        .addGap(0, 323, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchCustomer))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchCustomer;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tbtCusomerManage;
    private javax.swing.JTextField txtSearchCustomer;
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
        private int customerId;
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
            
            customerId = (int) value;
            button.setText("Remove");
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                removeUser(customerId);
            }
            isPushed = false;
            return customerId;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }
    }
    
    private void loadTable(List<Customer> customer){
        dtm.setRowCount(0);
        Customer_DAO customerDao = new Customer_DAO();
        Cart_DAO cartDao = new Cart_DAO();
        for (Customer c : customer) {
            System.out.println("" + c.isAdmin());
            if(c.isAdmin()){
              continue;
            }
            Cart cartId = cartDao.selectByIdCusomer(c.getIdCustomer());
            Customer customerId = customerDao.selectById(c.getIdCustomer());
            int customerIdN = customerId.getIdCustomer();
            dtm.addRow(new Object[]{
                c.getIdCustomer(), c.getName(), c.getCreateDate(), c.getAddress(), c.getStatus(), customerIdN
            });
            System.out.println("" + c.isAdmin());
        }
    }
    
    private void removeUser(int customerId) {
        try {
            Connection con = DBcontext.getConnection();
            CallableStatement cstmt = con.prepareCall("{call DeleteCustomer(?)}");
            cstmt.setInt(1, customerId);
            
            ResultSet rs = cstmt.executeQuery();
            if (rs.next() && rs.getInt("Success") == 1) {
                Application.showMessage(Notifications.Type.INFO, "User removed successfully");
                List<Customer> listUpdate = servieCustomerDao.queryToSelectAllbyAdmin();
                loadTable(listUpdate);
            } else {
                String errorMsg = rs.getString("ErrorMessage");
                Application.showMessage(Notifications.Type.ERROR, "Failed to remove user: " + errorMsg);
            }
            
            rs.close();
            cstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            Application.showMessage(Notifications.Type.ERROR, "Error removing user: " + e.getMessage());
        }
    }

    /**
     * Search customers based on name and status filter
     */
    private void searchCustomers() {
        String searchText = txtSearchCustomer.getText().trim();
        String status = cbStatus.getSelectedItem().toString();
        
        List<Customer> filteredCustomers = new ArrayList<>();
        List<Customer> allCustomers = servieCustomerDao.queryToSelectAllbyAdmin();
        
        for (Customer customer : allCustomers) {
            // Skip admin users as in loadTable method
            if (customer.isAdmin()) {
                continue;
            }
            
            boolean matchesSearch = searchText.isEmpty() || 
                                    customer.getName().toLowerCase().contains(searchText.toLowerCase());
            boolean matchesStatus = status.equals("Active") ? 
                                   "Active".equals(customer.getStatus()) : 
                                   "Offline".equals(customer.getStatus());
            
            if (matchesSearch && matchesStatus) {
                filteredCustomers.add(customer);
            }
        }
        
        // Update table with filtered results
        loadTable(filteredCustomers);
    }
}
