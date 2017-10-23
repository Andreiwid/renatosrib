package br.com.renatosrib.indexer.domain

import org.springframework.data.annotation.Id
import org.springframework.data.solr.core.mapping.Indexed
import org.springframework.data.solr.core.mapping.SolrDocument

@SolrDocument(solrCoreName = "files")
class IndexedFileEntity {
    @Id
    @Indexed(name = "id", type = "string")
    Long id

    @Indexed(name = "fileName", type = "string")
    String fileName

    @Indexed(name = "path", type = "string")
    String path

    @Indexed(name = "content", type = "string")
    String content
}
