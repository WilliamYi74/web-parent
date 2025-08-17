/**
 * @projectName web-parent
 * @package com.fuxinyi.common
 * @className com.fuxinyi.common.HttpClientFactory
 */
package com.fuxinyi.common;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;

/**
 * HttpClientFactory
 *
 * @author william
 * @version 1.0
 * @description httpclient工厂
 * @date 2025/8/17 10:43
 */
public class HttpClientFactory {

    public static CloseableHttpClient create(Config config) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(config.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(config.getDefaultMaxPerRoute());
        connectionManager.setDefaultConnectionConfig(ConnectionConfig.custom()
                .setConnectTimeout(config.getConnectTimeout())
                .setSocketTimeout(config.getSocketTimeout())
                .setTimeToLive(config.getTimeToLive())
                .setValidateAfterInactivity(config.getValidateAfterInactivity())
                .build());
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(config.getConnectionRequestTimeout())
                .build();
        HttpClientBuilder builder = HttpClients.custom();
        builder.setConnectionManager(connectionManager);
        builder.setDefaultRequestConfig(requestConfig);
        if (config.getMaxRetries() < 1) {
            HttpRequestRetryStrategy retryStrategy = new DefaultHttpRequestRetryStrategy(
                    config.getMaxRetries(),
                    config.getDefaultRetryInterval());
            builder.setRetryStrategy(retryStrategy);
        }
        if (config.isDisableCookieManagement()) {
            builder.disableCookieManagement();
        }
        return builder.build();
    }

    public static CloseableHttpClient createDefault() {
        return create(Config.defaultConfig());
    }
}