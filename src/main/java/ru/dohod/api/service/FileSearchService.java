package ru.dohod.api.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * File search API
 */
public interface FileSearchService {
    /**
     *
     * @param searchPath bsolute search path
     * @param searchCondition art/full file name
     * @return file found path list
     * @throws IOException
     */
    List<Path> search(String searchPath, String searchCondition) throws IOException;
}
