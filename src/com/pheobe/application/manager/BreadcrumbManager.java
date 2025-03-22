package com.pheobe.application.manager;

import com.pheobe.application.component.BreadcrumbComponent;
import com.pheobe.application.Application;
import javax.swing.JPanel;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

public class BreadcrumbManager {
    private static BreadcrumbManager instance;
    private final BreadcrumbComponent breadcrumb;
    private final Map<Class<?>, Object> formDataMap;

    private BreadcrumbManager() {
        breadcrumb = new BreadcrumbComponent();
        formDataMap = new HashMap<>();

        breadcrumb.setBreadcrumbListener((data, index) -> {
            if (data != null && data instanceof Component) {
                Application.showForm((Component) data);
            }
        });
    }
    
    public static synchronized BreadcrumbManager getInstance() {
        if (instance == null) {
            instance = new BreadcrumbManager();
        }
        return instance;
    }

    public BreadcrumbComponent getBreadcrumb() {
        return breadcrumb;
    }

    public void addBreadcrumb(String name, Component formComponent) {
        breadcrumb.addBreadcrumb(name, formComponent);
        formDataMap.put(formComponent.getClass(), formComponent);
    }

    public void navigateToHome() {
        breadcrumb.navigateToHome();
    }

    public void navigateBack() {
        int size = breadcrumb.getBreadcrumbSize();
        if (size > 1) {
            breadcrumb.navigateTo(size - 2);
        } else {
            navigateToHome();
        }
    }

    public void clear() {
        breadcrumb.navigateToHome();
        formDataMap.clear();
    }
}