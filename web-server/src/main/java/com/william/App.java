package com.william;

import com.william.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@RestController
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    Person hello() {
        Person person = new Person();
        person.setId(1);
        person.setName("lay");
        person.setAge(18);
        person.setGender("男");
        return person;
    }
}
