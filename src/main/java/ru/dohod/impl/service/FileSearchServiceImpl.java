package ru.dohod.impl.service;

import org.springframework.stereotype.Service;
import ru.dohod.api.component.Finder;
import ru.dohod.api.service.FileSearchService;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("fileSearchService")
public class FileSearchServiceImpl implements FileSearchService {
    private Finder finder;

    public FileSearchServiceImpl(Finder finder) {
        this.finder = finder;
    }

    @Override
    public void search(String searchPath, String searchCondition) throws IOException {
        finder.init(buildGlobPattern(searchCondition));
        Files.walkFileTree(Paths.get(searchPath), (FileVisitor<? super Path>) finder);
        finder.done().forEach(path -> System.out.println(path));
    }

    /**
     * Build glob pattern from command line parameter
     *
     * @param searchCondition command line parameter
     * @return glob pattern
     */
    private String buildGlobPattern(String searchCondition) {
        return new StringBuilder("glob:")
                .append(searchCondition)
                .append("*")
                .toString();
    }
}
