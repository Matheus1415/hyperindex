package com.hypeindex.demo.engine.trie;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    /*
    |--------------------------------------------------
    | Representa UM NÓ da árvore Trie
    | - palavra armazenada: java
    | - palavra guardada: j → a → v → a
    |--------------------------------------------------
    */
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}
