package br.com.renatosrib.indexer.repository

import br.com.renatosrib.indexer.model.Document
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

import java.awt.print.Pageable

@Repository
interface DocumentRepository extends ElasticsearchRepository<Document, String> {

    public List<Document> findByContent(String content, Pageable pageable);
    
    public Document findByPath(String path)
}