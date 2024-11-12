package com.cloudy.domain.container.model;

import com.cloudy.domain.server.model.Server;
import com.cloudy.global.config.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContainerLog {
    @JsonProperty("_source") // 실제 로그 데이터
    private LogSource source; // 내부 클래스인 LogSource를 _source에 매핑

    // 로그 데이터 관련 속성들을 담는 내부 클래스
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogSource {

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
    }

}
