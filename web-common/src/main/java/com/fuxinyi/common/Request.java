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
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.BasicHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
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
@Component
@RequiredArgsConstructor
public class Request {

    private final CloseableHttpClient httpClient;

    public String fetch(String uri, String method, List<BasicNameValuePair> params, Object data) throws IOException, URISyntaxException {
        HttpEntity entity = null;
        StringBuilder sb = new StringBuilder("?");
        if (method.toLowerCase().equals("get")) {
            for (int i = 0; i < params.size(); i++) {
                BasicNameValuePair param = params.get(i);
                String name = param.getName();
                String value = param.getValue();
                sb.append(name).append("=").append(value);
                if (i != params.size() - 1) {
                    sb.append("&");
                }
            }
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(data);
            entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        }
        uri = uri + sb;
        BasicClassicHttpRequest request = new BasicClassicHttpRequest(method, uri);
        if (entity != null) {
            request.setEntity(entity);
        }
        return httpClient.execute(request, response -> EntityUtils.toString(response.getEntity()));
    }

}