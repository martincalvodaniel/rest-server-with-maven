package com.dmartinc.poc.repositories;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;
import com.dmartinc.poc.repositories.model.PocMongoDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MONGO;

@Profile(PROFILE_MONGO)
@RequiredArgsConstructor
@Component
public class PocMongoStore implements PocStore {

    private final PocMongoRepository repository;

    @Override
    public PocModel create(PocModelContent content) {
        return buildPocModel(save(buildPocMongoDocument(content)));
    }

    @Override
    public Optional<PocModel> read(String id) {
        return repository.findById(id)
                .map(this::buildPocModel);
    }

    @Override
    public Optional<PocModel> update(String id, PocModelContent pocModelContent) {
        return repository.findById(id)
                .map(pocMongoDocument -> updateWithPocModel(pocMongoDocument, pocModelContent))
                .map(repository::save)
                .map(this::buildPocModel);
    }

    @Override
    public Optional<PocModel> delete(String id) {
        return repository.findById(id)
                .map(this::deleteAndParse);
    }

    @Override
    public long count() {
        return repository.count();
    }

    private PocModelContent buildPocModelContent(String content) {
        PocModelContent ret = new PocModelContent();
        ret.setContent(content);
        return ret;
    }

    private PocModel buildPocModel(PocMongoDocument document) {
        PocModel ret = new PocModel();
        ret.setId(document.getId().toString());
        ret.setPocModelContent(buildPocModelContent(document.getContent()));
        return ret;
    }

    private PocMongoDocument save(PocMongoDocument document) {
        return repository.save(document);
    }

    private PocMongoDocument updateWithPocModel(PocMongoDocument pocMongoDocument, PocModelContent pocModelContent) {
        pocMongoDocument.setContent(pocModelContent.getContent());
        return pocMongoDocument;
    }

    private PocMongoDocument buildPocMongoDocument(PocModelContent pocModelContent) {
        PocMongoDocument ret = new PocMongoDocument();
        ret.setContent(pocModelContent.getContent());
        ret.setTimestamp(new Date());
        return ret;
    }

    private PocModel deleteAndParse(PocMongoDocument document) {
        repository.delete(document);
        return buildPocModel(document);
    }
}
