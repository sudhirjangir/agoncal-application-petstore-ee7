package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogService_updateCategory_f8f422915e_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    private Category existingCategory;
    private Category updatedCategory;

    @BeforeEach
    public void setUp() {
        existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Existing Category");
        existingCategory.setDescription("This is an existing category");

        updatedCategory = new Category();
        updatedCategory.setId(1L);
        updatedCategory.setName("Updated Category");
        updatedCategory.setDescription("This is the updated category description");
    }

    @Test
    public void testUpdateCategorySuccess() {
        when(em.merge(existingCategory)).thenReturn(updatedCategory);

        Category result = catalogService.updateCategory(existingCategory);

        assertEquals(updatedCategory.getName(), result.getName());
        assertEquals(updatedCategory.getDescription(), result.getDescription());
    }

    @Test
    public void testUpdateCategoryWithNullCategory() {
        assertThrows(NullPointerException.class, () -> catalogService.updateCategory(null));
    }
}
