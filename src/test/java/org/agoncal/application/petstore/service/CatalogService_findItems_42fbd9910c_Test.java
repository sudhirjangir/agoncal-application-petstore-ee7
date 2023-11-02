// Test generated by RoostGPT for test petstore using AI Type Open AI and AI Model gpt-4

package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.model.Item;
import org.agoncal.application.petstore.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CatalogService_findItems_42fbd9910c_Test {

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<Item> typedQuery;

    private CatalogService catalogService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        catalogService = new CatalogService();
        catalogService.em = em;
    }

    @Test
    public void testFindItems_Success() {
        Long productId = 1L;
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());

        when(em.createNamedQuery(Item.FIND_BY_PRODUCT_ID, Item.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expectedItems);

        List<Item> actualItems = catalogService.findItems(productId);

        verify(em, times(1)).createNamedQuery(Item.FIND_BY_PRODUCT_ID, Item.class);
        verify(typedQuery, times(1)).setParameter("productId", productId);
        verify(typedQuery, times(1)).getResultList();

        assertSame(expectedItems, actualItems);
    }

    @Test
    public void testFindItems_NoItemsFound() {
        Long productId = 1L;

        when(em.createNamedQuery(Item.FIND_BY_PRODUCT_ID, Item.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(anyString(), any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(null);

        List<Item> actualItems = catalogService.findItems(productId);

        verify(em, times(1)).createNamedQuery(Item.FIND_BY_PRODUCT_ID, Item.class);
        verify(typedQuery, times(1)).setParameter("productId", productId);
        verify(typedQuery, times(1)).getResultList();

        assertNull(actualItems);
    }
}
