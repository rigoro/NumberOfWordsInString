package com.string.wordsCount.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StringOperationsService {

    @Value("${words.separator.regex}")
    private String separatorRegex;

    /**
     * Returns number of words for given string.
     * By word we understand a sequence of one or more non-space character
     *
     * @param str
     * @return number of words for given string
     */
    public int calculateWordsCount(String str) {
        if (str == null) {
            return 0;
        }
        String input = str.trim();

        return input.isEmpty() ? 0 : input.split(separatorRegex).length;
    }
}
