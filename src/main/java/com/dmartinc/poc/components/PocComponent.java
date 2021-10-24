package com.dmartinc.poc.components;

import com.dmartinc.poc.repositories.PocStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class PocComponent {

    private final PocStore pocStore;

    @Scheduled(fixedRate = 15_000)
    public void logCount() {
        log.info("PocStore count: {}", pocStore.count());
    }
}
