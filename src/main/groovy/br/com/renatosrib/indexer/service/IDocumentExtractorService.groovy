package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document

interface IDocumentExtractorService {
    List<Document> synchronizeFolder()

}