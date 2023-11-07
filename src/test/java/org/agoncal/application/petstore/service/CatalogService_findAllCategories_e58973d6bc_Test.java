package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CatalogService_findAllCategories_e58973d6bc_Test {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Category> query;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCategories_Success() {
        List<Category> expectedCategories = List.of(new Category(), new Category());
        when(em.createNamedQuery(Category.FIND_ALL, Category.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedCategories);

        List<Category> actualCategories = catalogService.findAllCategories();

        assertEquals(expectedCategories, actualCategories);
        verify(query).getResultList();
    }

    @Test
    void testFindAllCategories_NoResults() {
        List<Category> expectedCategories = Collections.emptyList();
        when(em.createNamedQuery(Category.FIND_ALL, Category.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedCategories);

        List<Category> actualCategories = catalogService.findAllCategories();

        assertEquals(expectedCategories, actualCategories);
        verify(query).getResultList();
    }
}
