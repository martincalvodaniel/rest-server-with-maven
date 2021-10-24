package com.dmartinc.poc.repositories;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.repositories.model.PocMongoDocument;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.dmartinc.poc.utils.PocTestUtils.CONTENT;
import static com.dmartinc.poc.utils.PocTestUtils.CONTENT_2;
import static com.dmartinc.poc.utils.PocTestUtils.ID;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT;
import static com.dmartinc.poc.utils.PocTestUtils.POC_MODEL_CONTENT_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PocMongoStoreTest {

    private static final PocMongoDocument POC_MONGO_DOCUMENT = aPocMongoDocument(CONTENT);
    private static final PocMongoDocument POC_MONGO_DOCUMENT_2 = aPocMongoDocument(CONTENT_2);

    private PocMongoStore store;

    @Mock
    private PocMongoRepository repository;

    @BeforeEach
    void setUp() {
        store = new PocMongoStore(repository);
    }

    @Test
    void shouldSaveAtRepositoryWhenCreate() {
        when(repository.save((any()))).thenReturn(POC_MONGO_DOCUMENT);
        PocModel pocModel = store.create(POC_MODEL_CONTENT);
        verify(repository, times(1)).save((any()));
        assertEquals(POC_MONGO_DOCUMENT.getContent(), pocModel.getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAtRepositoryWhenRead() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MONGO_DOCUMENT));
        Optional<PocModel> maybePocModel = store.read(String.valueOf(ID));
        verify(repository, times(1)).findById((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MONGO_DOCUMENT.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAndSaveAtRepositoryWhenUpdate() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MONGO_DOCUMENT_2));
        when(repository.save((any()))).thenReturn(POC_MONGO_DOCUMENT_2);
        Optional<PocModel> maybePocModel = store.update(String.valueOf(ID), POC_MODEL_CONTENT_2);
        verify(repository, times(1)).findById((any()));
        verify(repository, times(1)).save((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MONGO_DOCUMENT_2.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldFindByIdAndDeleteAtRepositoryWhenDelete() {
        when(repository.findById((any()))).thenReturn(Optional.of(POC_MONGO_DOCUMENT));
        Optional<PocModel> maybePocModel = store.delete(String.valueOf(ID));
        verify(repository, times(1)).findById((any()));
        verify(repository, times(1)).delete((any()));
        assertTrue(maybePocModel.isPresent());
        assertEquals(POC_MONGO_DOCUMENT.getContent(), maybePocModel.get().getPocModelContent().getContent());
    }

    @Test
    void shouldCountAtRepositoryWhenCount() {
        when(repository.count()).thenReturn(ID);
        long count = store.count();
        verify(repository, times(1)).count();
        assertEquals(count, ID);
    }

    private static PocMongoDocument aPocMongoDocument(String content) {
        PocMongoDocument ret = new PocMongoDocument();
        ret.setId(ObjectId.get());
        ret.setContent(content);
        return ret;
    }
}