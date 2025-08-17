/**
 * @projectName web-parent
 * @package com.fuxinyi.common
 * @className com.fuxinyi.common.HttpClient
 */
package com.fuxinyi.common;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HttpClient
 *
 * @author william
 * @version 1.0
 * @description httpclient实例
 * @date 2025/8/17 23:39
 */
@Configuration
public class HttpClientConfig {

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClientFactory.create(Config.defaultConfig());
    }
}