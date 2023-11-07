package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CatalogService_createCategory_eedbe5c31d_Test {

    @Mock
    private EntityManager em;

    @InjectMocks
    private CatalogService catalogService;

    private Category validCategory;
    private Category invalidCategory;

    @BeforeEach
    public void setUp() {
        validCategory = new Category();
        validCategory.setName("Feline");
        validCategory.setDescription("Cat category");

        invalidCategory = new Category();
        // Set up an invalid category if needed for testing constraints violations
    }

    @Test
    public void testCreateCategoryWithValidCategory() {
        when(em.merge(any(Category.class))).thenReturn(validCategory);

        Category createdCategory = catalogService.createCategory(validCategory);

        assertNotNull(createdCategory);
        assertEquals(validCategory.getName(), createdCategory.getName());
        assertEquals(validCategory.getDescription(), createdCategory.getDescription());
        verify(em, times(1)).persist(any(Category.class));
    }

    @Test
    public void testCreateCategoryWithNullCategory() {
        assertThrows(ConstraintViolationException.class, () -> {
            catalogService.createCategory(null);
        });
    }

    // Additional test cases can be added here to cover more scenarios
}
