package com.javedrpi.kafkaconsumer.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JsonLog {

    public Instant instant;
    public String thread;
    public String level;
    public String loggerName;
    public String message;
    public Boolean endOfBatch;
    public String loggerFqcn;
    public Integer threadId;
    public Integer threadPriority;

}