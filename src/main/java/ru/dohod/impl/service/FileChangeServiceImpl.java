package ru.dohod.impl.service;

import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import ru.dohod.api.service.FileChangeService;
import ru.dohod.api.service.FileSearchService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("fileChangeService")
public class FileChangeServiceImpl implements FileChangeService {
    private FileSearchService fileSearchService;
    private static final String FILE = "File: ";
    private static final String CONTENT = "Content: ";
    private static final String CARRIAGE_RETURN = "\n";
    private static final int LEFT_LIMIT = 97;
    private static final int RIGHT_LIMIT = 122;
    private static final int RANDOM_STRING_SIZE = 50;
    private static final String RANDOM_STRING_CHARSET = "UTF-8";
    private Charset charset;

    public FileChangeServiceImpl(FileSearchService fileSearchService) {
        this.fileSearchService = fileSearchService;
        charset = Charset.forName(RANDOM_STRING_CHARSET);
    }

    @Override
    public void change(String searchPath, String searchCondition) throws IOException {
        List<Path> fileFoundPathList = fileSearchService.search(searchPath, searchCondition);
        fileFoundPathList.forEach(path -> {
            try {
                StringBuilder stringBuilder = new StringBuilder(FILE)
                        .append(path)
                        .append(CARRIAGE_RETURN)
                        .append(CONTENT);
                System.out.println(stringBuilder.toString());
                Files.lines(path).forEach(System.out::println);
                System.out.println(CARRIAGE_RETURN);
                String fileContentType = Files.probeContentType(path);
                if (MimeTypeUtils.TEXT_PLAIN_VALUE.equals(fileContentType)
                        || MimeTypeUtils.TEXT_XML_VALUE.equals(fileContentType)) {
                    changeFile(path);
                }
            } catch (IOException e) {
                System.err.println("Exception while retrieving lines from path: " + e);
            }
        });
    }

    /**
     * Add random string to the beginning of the file
     *
     * @param path modifiable file
     * @throws IOException
     */
    private void changeFile(Path path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(generateRandomString())
                .append(CARRIAGE_RETURN)
                .append(Files.lines(path).collect(Collectors.joining(CARRIAGE_RETURN)));
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            writer.write(stringBuilder.toString(), 0, stringBuilder.toString().length());
        } catch (IOException x) {
            System.err.format("Exception while writing to file: " + x);
        }
    }

    /**
     * Generate random bounded(by RANDOM_STRING_SIZE, LEFT_LIMIT, RIGHT_LIMIT) string
     *
     * @return random bounded string
     */
    private String generateRandomString() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(RANDOM_STRING_SIZE);
        for (int i = 0; i < RANDOM_STRING_SIZE; i++) {
            int randomLimitedInt = LEFT_LIMIT + (int)
                    (random.nextFloat() * (RIGHT_LIMIT - LEFT_LIMIT + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
