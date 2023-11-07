package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogService_removeCategory_d8859f27bd_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("TestCategory");
        category.setDescription("TestDescription");
    }

    @Test
    public void testRemoveCategory_Successful() {
        when(em.merge(any(Category.class))).thenReturn(category);
        doNothing().when(em).remove(any(Category.class));

        catalogService.removeCategory(category);

        verify(em, times(1)).merge(category);
        verify(em, times(1)).remove(category);
    }

    @Test
    public void testRemoveCategory_EntityNotFound() {
        when(em.merge(any(Category.class))).thenReturn(category);
        doThrow(EntityNotFoundException.class).when(em).remove(any(Category.class));

        assertThrows(EntityNotFoundException.class, () -> catalogService.removeCategory(category));
        verify(em, times(1)).merge(category);
        verify(em, times(1)).remove(category);
    }
}
