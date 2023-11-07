package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.agoncal.application.petstore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogService_findProducts_42b52d91b9_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Product> typedQuery;

    @BeforeEach
    void setUp() {
        // Initialize the CatalogService with mocked EntityManager
    }

    @Test
    public void testFindProducts_WithValidCategoryName_ShouldReturnProductList() {
        // Setup
        String categoryName = "Fish";
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product());

        when(em.createNamedQuery(Product.FIND_BY_CATEGORY_NAME, Product.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("pname", categoryName)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedProducts);

        // Execution
        List<Product> actualProducts = catalogService.findProducts(categoryName);

        // Assertion
        assertEquals(expectedProducts, actualProducts, "The expected products should match the actual products.");
        verify(typedQuery).setParameter("pname", categoryName);
        verify(typedQuery).getResultList();
    }

    @Test
    public void testFindProducts_WithNonExistentCategoryName_ShouldReturnEmptyList() {
        // Setup
        String categoryName = "NonExistentCategory";
        List<Product> expectedProducts = new ArrayList<>();

        when(em.createNamedQuery(Product.FIND_BY_CATEGORY_NAME, Product.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("pname", categoryName)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedProducts);

        // Execution
        List<Product> actualProducts = catalogService.findProducts(categoryName);

        // Assertion
        assertEquals(expectedProducts, actualProducts, "The expected empty product list should match the actual product list.");
        verify(typedQuery).setParameter("pname", categoryName);
        verify(typedQuery).getResultList();
    }
}
