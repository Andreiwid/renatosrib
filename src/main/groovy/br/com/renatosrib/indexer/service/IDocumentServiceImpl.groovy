package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.repository.DocumentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
class IDocumentServiceImpl implements IDocumentService {

    @Autowired
    DocumentRepository repository


    @Override
    Document save(Document document) {
        return repository.save(document)
    }

    @Override
    List<Document> findByContent(String content, Integer page) {
        return null
    }

    @Override
    List<Document> findAll() {
        return repository.findAll().toList()
    }
}
