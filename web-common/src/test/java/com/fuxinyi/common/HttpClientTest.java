package com.fuxinyi.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;

@Slf4j
@SpringBootTest
class HttpClientTest {

    @Resource
    private Request request;

    @Test
    void testGet() throws IOException, URISyntaxException {
        BasicNameValuePair param = new BasicNameValuePair("iccid", "89860623390002324229");
        String s = request.fetch("https://boss.juhuoiot.com/bms-sim/sim/manage/getSimCardStatus", "get", Arrays.asList(param), null);
        log.info("result {}", s);
    }
}