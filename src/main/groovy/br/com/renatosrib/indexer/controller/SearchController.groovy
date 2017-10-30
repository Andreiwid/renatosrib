package br.com.renatosrib.indexer.controller

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.service.IDocumentExtractorService
import br.com.renatosrib.indexer.service.IDocumentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/files")
class SearchController {

    @Autowired
    IDocumentService documentService

    @Autowired
    IDocumentExtractorService documentExtractorService

    @RequestMapping("/ok")
    public String ok() {
        return "Ok"
    }

    @RequestMapping(value = "/save", method = [RequestMethod.POST])
    @ResponseBody
    public Document save(Document document){
        return documentService.save(document)
    }

    @RequestMapping(value = "/save", method = [RequestMethod.GET])
    @ResponseBody
    public List<Document> indexFolder(){
        return documentExtractorService.synchronizeFolder()
    }

    @RequestMapping("/")
    public List<Document> documents() {
        return documentService.findAll()
    }
}
