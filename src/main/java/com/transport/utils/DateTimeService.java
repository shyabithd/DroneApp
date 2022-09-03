package com.transport.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeService {

    private LocalDateTime current;

    @PostConstruct
    public void init() {
        current = LocalDateTime.now();
    }

    public synchronized LocalDateTime getUniqueTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        if (current.isEqual(now) || current.isAfter(now)) {
            current = current.plus(1, ChronoUnit.MICROS);
        } else {
            current = now;
        }
        return current;
    }
}
