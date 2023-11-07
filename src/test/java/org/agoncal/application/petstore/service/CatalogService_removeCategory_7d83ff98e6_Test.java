package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogService_removeCategory_7d83ff98e6_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    private Category existingCategory;
    private Category nonExistingCategory;

    @BeforeEach
    public void setUp() {
        // Existing category
        existingCategory = new Category();
        existingCategory.setId(1L);
        existingCategory.setName("Existing Category");
        existingCategory.setDescription("Description of Existing Category");

        // Non-existing category (simulated)
        nonExistingCategory = new Category();
        nonExistingCategory.setId(999L);
        nonExistingCategory.setName("Non-existing Category");
        nonExistingCategory.setDescription("Description of Non-existing Category");

        // Mocking EntityManager to return existing category
        when(em.find(Category.class, existingCategory.getId())).thenReturn(existingCategory);
        when(em.find(Category.class, nonExistingCategory.getId())).thenReturn(null);
    }

    @Test
    public void testRemoveCategory_Success() {
        catalogService.removeCategory(existingCategory.getId());
        verify(em, times(1)).remove(any(Category.class));
    }

    @Test
    public void testRemoveCategory_Failure() {
        Long nonExistingCategoryId = nonExistingCategory.getId();
        catalogService.removeCategory(nonExistingCategoryId);
        verify(em, never()).remove(any(Category.class));
    }
}
