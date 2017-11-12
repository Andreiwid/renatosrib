package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.repository.DocumentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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
    List<Document> findByContent(String content, Pageable pageRequest) {
        Page<Document> documents = repository.findByContent(content, pageRequest)
        return documents.getContent()
    }

    @Override
    List<Document> findAll() {
        return repository.findAll().toList()
    }
}
