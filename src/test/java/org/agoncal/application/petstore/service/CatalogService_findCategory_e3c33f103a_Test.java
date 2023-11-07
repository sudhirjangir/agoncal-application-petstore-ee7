package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CatalogService_findCategory_e3c33f103a_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCategory_ExistingId() {
        // Arrange
        Long categoryId = 1L;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        when(em.find(Category.class, categoryId)).thenReturn(expectedCategory);

        // Act
        Category result = catalogService.findCategory(categoryId);

        // Assert
        assertNotNull(result, "The result should not be null.");
        assertEquals(expectedCategory.getId(), result.getId(), "The category ID should match the expected value.");
    }

    @Test
    public void testFindCategory_NonExistingId() {
        // Arrange
        Long categoryId = 999L;
        when(em.find(Category.class, categoryId)).thenReturn(null);

        // Act
        Category result = catalogService.findCategory(categoryId);

        // Assert
        assertNull(result, "The result should be null for a non-existing category ID.");
    }

    @Test
    public void testFindCategory_NullId() {
        // Arrange
        Long categoryId = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> catalogService.findCategory(categoryId), "Should throw an exception for null category ID.");
    }
}
