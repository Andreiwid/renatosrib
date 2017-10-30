package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document

interface IDocumentService {
    public Document save(Document document)

    public List<Document> findByContent(String content, Integer page)

    public List<Document> findAll()

}