package org.lanqiao.phone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BackstageConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/Bhome.html").setViewName("Bhome");
        registry.addViewController( "/BShops_Audit.html" ).setViewName( "BShops_Audit" );
        registry.addViewController( "Bshopping_detailed.html" ).setViewName( "Bshopping_detailed" );
        registry.addViewController( "BFeedback.html" ).setViewName( "BFeedback" );
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
    }
}
