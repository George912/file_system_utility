package ru.dohod.impl.service;

import com.beust.jcommander.JCommander;
import org.springframework.stereotype.Service;
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
    private static final String READ_DRIVES_COMMAND = "-readDrives";
    private static final String SEARCH_COMMAND = "-search";
    private static final String CHANGE_COMMAND = "-change";

    public UtilityServiceImpl(ReadDrivesCommand readDrivesCommand, FileChangeCommand fileChangeCommand, FileSearchCommand fileSearchCommand, ReadDrivesService readDrivesService, FileSearchService fileSearchService) {
        this.readDrivesCommand = readDrivesCommand;
        this.fileChangeCommand = fileChangeCommand;
        this.fileSearchCommand = fileSearchCommand;
        this.readDrivesService = readDrivesService;
        this.fileSearchService = fileSearchService;
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
        System.out.println("Command from cli: " + parsedCommand);
        System.out.println("Command params: " + fileSearchCommand);
        System.out.println("Command params: " + fileChangeCommand);
        if (READ_DRIVES_COMMAND.equals(parsedCommand)) {
            readDrivesService.readDrives();

        } else if (SEARCH_COMMAND.equals(parsedCommand)) {
            fileSearchService.search(fileSearchCommand.getSearchPath(), fileSearchCommand.getSearchCondition());

        } else if (CHANGE_COMMAND.equals(parsedCommand)) {


        }
    }
}
