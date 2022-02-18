package com.rochak.bankingapp.config;

import com.rochak.bankingapp.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedMethods = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};

        // Disable POST, PUT, DELETE, PATCH for Transfer
        disableHttpMethods(Transfer.class, config, theUnsupportedMethods);

        // Disable POST, PUT, DELETE, PATCH for Customer
        disableHttpMethods(Transfer.class, config, theUnsupportedMethods);

        exposeIds(config);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins("*");

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType<?> entityType : entityTypes) {
            entityClasses.add(entityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);

        config.exposeIdsFor(domainTypes);
    }
}
