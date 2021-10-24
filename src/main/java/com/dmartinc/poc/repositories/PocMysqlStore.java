package com.dmartinc.poc.repositories;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;
import com.dmartinc.poc.repositories.model.PocMysqlRow;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MYSQL;

@Profile(PROFILE_MYSQL)
@RequiredArgsConstructor
@Component
public class PocMysqlStore implements PocStore {

    private final PocMysqlRepository repository;

    @Override
    public PocModel create(PocModelContent pocModelContent) {
        return buildPocModel(repository.save(buildPocMysqlRow(pocModelContent)));
    }

    @Override
    public Optional<PocModel> read(String id) {
        return repository.findById(Long.valueOf(id))
                .map(this::buildPocModel);
    }

    @Override
    public Optional<PocModel> update(String id, PocModelContent pocModelContent) {
        return repository.findById(Long.valueOf(id))
                .map(pocMysqlRow -> updateWithPocModel(pocMysqlRow, pocModelContent))
                .map(repository::save)
                .map(this::buildPocModel);
    }

    @Override
    public Optional<PocModel> delete(String id) {
        return repository.findById(Long.valueOf(id))
                        .map(this::deleteAndParse);
    }

    @Override
    public long count() {
        return repository.count();
    }

    private PocModel deleteAndParse(PocMysqlRow pocMysqlRow) {
        repository.delete(pocMysqlRow);
        return buildPocModel(pocMysqlRow);
    }

    private PocMysqlRow updateWithPocModel(PocMysqlRow pocMysqlRow, PocModelContent pocModelContent) {
        pocMysqlRow.setContent(pocModelContent.getContent());
        return pocMysqlRow;
    }

    private PocModel buildPocModel(PocMysqlRow row) {
        PocModel ret = new PocModel();
        ret.setId(String.valueOf(row.getId()));
        ret.setPocModelContent(buildPocModelContent(row.getContent()));
        return ret;
    }

    private PocModelContent buildPocModelContent(String content) {
        PocModelContent ret = new PocModelContent();
        ret.setContent(content);
        return ret;
    }

    private PocMysqlRow buildPocMysqlRow(PocModelContent pocModelContent) {
        PocMysqlRow ret = new PocMysqlRow();
        ret.setContent(pocModelContent.getContent());
        ret.setTimestamp(new Date());
        return ret;
    }
}
