package ru.dohod.impl.service;

import com.beust.jcommander.JCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dohod.api.service.FileChangeService;
import ru.dohod.api.service.FileSearchService;
import ru.dohod.api.service.ReadDrivesService;
import ru.dohod.api.service.UtilityService;
import ru.dohod.cli.FileChangeCommand;
import ru.dohod.cli.FileSearchCommand;
import ru.dohod.cli.ReadDrivesCommand;

import java.io.IOException;

@Service("utilityService")
public class UtilityServiceImpl implements UtilityService {
    private ReadDrivesCommand readDrivesCommand;
    private FileChangeCommand fileChangeCommand;
    private FileSearchCommand fileSearchCommand;
    private ReadDrivesService readDrivesService;
    private FileSearchService fileSearchService;
    private FileChangeService fileChangeService;
    private static final String READ_DRIVES_COMMAND = "-readDrives";
    private static final String SEARCH_COMMAND = "-search";
    private static final String CHANGE_COMMAND = "-change";
    private static final String LOCAL_DISK_FOUND_LIST_MSG = "List of local disks found:";
    private static final String FOUND_FILES_LIST_MSG = "List of found files:";
    private static final String CHANGED_FILES_LIST_LIST_MSG = "List of changed files:";

    @Autowired
    public UtilityServiceImpl(ReadDrivesCommand readDrivesCommand, FileChangeCommand fileChangeCommand, FileSearchCommand fileSearchCommand, ReadDrivesService readDrivesService, FileSearchService fileSearchService, FileChangeService fileChangeService) {
        this.readDrivesCommand = readDrivesCommand;
        this.fileChangeCommand = fileChangeCommand;
        this.fileSearchCommand = fileSearchCommand;
        this.readDrivesService = readDrivesService;
        this.fileSearchService = fileSearchService;
        this.fileChangeService = fileChangeService;
    }

    @Override
    public void start(String[] args) throws IOException {
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(readDrivesCommand)
                .addCommand(fileChangeCommand)
                .addCommand(fileSearchCommand)
                .build();
        jCommander.parse(args);
        String parsedCommand = jCommander.getParsedCommand();
        if (READ_DRIVES_COMMAND.equals(parsedCommand)) {
            System.out.println(LOCAL_DISK_FOUND_LIST_MSG);
            readDrivesService.readDrives();

        } else if (SEARCH_COMMAND.equals(parsedCommand)) {
            System.out.println(FOUND_FILES_LIST_MSG);
            fileSearchService.search(fileSearchCommand.getSearchPath(), fileSearchCommand.getSearchCondition())
                    .forEach(System.out::println);

        } else if (CHANGE_COMMAND.equals(parsedCommand)) {
            System.out.println(CHANGED_FILES_LIST_LIST_MSG);
            fileChangeService.change(fileChangeCommand.getSearchPath(), fileChangeCommand.getSearchCondition());
        }
    }
}
