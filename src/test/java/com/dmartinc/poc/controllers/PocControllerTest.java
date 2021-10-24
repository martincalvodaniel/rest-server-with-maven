package com.dmartinc.poc.controllers;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.services.PocService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.dmartinc.poc.utils.PocTestUtils.ID;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_2;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PocControllerTest {

    private PocController controller;

    @Mock
    private PocService service;

    @BeforeEach
    void setUp() {
        controller = new PocController(service);
    }

    @Test
    void shouldCreateAtServiceWhenCreate() {
        when(service.create((any()))).thenReturn(POC_MODEL);
        ResponseEntity<PocModel> responseEntity = controller.create(POC_MODEL_CONTENT);
        verify(service, times(1)).create((any()));
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertNotNull(responseEntity.getBody());
        assertEquals(POC_MODEL_CONTENT, responseEntity.getBody().getPocModelContent());
    }

    @Test
    void shouldReadAtServiceWhenRead() {
        when(service.read((any()))).thenReturn(Optional.of(POC_MODEL));
        ResponseEntity<PocModel> responseEntity = controller.read(String.valueOf(ID));
        verify(service, times(1)).read((any()));
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertNotNull(responseEntity.getBody());
        assertEquals(POC_MODEL_CONTENT, responseEntity.getBody().getPocModelContent());
    }

    @Test
    void shouldUpdateAtServiceWhenUpdate() {
        when(service.update(any(), any())).thenReturn(Optional.of(POC_MODEL_2));
        ResponseEntity<PocModel> responseEntity = controller.update(String.valueOf(ID), POC_MODEL_CONTENT_2);
        verify(service, times(1)).update(any(), any());
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertNotNull(responseEntity.getBody());
        assertEquals(POC_MODEL_CONTENT_2, responseEntity.getBody().getPocModelContent());
    }

    @Test
    void shouldReturnNotFoundWhenUpdateFails() {
        when(service.update(any(), any())).thenReturn(Optional.empty());
        ResponseEntity<PocModel> responseEntity = controller.update(String.valueOf(ID), POC_MODEL_CONTENT);
        verify(service, times(1)).update(any(), any());
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
        assertNull(responseEntity.getBody());
    }

    @Test
    void shouldDeleteAtServiceWhenDelete() {
        when(service.delete((any()))).thenReturn(Optional.of(POC_MODEL));
        ResponseEntity<PocModel> responseEntity = controller.delete(String.valueOf(ID));
        verify(service, times(1)).delete((any()));
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        assertNotNull(responseEntity.getBody());
        assertEquals(POC_MODEL_CONTENT, responseEntity.getBody().getPocModelContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeleteFails() {
        when(service.delete((any()))).thenReturn(Optional.empty());
        ResponseEntity<PocModel> responseEntity = controller.delete(String.valueOf(ID));
        verify(service, times(1)).delete((any()));
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
        assertNull(responseEntity.getBody());
    }
}