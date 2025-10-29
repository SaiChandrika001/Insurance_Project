package com.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.demo.test.Palindrom;

public class PalindromTest {

    @ParameterizedTest
    @ValueSource(strings = {"Amma","Dad","Madam",""})
    public void isPalindromTest(String s) {
        Palindrom p = new Palindrom();

        assertAll(
            () -> assertTrue(p.isPalindrom("mam")),
            () -> assertTrue(p.isPalindrom("Amma")),
            () -> assertTrue(p.isPalindrom("Dad")),
            () -> assertFalse(p.isPalindrom("hello"))
        );
    }
}
