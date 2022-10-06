package com.gmail.at.kotamadeo;

import lombok.Setter;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Setter
public class NicknameGenerator {
    private static final Random RANDOM = new Random();
    private AtomicInteger atomicInteger = new AtomicInteger();
    private ExecutorService executorService;

    public String[] generateText(int size) {
        String[] texts = new String[size];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateString("abc", 3 + RANDOM.nextInt(3));
        }
        return texts;
    }

    private String generateString(String letters, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(letters.charAt(RANDOM.nextInt(letters.length())));
        }
        return stringBuilder.toString();
    }

    private boolean isPalindrome(String text) {
        return text.equalsIgnoreCase(new StringBuilder(text).reverse().toString());
    }

    private boolean sortedLetter(String text) {
        char[] sLetter = text.toCharArray();
        int nLetter = 0;
        boolean result = false;
        for (char i = 0; i < sLetter.length - 1; i++) {
            if (sLetter[i] == sLetter[i + 1])
                nLetter++;
        }
        if (nLetter == sLetter.length - 1) {
            result = true;
        }
        return result;
    }

    private boolean sortedText(String text) {
        char[] sText = text.toCharArray();
        Arrays.sort(sText);
        String sorted = String.valueOf(sText);
        return text.equals(sorted);
    }

    public void beautifulWordsWithCustomLength(String[] texts, int length) {
        IntStream.range(0, texts.length)
                .forEach(i -> executorService.submit(() -> {
                    if (texts[i].length() == length && (isPalindrome(texts[i]) || sortedText(texts[i])
                            || sortedLetter(texts[i]))) {
                        atomicInteger.incrementAndGet();
                    }
                }));
        System.out.printf("Красивых слов с длинной %d: %d шт.%n", length, atomicInteger.get());
        atomicInteger = new AtomicInteger(0);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
