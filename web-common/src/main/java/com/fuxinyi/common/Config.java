package com.fuxinyi.common;

import lombok.Data;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.util.stream.Stream;

@Data
public class Config {

    /**
     * 连接池最大连接数
     */
    private int maxTotal;
    /**
     * 默认最大路由数(全局生效)
     */
    private int defaultMaxPerRoute;
    /**
     * 握手建立连接超时时间
     */
    private Timeout connectTimeout;
    /**
     * 读取响应数据时间
     */
    private Timeout socketTimeout;
    /**
     * 连接池连接存活时间
     */
    private TimeValue timeToLive;
    /**
     * 空闲连接有效性检测间隔
     */
    private TimeValue validateAfterInactivity;
    /**
     * 从连接池获取连接的超时时间
     */
    private Timeout connectionRequestTimeout;
    /**
     * 最大重试次数 小于1则不重试
     */
    private int maxRetries;
    /**
     * 重试间隔
     */
    private TimeValue defaultRetryInterval;
    /**
     * 是否禁用Cookie管理
     */
    private boolean disableCookieManagement;

    private static final int DEFAULT_MAX_TOTAL = 200;
    private static final int DEFAULT_DEFAULT_MAX_PER_ROUTE = 20;
    private static final Timeout DEFAULT_CONNECT_TIMEOUT = Timeout.ofMilliseconds(5_000);
    private static final Timeout DEFAULT_SOCKET_TIMEOUT = Timeout.ofMilliseconds(30_000);
    private static final Timeout DEFAULT_TIME_TO_LIVE = Timeout.ofMilliseconds(30_000);
    private static final Timeout DEFAULT_VALIDATE_AFTER_INACTIVITY = Timeout.ofMilliseconds(30_000);
    private static final Timeout DEFAULT_CONNECTION_REQUEST_TIMEOUT = Timeout.ofMilliseconds(5_000);
    private static final int DEFAULT_MAX_RETRIES = 3;
    private static final TimeValue DEFAULT_DEFAULT_CONNECTION_RETRY_INTERVAL = Timeout.ofMilliseconds(200);
    private static final boolean DEFAULT_DISABLE_COOKIE_MANAGEMENT = false;

    private Config() {
    }

    private Config(int maxTotal,
                   int defaultMaxPerRoute,
                   Timeout connectTimeout,
                   Timeout socketTimeout,
                   TimeValue timeToLive,
                   TimeValue validateAfterInactivity,
                   Timeout connectionRequestTimeout,
                   int maxRetries,
                   TimeValue defaultRetryInterval,
                   boolean disableCookieManagement) {
        this.maxTotal = maxTotal;
        this.defaultMaxPerRoute = defaultMaxPerRoute;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
        this.timeToLive = timeToLive;
        this.validateAfterInactivity = validateAfterInactivity;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.maxRetries = maxRetries;
        this.defaultRetryInterval = defaultRetryInterval;
        this.disableCookieManagement = disableCookieManagement;
    }

    /**
     * @return
     * @author williamyi
     * @description 默认配置
     * @createdAt 2025-08-17 23:10:26
     **/
    public static Config defaultConfig() {
        return new Config.Builder().build();
    }

    public static Config.Builder builder() {
        return new Config.Builder();
    }

    public static class Builder {
        private int maxTotal;
        private int defaultMaxPerRoute;
        private Timeout connectTimeout;
        private Timeout socketTimeout;
        private TimeValue timeToLive;
        private TimeValue validateAfterInactivity;
        private Timeout connectionRequestTimeout;
        private int maxRetries;
        private TimeValue defaultRetryInterval;
        private boolean disableCookieManagement;

        public Builder() {
            this.maxTotal = DEFAULT_MAX_TOTAL;
            this.defaultMaxPerRoute = DEFAULT_DEFAULT_MAX_PER_ROUTE;
            this.connectTimeout = DEFAULT_CONNECT_TIMEOUT;
            this.socketTimeout = DEFAULT_SOCKET_TIMEOUT;
            this.timeToLive = DEFAULT_TIME_TO_LIVE;
            this.validateAfterInactivity = DEFAULT_VALIDATE_AFTER_INACTIVITY;
            this.connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT;
            this.maxRetries = DEFAULT_MAX_RETRIES;
            this.defaultRetryInterval = DEFAULT_DEFAULT_CONNECTION_RETRY_INTERVAL;
            this.disableCookieManagement = DEFAULT_DISABLE_COOKIE_MANAGEMENT;
        }

        public Builder maxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder defaultMaxPerRoute(int defaultMaxPerRoute) {
            this.defaultMaxPerRoute = defaultMaxPerRoute;
            return this;
        }

        public Builder connectTimeout(Timeout connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder socketTimeout(Timeout socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        public Builder timeToLive(TimeValue timeToLive) {
            this.timeToLive = timeToLive;
            return this;
        }

        public Builder validateAfterInactivity(TimeValue validateAfterInactivity) {
            this.validateAfterInactivity = validateAfterInactivity;
            return this;
        }

        public Builder connectionRequestTimeout(Timeout connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
            return this;
        }

        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public Builder defaultRetryInterval(TimeValue defaultRetryInterval) {
            this.defaultRetryInterval = defaultRetryInterval;
            return this;
        }

        public Builder disableCookieManagement(boolean disableCookieManagement) {
            this.disableCookieManagement = disableCookieManagement;
            return this;
        }

        public Config build() {
            return new Config(
                    this.maxTotal,
                    this.defaultMaxPerRoute,
                    this.connectTimeout,
                    this.socketTimeout,
                    this.timeToLive,
                    this.validateAfterInactivity,
                    this.connectionRequestTimeout,
                    this.maxRetries,
                    this.defaultRetryInterval,
                    this.disableCookieManagement);
        }
    }
}