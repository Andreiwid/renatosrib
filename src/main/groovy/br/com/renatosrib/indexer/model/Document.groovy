package br.com.renatosrib.indexer.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldIndex

@org.springframework.data.elasticsearch.annotations.Document(indexName = "files", type="files")
class Document {
    @Id
    @Field(index = FieldIndex.not_analyzed)
    String id

    String fileName

    String content

    Long lastModification
}
