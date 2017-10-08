package br.com.renatosrib.indexer.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class Controller {

    @RequestMapping("/" )
    @ResponseBody
    public String index() {
        return "Hello world!"
    }
}
