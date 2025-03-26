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
import com.pheobe.model.Cart;
import com.pheobe.service.Cart_DAO;
import com.pheobe.model.Cart_detail;
import com.pheobe.service.Cart_Detail_DAO;
import com.pheobe.application.component.NoProductsPanel;
import javax.swing.SwingUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

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
    private int filterCategory = 0;
    private int filterBrand = 0;

    public FormDashboard() {
        this(0, 0);
    }

    public FormDashboard(int categoryFilter) {
        this(categoryFilter, 0);
    }

    public FormDashboard(int categoryFilter, int brandFilter) {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, "" + "font:$h1.font");
        
        product_DAO = new Product_DAO();
        brand_DAO = new Brand_DAO();
        category_DAO = new Category_DAO();
        
        this.filterCategory = categoryFilter;
        this.filterBrand = brandFilter;
        
        breadcrumbManager = BreadcrumbManager.getInstance();
        breadcrumbManager.clear();
        breadcrumbManager.addBreadcrumb("Home", this);

        if (filterCategory > 0) {
            Category category = category_DAO.selectById(filterCategory);
            if (category != null) {
                lb.setText("Products - " + category.getName());
                breadcrumbManager.addBreadcrumb(category.getName(), this);
            }
        }
        
        if (filterBrand > 0) {
            Brand brand = brand_DAO.selectById(filterBrand);
            if (brand != null) {
                brand.setSearchCount(brand.getSearchCount() + 1);
                brand.setUpdateDate(java.time.LocalDateTime.now());
                brand_DAO.update(brand);
                
                String currentTitle = lb.getText();
                if (currentTitle.equals("Home")) {
                    lb.setText("Products - " + brand.getName());
                } else {
                    lb.setText(currentTitle + " - " + brand.getName());
                }
                breadcrumbManager.addBreadcrumb(brand.getName(), this);
                
                updateMenuSelectionForBrand(brand);
            }
        }
        
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
                List<Product> products;
                
                if (filterBrand > 0 && filterCategory <= 0) {
                    products = product_DAO.getProductsByBrandId(filterBrand);
                } else if (filterCategory > 0) {
                    products = product_DAO.getProductsByCategoryId(filterCategory);
                    
                    if (filterBrand > 0) {
                        products = products.stream()
                            .filter(p -> p.getBrandId() == filterBrand)
                            .collect(Collectors.toList());
                    }
                } else {
                    products = product_DAO.getAllProducts();
                }
                
                return products;
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
        
        List<Product> filteredProducts = products.stream()
            .filter(p -> "Active".equals(p.getStatus()))
            .filter(p -> filterCategory <= 0 || p.getCategoryId() == filterCategory)
            .filter(p -> filterBrand <= 0 || p.getBrandId() == filterBrand)
            .collect(Collectors.toList());
        
        if (filteredProducts.isEmpty()) {
            String message = "No products available in this category";
            if (searchComponent.getSearchText() != null && !searchComponent.getSearchText().isEmpty()) {
                message = "No results found for \"" + searchComponent.getSearchText() + "\"";
            }
            
            NoProductsPanel noProductsPanel = new NoProductsPanel(message);
            productsPanel.add(noProductsPanel);
        } else {
            for (Product product : filteredProducts) {
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
        try {
            if (Application.getCurrentUser() == null) {
                showMessage(Notifications.Type.WARNING, "Please login to add items to cart");
                return;
            }

            if (product.getStock() <= 0) {
                showMessage(Notifications.Type.WARNING, "\"" + product.getName() + "\" is out of stock");
                return;
            }

            int customerId = Application.getCurrentUser().getIdCustomer();
            
            Cart_DAO cartDao = new Cart_DAO();
            List<Cart> carts = cartDao.selectAll();

            Cart activeCart = null;
            for (Cart c : carts) {
                if (c.getCustomerId() == customerId && "Active".equalsIgnoreCase(c.getStatus())) {
                    activeCart = c;
                    break;
                }
            }

            if (activeCart == null) {
                Cart newCart = new Cart();
                newCart.setCustomerId(customerId);
                newCart.setCartID("CART-" + customerId + "-" + System.currentTimeMillis());
                newCart.setStatus("Active");
                newCart.setCreateDate(java.time.LocalDateTime.now());

                boolean created = cartDao.insert(newCart);
                if (!created) {
                    showMessage(Notifications.Type.ERROR, "Failed to create cart");
                    return;
                }

                carts = cartDao.selectAll();
                for (Cart c : carts) {
                    if (c.getCustomerId() == customerId && "Active".equalsIgnoreCase(c.getStatus())) {
                        activeCart = c;
                        break;
                    }
                }

                if (activeCart == null) {
                    showMessage(Notifications.Type.ERROR, "Failed to retrieve cart");
                    return;
                }
            }

            Cart_Detail_DAO cartDetailDao = new Cart_Detail_DAO();
            List<Cart_detail> cartDetails = cartDetailDao.selectAll();

            boolean productFound = false;
            for (Cart_detail detail : cartDetails) {
                if (detail.getCartID() == activeCart.getId() && 
                    detail.getProductId() == product.getIdProduct() && 
                    "Active".equals(detail.getStatus())) {

                    if (detail.getQuantity() + 1 > product.getStock()) {
                        showMessage(Notifications.Type.WARNING, 
                            "Cannot add more \"" + product.getName() + "\". Maximum stock reached (" + product.getStock() + ")");
                        return;
                    }

                    detail.setQuantity(detail.getQuantity() + 1);
                    boolean updated = cartDetailDao.update(detail);

                    if (updated) {
                        showMessage(Notifications.Type.SUCCESS, "Added one more \"" + product.getName() + "\" to cart");
                    } else {
                        showMessage(Notifications.Type.ERROR, "Failed to update \"" + product.getName() + "\" in cart");
                    }

                    productFound = true;
                    break;
                }
            }

            if (!productFound) {
                Cart_detail newDetail = new Cart_detail();
                newDetail.setCartID(activeCart.getId());
                newDetail.setProductId(product.getIdProduct());
                newDetail.setQuantity(1);
                newDetail.setPrice(product.getPrice());
                newDetail.setStatus("Active");
                newDetail.setCreateDate(java.time.LocalDateTime.now());

                boolean success = cartDetailDao.insert(newDetail);
                if (success) {
                    showMessage(Notifications.Type.SUCCESS, "\"" + product.getName() + "\" added to cart");
                } else {
                    showMessage(Notifications.Type.ERROR, "Failed to add \"" + product.getName() + "\" to cart");
                }
            }

            refreshCartIfVisible();

        } catch (Exception e) {
            e.printStackTrace();
            showMessage(Notifications.Type.ERROR, "Error adding \"" + product.getName() + "\" to cart: " + e.getMessage());
        }
    }
    
    private void refreshCartIfVisible() {
        SwingUtilities.invokeLater(() -> {
            for (Window window : Window.getWindows()) {
                if (window instanceof JFrame) {
                    JFrame frame = (JFrame) window;
                    for (Component comp : frame.getContentPane().getComponents()) {
                        if (comp instanceof FormCart1) {
                            FormCart1 cartForm = (FormCart1) comp;
                            cartForm.refreshCart();
                            break;
                        }
                    }
                }
            }
        });
    }
    
    private void performSearch(String searchText) {
        List<Product> searchResults = product_DAO.searchProductsByName(searchText);
        System.out.println("Searching for: " + searchText);
        
        productsPanel.removeAll();
        
        if(searchResults.isEmpty()){
            NoProductsPanel noProductsPanel = new NoProductsPanel("No results found for \"" + searchText + "\"");
            productsPanel.add(noProductsPanel);
        } else {
            boolean anyProductsAdded = false;
            
            for (Product product : searchResults) {
                if ((filterCategory > 0 && product.getCategoryId() != filterCategory) ||
                    (filterBrand > 0 && product.getBrandId() != filterBrand)) {
                    continue;
                }
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
                    anyProductsAdded = true;
                }
            }
            
            if (!anyProductsAdded) {
                String filterText = "";
                if (filterCategory > 0 && filterBrand > 0) {
                    filterText = " in this category and brand";
                } else if (filterCategory > 0) {
                    filterText = " in this category";
                } else if (filterBrand > 0) {
                    filterText = " for this brand";
                }
                NoProductsPanel noProductsPanel = new NoProductsPanel("No results found for \"" + searchText + "\"" + filterText);
                productsPanel.add(noProductsPanel);
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
            NoProductsPanel noProductsPanel = new NoProductsPanel("No results found for \"" + text + "\"");
            productsPanel.add(noProductsPanel);
        } else {
            boolean anyProductsAdded = false;
            
            for (Product product : searchResults) {
                if ("Active".equals(product.getStatus())) {
                    if ((filterCategory > 0 && product.getCategoryId() != filterCategory) ||
                        (filterBrand > 0 && product.getBrandId() != filterBrand)) {
                        continue;
                    }
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
                        anyProductsAdded = true;
                    }
                }
            }
            
            if (!anyProductsAdded) {
                String filterText = "";
                if (filterCategory > 0 && filterBrand > 0) {
                    filterText = " in this category and brand";
                } else if (filterCategory > 0) {
                    filterText = " in this category";
                } else if (filterBrand > 0) {
                    filterText = " for this brand";
                }
                NoProductsPanel noProductsPanel = new NoProductsPanel("No results found for \"" + text + "\"" + filterText);
                productsPanel.add(noProductsPanel);
            }
        }
        
        productsPanel.revalidate();
        productsPanel.repaint();
    }

    private void showProductDetail(Product product, Brand brand, Category category) {
        ProductDetailComponent detailPanel = new ProductDetailComponent(product, brand, category);
        
        detailPanel.setupBreadcrumb("Home", this);
        
        detailPanel.addBackButtonListener(e -> {
            Application.showForm(this);
        });

        Application.showForm(detailPanel);
    }

    private void updateMenuSelectionForBrand(Brand brand) {
        SwingUtilities.invokeLater(() -> {
            try {
                Application.getMainForm().setIgnoreNextMenuEvent(true);
                
                Brand_DAO brandDAO = new Brand_DAO();
                ArrayList<Brand> brands = brandDAO.selectAll();
                
                int subIndex = -1;
                for (int i = 0; i < brands.size(); i++) {
                    if (brands.get(i).getBrandId() == brand.getBrandId()) {
                        subIndex = i + 1;
                        break;
                    }
                }
                
                if (subIndex > 0) {
                    Application.getMainForm().setSelectedMenu(2, subIndex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Home");

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
