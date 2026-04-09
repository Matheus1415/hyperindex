package com.hypeindex.demo.engine.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private final TrieNode root = new TrieNode();

    /*
    |--------------------------------------------------
    | Insere uma palavra na Trie
    |--------------------------------------------------
    | Percorre cada caractere da palavra e cria os nós
    | necessários caso ainda não existam.
    |
    | Exemplo:
    | "java" → j → a → v → a
    |
    | Complexidade: O(L)
    | (L = tamanho da palavra)
    */
    public void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            // Se o caractere não existir, cria um novo nó
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }

        node.isEndOfWord = true;
    }

    /*
    |--------------------------------------------------
    | Busca uma palavra completa na Trie
    |--------------------------------------------------
    | Verifica se a palavra existe e se é um fim válido.
    |
    | Retorna:
    | true  → palavra existe
    | false → não existe ou é apenas prefixo
    |
    | Complexidade: O(L)
    */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    /*
    |--------------------------------------------------
    | Localiza o nó final de uma palavra ou prefixo
    |--------------------------------------------------
    | Percorre a Trie seguindo os caracteres informados.
    |
    | Retorna:
    | Nó final → se encontrado
    | null     → se não existir caminho
    |
    | Usado internamente por:
    | - search()
    | - autocomplete()
    */
    private TrieNode findNode(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            node = node.children.get(c);

            // Caminho não existe
            if (node == null) return null;
        }

        return node;
    }


    /*
    |--------------------------------------------------
    | Retorna sugestões de palavras (autocomplete)
    |--------------------------------------------------
    | A partir de um prefixo, busca todas as palavras
    | possíveis na subárvore correspondente.
    |
    | Exemplo:
    | prefixo: "ja"
    | retorno: ["java", "javascript", "jar"]
    |
    | Complexidade:
    | O(L + N)
    | L = tamanho do prefixo
    | N = número de palavras encontradas
    */
    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();

        TrieNode node = findNode(prefix);

        if (node != null) {
            // Busca todas as palavras a partir desse ponto
            dfs(node, prefix, results);
        }

        return results;
    }

    /*
    |--------------------------------------------------
    | Busca em profundidade (DFS) na Trie
    |--------------------------------------------------
    | Percorre todos os caminhos possíveis a partir de
    | um nó, formando palavras completas.
    |
    | Estratégia:
    | - Se for fim de palavra → adiciona resultado
    | - Continua explorando os filhos recursivamente
    |
    | Usado para gerar autocomplete
    */
    private void dfs(TrieNode node, String word, List<String> results) {

        // Se chegou ao fim de uma palavra válida
        if (node.isEndOfWord) {
            results.add(word);
        }

        // Percorre todos os próximos caracteres possíveis
        for (char c : node.children.keySet()) {
            dfs(node.children.get(c), word + c, results);
        }
    }
}
