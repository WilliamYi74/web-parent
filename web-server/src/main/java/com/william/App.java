package com.william;

import com.william.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
@RestController
@SpringBootApplication
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    List<Person> hello() {
        List<Person> list = new ArrayList<>(100_000);
        for (int i = 0; i < 100_000; i++) {
            Person person = new Person();
            person.setId(1);
            person.setName("lay");
            person.setAge(18);
            person.setGender("男");
            log.info("person {}", person);
            list.add(person);
        }
        return list;
    }
}
