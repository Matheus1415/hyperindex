package com.hypeindex.demo.service;

import com.hypeindex.demo.engine.trie.Trie;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class IndexService {

    private final Trie trie = new Trie();

    /*
    |--------------------------------------------------
    | Indexa um arquivo linha por linha
    |--------------------------------------------------
    */
    public void indexFile(MultipartFile file) throws Exception {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        );

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\W+");

            for (String word : words) {
                trie.insert(word.toLowerCase());
            }
        }
    }

    /*
    |--------------------------------------------------
    | Retorna sugestões de autocomplete
    |--------------------------------------------------
    */
    public List<String> search(String prefix) {
        return trie.autocomplete(prefix.toLowerCase());
    }
}