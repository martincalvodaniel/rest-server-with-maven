package com.dmartinc.poc.services;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;
import com.dmartinc.poc.repositories.PocStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PocService {

    private final PocStore store;

    public PocModel create(PocModelContent pocModelContent) {
        return store.create(pocModelContent);
    }

    public Optional<PocModel> read(String id) {
        return store.read(id);
    }

    public Optional<PocModel> update(String id, PocModelContent pocModelContent) {
        return store.update(id, pocModelContent);
    }

    public Optional<PocModel> delete(String id) {
        return store.delete(id);
    }
}
