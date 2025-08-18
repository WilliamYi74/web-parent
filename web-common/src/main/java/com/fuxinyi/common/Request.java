/**
 * @projectName web-parent
 * @package com.fuxinyi.common
 * @className com.fuxinyi.common.Fetch
 */
package com.fuxinyi.common;

import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.BasicHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Fetch
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/18 00:25
 */
@Slf4j
@ConditionalOnBean(name="httpClient")
@Component
@RequiredArgsConstructor
public class Request {

    private final CloseableHttpClient httpClient;

    /**
     * @return
     * @author williamyi
     * @description 发送请求 get请求参数放params 其它放data 可自行组装restful请求
     * @createdAt 2025-08-18 10:13:26
     **/
    public String fetch(String url, Method method, List<BasicNameValuePair> params, Object data, List<BasicHeader> headers) {
        HttpEntity entity = null;
        StringBuilder sb = null;
        // 拼接querystring参数
        if (params != null && !params.isEmpty()) {
            sb = new StringBuilder("?");
            for (int i = 0; i < params.size(); i++) {
                BasicNameValuePair param = params.get(i);
                sb.append(URLEncoder.encode(param.getName(), StandardCharsets.UTF_8))
                        .append("=")
                        .append(URLEncoder.encode(param.getValue(), StandardCharsets.UTF_8));
                if (i != params.size() - 1) {
                    sb.append("&");
                }
            }
        }
        if (sb != null) {
            url += sb.toString();
        }
        // 处理body参数
        if (method != null && !method.name().equalsIgnoreCase("get") && data != null) {
            Gson gson = new Gson();
            String json = gson.toJson(data);
            entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        }
        URI uri = null;
        try {
            uri = new URIBuilder(url).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        BasicClassicHttpRequest request = new BasicClassicHttpRequest(method == null ? "get" : method.name(), uri);
        // 处理请求头
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(request::addHeader);
        }
        // 添加必要请求头 现代服务器大部分都会拒绝无头请求并返回400
        request.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/139.0.0.0 Safari/537.36");
        request.setHeader(HttpHeaders.ACCEPT, "*");
        if (entity != null) {
            request.setEntity(entity);
        }
        try {
            return httpClient.execute(request, response -> EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String url, List<BasicNameValuePair> params) {
        return get(url, params, null);
    }

    public String get(String url, List<BasicNameValuePair> params, List<BasicHeader> headers) {
        return fetch(url, Method.GET, params, null, headers);
    }

    public String post(String url, Object data) {
        return post(url, data, null);
    }

    public String post(String url, Object data, List<BasicHeader> headers) {
        return fetch(url, Method.POST, null, data, headers);
    }

    public String put(String url, Object data) {
        return put(url, data, null);
    }

    public String put(String url, Object data, List<BasicHeader> headers) {
        return fetch(url, Method.PUT, null, data, headers);
    }

    public String delete(String url, Object data) {
        return delete(url, data, null);
    }

    public String delete(String url, Object data, List<BasicHeader> headers) {
        return fetch(url, Method.DELETE, null, data, headers);
    }

}