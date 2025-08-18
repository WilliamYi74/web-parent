/**
 * @projectName web-parent
 * @package com.william
 * @className com.william.HttpClientTest
 */
package com.william;

import com.fuxinyi.common.HttpClientConfig;
import com.fuxinyi.common.Request;
import com.fuxinyi.config.ImportSelectorImpl;
import com.william.domain.Person;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * HttpClientTest
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/18 10:17
 */
@Import({Request.class, HttpClientConfig.class})
//    @Import(ImportSelectorImpl.class)
@SpringBootTest
class HttpClientTest {

    private static final Logger log = LoggerFactory.getLogger(HttpClientTest.class);

    @Resource
    private ApplicationContext applicationContext;

    @Test
    void testGet() {
        Request request = applicationContext.getBean(Request.class);
        Person person = new Person();
        person.setId(1);
        person.setName("lay");
        person.setAge(18);
        person.setGender("男");
        BasicNameValuePair param = new BasicNameValuePair("type", "1");
        String result = request.fetch("http://localhost:8080/post", Method.POST, Arrays.asList(param), person, null);
        log.info("result {}", result);
        Animal animal = (Animal) applicationContext.getBean("animal");
        log.info("animal {}", animal);
    }

    @Test
    void testConditionalOnBean(){
        Object request = applicationContext.getBean("request");
        log.info("request {}", request);
    }
}