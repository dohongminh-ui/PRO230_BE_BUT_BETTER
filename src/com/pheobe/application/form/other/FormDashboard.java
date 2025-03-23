package com.pheobe.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.pheobe.application.component.SearchComponent;
import com.pheobe.application.component.ProductCardComponent;
import com.pheobe.application.component.WrapLayout;
import com.pheobe.application.component.ProductDetailComponent;
import com.pheobe.model.Product;
import com.pheobe.model.Brand;
import com.pheobe.application.Application;
import static com.pheobe.application.Application.showMessage;
import com.pheobe.model.Category;
import com.pheobe.service.Product_DAO;
import com.pheobe.service.Brand_DAO;
import com.pheobe.service.Category_DAO;
import raven.toast.Notifications;
import com.pheobe.application.manager.BreadcrumbManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author pheoebe
 */
public class FormDashboard extends javax.swing.JPanel {

    private SearchComponent searchComponent;
    private JPanel productsPanel;
    private JScrollPane scrollPane;
    private Product_DAO product_DAO;
    private Brand_DAO brand_DAO;
    private Category_DAO category_DAO;
    private BreadcrumbManager breadcrumbManager;

    public FormDashboard() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "" + "font:$h1.font");
        
        product_DAO = new Product_DAO();
        brand_DAO = new Brand_DAO();
        category_DAO = new Category_DAO();
        
        breadcrumbManager = BreadcrumbManager.getInstance();
        breadcrumbManager.clear();
        breadcrumbManager.addBreadcrumb("Home", this);
        
        breadcrumbManager.getBreadcrumb().setBreadcrumbListener((item, index) -> {
            if (index == -1) {
                Application.showForm(this);
            } else if (item instanceof Component) {
                Application.showForm((Component) item);
            }
        });

        initSearchComponent();
        initProductPanel();
        loadProducts();
    }
    
    private void initSearchComponent() {
        searchComponent = new SearchComponent();
        
        searchComponent.addSearchListener(e -> {
            String searchText = searchComponent.getSearchText();
            performSearch(searchText);
        });
        
        searchComponent.addSearchTextChangeListener(text -> {
            updateSearchResults(text);
        });
    }

    private void initProductPanel() {
        productsPanel = new JPanel();
        productsPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 15, 15));

        scrollPane = new JScrollPane(productsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) getLayout();
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                    .addComponent(searchComponent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane))
                .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addGap(18, 18, 18)
                .addComponent(searchComponent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    private void loadProducts() {
        SwingWorker<List<Product>, Void> worker = new SwingWorker<List<Product>, Void>() {
            @Override
            protected List<Product> doInBackground() throws Exception {
                return product_DAO.getAllProducts();
            }

            @Override
            protected void done() {
                try {
                    List<Product> products = get();
                    displayProducts(products);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void displayProducts(List<Product> products) {
        productsPanel.removeAll();

        for (Product product : products) {
            if ("Active".equals(product.getStatus())) {
                Brand brand = brand_DAO.selectById(product.getBrandId());
                Category category = category_DAO.selectById(product.getCategoryId());

                if (brand != null && category != null) {
                    ProductCardComponent productCard = new ProductCardComponent(product, brand, category);
                    productCard.addAddToCartListener(e -> addToCart(product));

                    productCard.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            showProductDetail(product, brand, category);
                        }
                    });
                    productsPanel.add(productCard);
                }
            }
        }

        productsPanel.revalidate();
        productsPanel.repaint();
    }

    private void addToCart(Product product) {
        System.out.println("Adding product to cart: " + product.getName());
    }
    
    private void performSearch(String searchText) {
        List<Product> searchResults = product_DAO.searchProductsByName(searchText);
        System.out.println("Searching for: " + searchText);
        
        productsPanel.removeAll();
        
        if(searchResults.isEmpty()){
            showMessage(Notifications.Type.ERROR, "No results found");
        } else {
            for (Product product : searchResults) {
                Brand brand = brand_DAO.selectById(product.getBrandId());
                Category category = category_DAO.selectById(product.getCategoryId());
                
                if (brand != null && category != null) {
                    ProductCardComponent productCard = new ProductCardComponent(product, brand, category);
                    productCard.addAddToCartListener(e -> addToCart(product));
                    
                    productCard.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            showProductDetail(product, brand, category);
                        }
                    });
                    productsPanel.add(productCard);
                }
            }
        }
        
        productsPanel.revalidate();
        productsPanel.repaint();
    }
    
    private void updateSearchResults(String text) {
        if (text == null || text.trim().length() < 1) {
            if (text.isEmpty()) {
                loadProducts();
            }
            return;
        }
        List<Product> searchResults = product_DAO.searchProductsByName(text);
        
        productsPanel.removeAll();

        if (searchResults.isEmpty()) {
            showMessage(Notifications.Type.INFO, "No results found");
        } else {
            for (Product product : searchResults) {
                if ("Active".equals(product.getStatus())) {
                    Brand brand = brand_DAO.selectById(product.getBrandId());
                    Category category = category_DAO.selectById(product.getCategoryId());
                    
                    if (brand != null && category != null) {
                        ProductCardComponent productCard = new ProductCardComponent(product, brand, category);
                        productCard.addAddToCartListener(e -> addToCart(product));
                        
                        productCard.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                showProductDetail(product, brand, category);
                            }
                        });
                        productsPanel.add(productCard);
                    }
                }
            }
        }
        
        productsPanel.revalidate();
        productsPanel.repaint();
    }

    private void showProductDetail(Product product, Brand brand, Category category) {
        ProductDetailComponent detailPanel = new ProductDetailComponent(product, brand, category);
        
        breadcrumbManager.addBreadcrumb(product.getName(), detailPanel);
        
        detailPanel.addBackButtonListener(e -> {
            Application.showForm(this);
        });
        
        detailPanel.addAddToCartListener(e -> {
            int quantity = detailPanel.getQuantity();
            addToCart(product);
            Application.showMessage(Notifications.Type.SUCCESS, quantity + " item added to cart");
        });

        Application.showForm(detailPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Dashboard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addContainerGap(433, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
