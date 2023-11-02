// Test generated by RoostGPT for test petstore using AI Type Open AI and AI Model gpt-4

package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogService_findProduct_93acb8542e_Test {

    @InjectMocks
    CatalogService catalogService;

    @Mock
    EntityManager em;

    @Test
    public void testFindProductSuccess() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        when(em.find(Product.class, any(Long.class))).thenReturn(mockProduct);

        Product product = catalogService.findProduct(1L);

        assertNotNull(product);
        assertEquals(1L, product.getId());
    }

    @Test
    public void testFindProductFailure() {
        when(em.find(Product.class, any(Long.class))).thenReturn(null);

        Product product = catalogService.findProduct(1L);

        assertEquals(null, product);
    }
}