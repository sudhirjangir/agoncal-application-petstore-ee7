// Test generated by RoostGPT for test petstore using AI Type Open AI and AI Model gpt-4-1106-preview

package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.agoncal.application.petstore.model.Item;
import org.agoncal.application.petstore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CatalogService_createItem_b11efcae63_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateItem_WithNewProductAndCategory() {
        // Arrange
        Category newCategory = new Category();
        newCategory.setName("New Category");
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setCategory(newCategory);
        Item newItem = new Item();
        newItem.setName("New Item");
        newItem.setProduct(newProduct);

        // Act
        catalogService.createItem(newItem);

        // Assert
        verify(em, times(1)).persist(newCategory);
        verify(em, times(1)).persist(newProduct);
        verify(em, times(1)).persist(newItem);
    }

    @Test
    void testCreateItem_WithExistingProduct() {
        // Arrange
        Category existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Existing Category");
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Existing Product");
        existingProduct.setCategory(existingCategory);
        Item newItem = new Item();
        newItem.setName("New Item");
        newItem.setProduct(existingProduct);

        // Act
        catalogService.createItem(newItem);

        // Assert
        verify(em, never()).persist(existingCategory);
        verify(em, never()).persist(existingProduct);
        verify(em, times(1)).persist(newItem);
    }
}
