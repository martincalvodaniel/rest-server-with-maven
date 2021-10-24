package com.dmartinc.poc.repositories;

import com.dmartinc.poc.repositories.model.PocMysqlRow;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import static com.dmartinc.poc.model.PocModelConstants.PROFILE_MYSQL;

@Profile(PROFILE_MYSQL)
@Repository
interface PocMysqlRepository extends CrudRepository<PocMysqlRow, Long> {
}
