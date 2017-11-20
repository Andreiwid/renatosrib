package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.to.DocumentTo
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

interface IDocumentService {
    public Document download(DocumentTo document)

    public List<DocumentTo> findByContent(String content, Pageable pageRequest)

    public List<DocumentTo> findAll()

}