package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

interface IDocumentService {
    public Document save(Document document)

    public List<Document> findByContent(String content, Pageable pageRequest)

    public List<Document> findAll()

}