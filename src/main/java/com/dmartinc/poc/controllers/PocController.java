package com.dmartinc.poc.controllers;

import com.dmartinc.poc.model.PocModel;
import com.dmartinc.poc.model.PocModelContent;
import com.dmartinc.poc.services.PocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dmartinc.poc.model.PocModelConstants.FIELD_ID;
import static com.dmartinc.poc.model.PocModelConstants.PATH_POC;
import static com.dmartinc.poc.model.PocModelConstants.WITH_FIELD_ID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/" + PATH_POC)
public class PocController {

    private final PocService service;

    @PostMapping
    public ResponseEntity<PocModel> create(
            @RequestBody PocModelContent pocModelContent) {
        return ResponseEntity.ok(service.create(pocModelContent));
    }

    @PutMapping(WITH_FIELD_ID)
    public ResponseEntity<PocModel> update(
            @PathVariable(value = FIELD_ID) String id,
            @RequestBody PocModelContent pocModelContent) {
        return service.update(id, pocModelContent)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(WITH_FIELD_ID)
    public ResponseEntity<PocModel> delete(
            @PathVariable(value = FIELD_ID) String id) {
        return service.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(WITH_FIELD_ID)
    public ResponseEntity<PocModel> read(
            @PathVariable(value = FIELD_ID) String id) {
        return service.read(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
