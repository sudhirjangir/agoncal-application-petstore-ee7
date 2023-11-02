// Test generated by RoostGPT for test petstore using AI Type Open AI and AI Model gpt-4

package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CatalogService_removeCategory_7d83ff98e6_Test {

    private CatalogService catalogService;
    private EntityManager em;

    @BeforeEach
    public void setup() {
        em = mock(EntityManager.class);
        catalogService = new CatalogService();
        catalogService.em = em;
    }

    @Test
    public void removeCategoryTest_validCategoryId() {
        Long categoryId = new Random().nextLong();
        Category category = new Category();
        category.setId(categoryId);

        when(em.find(Category.class, categoryId)).thenReturn(category);

        catalogService.removeCategory(categoryId);

        verify(em, times(1)).find(Category.class, categoryId);
        verify(em, times(1)).remove(any(Category.class));
    }

    @Test
    public void removeCategoryTest_invalidCategoryId() {
        Long categoryId = new Random().nextLong();

        when(em.find(Category.class, categoryId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            catalogService.removeCategory(categoryId);
        });

        verify(em, times(1)).find(Category.class, categoryId);
        verify(em, times(0)).remove(any(Category.class));
    }
}
