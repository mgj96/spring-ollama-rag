package com.example.springai;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application.yml의 app.http 타임아웃 설정 바인딩.
 */
@ConfigurationProperties(prefix = "app.http")
public class HttpClientProperties {

    /** 연결 타임아웃 (초). */
    private int connectTimeout = 10;
    /** 읽기 타임아웃 (초). */
    private int readTimeout = 30;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
