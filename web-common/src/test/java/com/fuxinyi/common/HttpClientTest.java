package com.fuxinyi.common;

import com.william.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.Method;
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
    void testFetch() {
        BasicNameValuePair param = new BasicNameValuePair("iccid", "89860623390002324229");
        String s = request.fetch("https://boss.juhuoiot.com/bms-sim/sim/manage/getSimCardStatus", Method.GET, Arrays.asList(param), null, null);
        log.info("result {}", s);
    }

    @Test
    void testGet() {
        BasicNameValuePair param = new BasicNameValuePair("iccid", "89860623390002324229");
        String s = request.get("https://boss.juhuoiot.com/bms-sim/sim/manage/getSimCardStatus", Arrays.asList(param));
        log.info("result {}", s);
    }

    @Test
    void testPost() {
        Person person = new Person();
        person.setId(1);
        person.setName("lay");
        person.setAge(18);
        person.setGender("男");

        String s = request.post("http://localhost:8080/post/1", person);
        log.info("result {}", s);
    }

    @Test
    void testDel() {
        Person person = new Person();
        person.setId(1);
        person.setName("lay");
        person.setAge(18);
        person.setGender("男");

        String s = request.delete("http://localhost:8080/del/1", person);
        log.info("result {}", s);
    }
}