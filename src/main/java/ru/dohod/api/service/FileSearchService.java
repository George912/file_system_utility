package ru.dohod.api.service;

import java.io.IOException;

public interface FileSearchService {
    void search(String searchPath, String searchCondition) throws IOException;
}
