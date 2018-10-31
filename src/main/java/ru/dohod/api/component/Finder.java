package ru.dohod.api.component;

import java.nio.file.Path;
import java.util.List;

/**
 * Finder API. Used by FileSearchService
 * for recursive search file by globPattern.
 */
public interface Finder {
    /**
     * Initialize Finder instance through globPattern.
     * @param globPattern used by PathMatcher for file searching
     */
    void init(String globPattern);

    /**
     * Retrieve file searching result
     * @return file found path list
     */
    List<Path> done();

}
