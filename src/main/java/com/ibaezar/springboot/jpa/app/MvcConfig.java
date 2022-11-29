package com.ibaezar.springboot.jpa.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addResourceHandlers(registry);

        //TODO -> confifurar directorio externo (Esta clase config y en el controlador).
        registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:/C:/java/temp/uploads/");

        //? Para el caso de una ruta en linux sería
        /*
        registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:/opt/java/temp/uploads/");
         */
    }

    //TODO -> Metodo viewController para personalizar vista de error Acceso denegado de spring security
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/error_403").setViewName("error_403");
    }
}
