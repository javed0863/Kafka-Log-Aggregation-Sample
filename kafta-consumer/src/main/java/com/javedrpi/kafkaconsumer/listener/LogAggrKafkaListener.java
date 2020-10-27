package com.javedrpi.kafkaconsumer.listener;

import com.javedrpi.kafkaconsumer.model.JsonLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class LogAggrKafkaListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "${consumer.topic}", groupId = "${consumer.group-id}",
                    containerFactory = "jsonLogKafkaListenerFactory")
    public void consumeJson(JsonLog jsonLog) {
        StringBuilder logStmt = new StringBuilder();

        LocalDateTime timestamp = LocalDateTime.ofEpochSecond(
                getEpochSecond(jsonLog),
                getNanoOfSecond(jsonLog),
                ZoneOffset.ofHours(8));

        logStmt.append(timestamp+" ")
                .append(jsonLog.getThread()+" ")
                .append(jsonLog.getLevel()+" ")
                .append(jsonLog.getLoggerName()+" ")
                .append(jsonLog.getThreadId()+ " - ")
                .append(jsonLog.getMessage()+ " ");



        logger.debug(String.valueOf(logStmt));
    }

    private Integer getNanoOfSecond(JsonLog jsonLog) {
        return Optional.of(jsonLog)
                .map(o->o.getInstant())
                .map(o->o.getNanoOfSecond())
                .orElse(LocalDateTime.now().getNano());
    }

    private Long getEpochSecond(JsonLog jsonLog) {
        return Optional.of(jsonLog)
                .map(o->o.getInstant())
                .map(o->o.getEpochSecond())
                .orElse(LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)));
    }
}
