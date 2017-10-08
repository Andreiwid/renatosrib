package br.com.renatosrib.indexer

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }
}
