package br.com.renatosrib.indexer.controller

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.service.IDocumentExtractorService
import br.com.renatosrib.indexer.service.IDocumentService
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
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
    String ok() {
        return "Ok"
    }

    @RequestMapping(value = "/save", method = [RequestMethod.POST])
    @ResponseBody
    Document save(Document document){
        return documentService.save(document)
    }

    @RequestMapping(value = "/refresh", method = [RequestMethod.POST])
    @ResponseBody
    ResponseEntity<List<Document>> indexFolder(){
        List<Document> documents = documentExtractorService.synchronizeFolder()
        if(documents) {
            return new ResponseEntity(documents, HttpStatus.OK )

        }
        return new ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @RequestMapping(value = "/search", method = [RequestMethod.GET])
    @ResponseBody
    List<Document> search2(@RequestParam(required = true,  value="content") String term, @RequestParam(required = false, defaultValue = "1", value="page") Integer page, @RequestParam(required = false, defaultValue = "20", value="itemsPerPage") Integer itemsPerPage) {
        PageRequest pageRequest = new PageRequest(page -1, itemsPerPage, Sort.Direction.DESC, "lastModification")
        return documentService.findByContent(term, pageRequest)
    }

    @RequestMapping("/")
    List<Document> documents() {
        return documentService.findAll()
    }
}
