package com.amihaeseisergiu.config;

import com.amihaeseisergiu.models.Categories;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class Config implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("categories", Categories.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP
    }

}
