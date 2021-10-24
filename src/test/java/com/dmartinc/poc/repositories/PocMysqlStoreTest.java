package com.dmartinc.poc.repositories;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.repositories.model.PocMysqlRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dmartinc.poc.utils.PocTestUtils.CONTENT;
import static com.dmartinc.poc.utils.PocTestUtils.ID;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PocMysqlStoreTest {

    private static final PocMysqlRow POC_MYSQL_ROW = aPocMysqlRow();

    private PocMysqlStore store;

    @Mock
    private PocMysqlRepository repository;

    @BeforeEach
    void setUp() {
        store = new PocMysqlStore(repository);
    }

    @Test
    void shouldSaveAtRepositoryWhenCreate() {
        when(repository.save((any()))).thenReturn(POC_MYSQL_ROW);
        PocModel pocModel = store.create(POC_MODEL_CONTENT);
        verify(repository, times(1)).save((any()));
        assertEquals(POC_MYSQL_ROW.getContent(), pocModel.getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAtRepositoryWhenRead() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MYSQL_ROW));
        Optional<PocModel> maybePocModel = store.read(String.valueOf(ID));
        verify(repository, times(1)).findById((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MYSQL_ROW.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAndSaveAtRepositoryWhenUpdate() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MYSQL_ROW));
        when(repository.save((any()))).thenReturn(POC_MYSQL_ROW);
        Optional<PocModel> maybePocModel = store.update(String.valueOf(ID), POC_MODEL_CONTENT);
        verify(repository, times(1)).findById((any()));
        verify(repository, times(1)).save((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MYSQL_ROW.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAndDeleteAtRepositoryWhenDelete() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MYSQL_ROW));
        Optional<PocModel> maybePocModel = store.delete(String.valueOf(ID));
        verify(repository, times(1)).findById((any()));
        verify(repository, times(1)).delete((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MYSQL_ROW.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldCountAtRepositoryWhenCount() {
        when(repository.count()).thenReturn(ID);
        long count = store.count();
        verify(repository, times(1)).count();
        assertEquals(count, ID);
    }

    private static PocMysqlRow aPocMysqlRow() {
        PocMysqlRow ret = new PocMysqlRow();
        ret.setId(ID);
        ret.setContent(CONTENT);
        return ret;
    }
}