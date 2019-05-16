package com.string.wordsCount.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringOperationsServiceTest {

    @Autowired
    private StringOperationsService stringOperationsService;

    @Test
    public void wordsCountTest() {
        //null check
        assertEquals(0, stringOperationsService.calculateWordsCount(null));
        //empty string
        assertEquals(0, stringOperationsService.calculateWordsCount(""));
        //multiple spaces
        assertEquals(0, stringOperationsService.calculateWordsCount(" "));
        //correct string
        assertEquals(2, stringOperationsService.calculateWordsCount("Good string"));
        //carriage return
        assertEquals(4, stringOperationsService.calculateWordsCount("String with \r carriage return"));
        //multiline string
        assertEquals(2, stringOperationsService.calculateWordsCount("Multiline \n string"));
        //trailing spaces
        assertEquals(4, stringOperationsService.calculateWordsCount(" String with trailing spaces "));
    }
}
