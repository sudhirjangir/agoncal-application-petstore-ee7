package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatalogService_findCategory_823393bbe0_Test {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Category> typedQuery;

    @InjectMocks
    private CatalogService catalogService;

    @BeforeEach
    public void setUp() {
        when(em.createNamedQuery(Category.FIND_BY_NAME, Category.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("pname"), anyString())).thenReturn(typedQuery);
    }

    @Test
    public void testFindCategory_WithValidName_ShouldReturnCategory() {
        Category expectedCategory = new Category();
        expectedCategory.setName("Fish");

        when(typedQuery.getSingleResult()).thenReturn(expectedCategory);

        Category actualCategory = catalogService.findCategory("Fish");

        assertNotNull(actualCategory);
        assertEquals(expectedCategory.getName(), actualCategory.getName());
    }

    @Test
    public void testFindCategory_WithInvalidName_ShouldReturnNull() {
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(NoResultException.class, () -> catalogService.findCategory("InvalidName"));
    }

    @Test
    public void testFindCategory_WithNullName_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> catalogService.findCategory(null));
    }

    @Test
    public void testFindCategory_WhenNoCategoryFound_ShouldThrowException() {
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(NoResultException.class, () -> catalogService.findCategory("NonExistent"));
    }
}
