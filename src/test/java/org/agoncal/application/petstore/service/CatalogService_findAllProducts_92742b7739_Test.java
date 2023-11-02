// Test generated by RoostGPT for test petstore using AI Type Open AI and AI Model gpt-4

package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatalogService_findAllProducts_92742b7739_Test {

    @InjectMocks
    CatalogService catalogService;

    @Mock
    EntityManager em;

    @Mock
    TypedQuery<Product> typedQuery;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> expectedProducts = Arrays.asList(product1, product2);

        when(em.createNamedQuery(Product.FIND_ALL, Product.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedProducts);

        List<Product> actualProducts = catalogService.findAllProducts();

        assertEquals(expectedProducts, actualProducts);
        verify(em, times(1)).createNamedQuery(Product.FIND_ALL, Product.class);
        verify(typedQuery, times(1)).getResultList();
    }

    @Test
    public void testFindAllProductsWithNoProducts() {
        when(em.createNamedQuery(Product.FIND_ALL, Product.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(null);

        List<Product> actualProducts = catalogService.findAllProducts();

        assertEquals(null, actualProducts);
        verify(em, times(1)).createNamedQuery(Product.FIND_ALL, Product.class);
        verify(typedQuery, times(1)).getResultList();
    }
}