package br.com.renatosrib.indexer

import br.com.renatosrib.indexer.service.IDocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.elasticsearch.core.ElasticsearchOperations

@SpringBootApplication
class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args)
    }
}