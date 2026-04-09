package com.hypeindex.demo;

import com.hypeindex.demo.service.IndexService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService indexService)
    {
        this.indexService = indexService;
    }

    /*
    |--------------------------------------------------
    | Indexar um arquivo
    |--------------------------------------------------
    */
    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            indexService.indexFile(file);
            return "Arquivo indexado com sucesso!";
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }

    /*
    |--------------------------------------------------
    | Busca por prefixo (autocomplete)
    |--------------------------------------------------
    */
    @GetMapping("/search")
    public Object search(@RequestParam String term)
    {
        return indexService.search(term);
    }
}
