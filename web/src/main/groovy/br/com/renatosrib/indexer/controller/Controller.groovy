package br.com.renatosrib.indexer.controller

import br.com.renatosrib.indexer.services.IFileService
import br.com.renatosrib.indexer.to.IndexedFileTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller {

    @Autowired
    IFileService fileService

    @RequestMapping("/" )
    @ResponseBody
    public String index() {
        return "Hello world!"
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST )
    @ResponseBody
    public String save(IndexedFileTO file) {
        fileService.save(file)
        return "Hello world!"
    }


}
