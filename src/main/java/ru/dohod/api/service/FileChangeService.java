package ru.dohod.api.service;

import java.io.IOException;

/**
 * File change API
 */
public interface FileChangeService {
    /**
     * Add random string to the beginning of the file.
     * Changes only files of text/plain and text/xml type.
     * @param searchPath absolute search path
     * @param searchCondition part/full file name
     * @throws IOException
     */
    void change(String searchPath, String searchCondition) throws IOException;
}
