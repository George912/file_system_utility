package ru.dohod.api.service;

import java.io.IOException;

/**
 * Main service, contain application functionality
 */
public interface UtilityService {
    /**
     * Start commands execution
     * @param args command line arguments
     */
    void start(String[] args) throws IOException;
}
