package ru.malygin.parser_client.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@EnableScheduling
@Service
public class CacheService {

    private final FilmCache filmCache;
    private final DateCache dateCache;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        String dateCacheInfo = dateCache.create();
        log.info("Date cache created: {}", dateCacheInfo);

        String filmCacheInfo = filmCache.create();
        log.info("Film cache created: {}", filmCacheInfo);

    }

    @Scheduled(cron = "${cache.update.cron: 0 0 0 * * ?}")
    public void updateCache() {
        String dateCacheInfo = dateCache.update();
        log.info("Date cache updated: {}", dateCacheInfo);

        String filmCacheInfo = filmCache.update();
        log.info("Film cache updated: {}", filmCacheInfo);
    }
}
