package br.com.renatosrib.indexer.repository

import br.com.renatosrib.indexer.model.Document
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository



@Repository
interface DocumentRepository extends ElasticsearchRepository<Document, String> {

    @Query("{ \"query\": { \"query_string\": { \"query\": \"*?0*\", \"fields\":[\"content\", \"fileName\"] } }, \"highlight\" : { \"fields\" : { \"content\" : {}, \"fileName\": {} } } }")
    public Page<Document> findByContent(String content, Pageable pageable);

    @Query("{ \"query\": { \"bool\": { \"filter\": { \"term\":  { \"id\": \"?0\" }  } }}}")
    public Document findByPath(String path)
}