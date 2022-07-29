package org.example;

import org.example.converter.WordToNum;

public class Main {
    public static void main(String[] args) {
        WordToNum wordToNum = new WordToNum();
        System.out.println(wordToNum.convertToInt("123"));
        System.out.println(wordToNum.convertToDouble("12.12"));
    }
}