package br.com.renatosrib.indexer.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Field

@org.springframework.data.elasticsearch.annotations.Document(indexName = "files", type="files")
class Document {
    @Id
    String path

    String fileName


    String content

    Long lastModification
}
