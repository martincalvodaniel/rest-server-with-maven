package com.dmartinc.poc.services;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.repositories.PocMongoStore;
import com.dmartinc.poc.repositories.PocStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dmartinc.poc.utils.PocTestUtils.ID;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_2;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT_2;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PocServiceTest {

    private PocService service;

    @Mock
    private PocStore store;

    @BeforeEach
    void setUp() {
        service = new PocService(store);
    }

    @Test
    void create() {
        when(store.create((any()))).thenReturn(POC_MODEL);
        PocModel pocModel = service.create(POC_MODEL_CONTENT);
        verify(store, times(1)).create((any()));
        assertEquals(POC_MODEL.getPocModelContent(), pocModel.getPocModelContent());
    }

    @Test
    void read() {
        when(store.read((any()))).thenReturn(Optional.of(POC_MODEL));
        Optional<PocModel> maybePocModel = service.read(String.valueOf(ID));
        verify(store, times(1)).read((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MODEL.getPocModelContent(), maybePocModel.get().getPocModelContent());
    }

    @Test
    void update() {
        when(store.update(any(), any())).thenReturn(Optional.of(POC_MODEL_2));
        Optional<PocModel> maybePocModel = service.update(String.valueOf(ID), POC_MODEL_CONTENT_2);
        verify(store, times(1)).update(any(), any());
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MODEL_2.getPocModelContent(), maybePocModel.get().getPocModelContent());
    }

    @Test
    void delete() {
        when(store.delete(any())).thenReturn(Optional.of(POC_MODEL));
        Optional<PocModel> maybePocModel = service.delete(String.valueOf(ID));
        verify(store, times(1)).delete((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MODEL.getPocModelContent(), maybePocModel.get().getPocModelContent());
    }
}