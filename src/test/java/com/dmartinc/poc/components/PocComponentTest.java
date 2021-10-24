package com.dmartinc.poc.components;

import com.dmartinc.poc.repositories.PocStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PocComponentTest {

    private PocComponent pocComponent;

    @Mock
    private PocStore pocStore;

    @BeforeEach
    void setUp() {
        pocComponent = new PocComponent(pocStore);
    }

    @Test
    void shouldCountAtPocStoreWhenLogCount() {
        pocComponent.logCount();
        verify(pocStore, times(1)).count();
    }
}