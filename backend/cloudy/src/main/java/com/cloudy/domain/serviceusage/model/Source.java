package com.cloudy.domain.serviceusage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Source {
    @JsonProperty("message")
    private String message;

    @JsonProperty("@timestamp")
    private String timeStamp;

    @JsonProperty("specific_date")
    private String specificDate;

    @JsonProperty("thread_name")
    private String threadName;

    @JsonProperty("logger_name")
    private String loggerName;

    @JsonProperty("level")
    private String level;

    @JsonProperty("level_value")
    private int levelValue;

    @Override
    public String toString() {
        return "LogSource{" +
                "message='" + message + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", specificDate='" + specificDate + '\'' +
                ", threadName='" + threadName + '\'' +
                ", loggerName='" + loggerName + '\'' +
                ", level='" + level + '\'' +
                ", levelValue=" + levelValue +
                '}';
    }
}
