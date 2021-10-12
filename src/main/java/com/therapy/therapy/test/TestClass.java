package com.therapy.therapy.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestClass {

    private Test test;

    @Bean
    public Test getTest(){
        return new Test("Hello Tsega");
    }
}
