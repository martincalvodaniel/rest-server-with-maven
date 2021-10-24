package com.dmartinc.poc.repositories;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;

import java.util.Optional;

public interface PocStore {
    PocModel create(PocModelContent pocModelContent);

    Optional<PocModel> read(String id);

    Optional<PocModel> update(String id, PocModelContent pocModelContent);

    Optional<PocModel> delete(String id);

    long count();
}
