package com.gmail.at.kotamadeo;

import java.util.concurrent.Executors;

public class Main {
private static final int SIZE = 100_000;
    public static void main(String[] args) {
        NicknameGenerator nicknameGenerator = new NicknameGenerator();
        nicknameGenerator.setExecutorService(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
        String[] texts = nicknameGenerator.generateText(SIZE);
        nicknameGenerator.beautifulWordsWithCustomLength(texts,3);
        nicknameGenerator.beautifulWordsWithCustomLength(texts,4);
        nicknameGenerator.beautifulWordsWithCustomLength(texts,5);
        nicknameGenerator.shutdown();
    }
}