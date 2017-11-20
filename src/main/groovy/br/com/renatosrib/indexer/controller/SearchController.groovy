package br.com.renatosrib.indexer.controller

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.service.IDocumentExtractorService
import br.com.renatosrib.indexer.service.IDocumentService
import br.com.renatosrib.indexer.to.DocumentTo
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.apache.http.entity.mime.MultipartEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.nio.file.Files

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

    @RequestMapping(value = "/download", method = [RequestMethod.GET])
    @ResponseBody
    BufferedOutputStream save(@RequestParam String path, HttpServletResponse response){
        try {
            File file = new File(path)
            response.addHeader("filename", file.name )
            response.setContentType(Files.probeContentType(file.toPath()))
            byte[] fileByte = FileUtils.readFileToByteArray(file);
            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();os arquivso
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

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
    List<DocumentTo> search(@RequestParam(required = true,  value="content") String term, @RequestParam(required = false, defaultValue = "1", value="page") Integer page, @RequestParam(required = false, defaultValue = "20", value="itemsPerPage") Integer itemsPerPage) {
        PageRequest pageRequest = new PageRequest(page -1, itemsPerPage)
        return documentService.findByContent(term, pageRequest)
    }

    @RequestMapping("/")
    List<DocumentTo> documents() {
        return documentService.findAll()
    }
}
