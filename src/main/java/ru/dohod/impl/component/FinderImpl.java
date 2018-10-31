package ru.dohod.impl.component;

import org.springframework.stereotype.Component;
import ru.dohod.api.component.Finder;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

@Component("finder")
public class FinderImpl extends SimpleFileVisitor<Path> implements Finder {
    private PathMatcher matcher;
    private List<Path> foundFilePathList = new ArrayList<>();

    private void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            foundFilePathList.add(file);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        find(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    @Override
    public void init(String globPattern) {
        matcher = FileSystems.getDefault().getPathMatcher(globPattern);
    }

    @Override
    public List<Path> done() {
        return foundFilePathList;
    }
}
