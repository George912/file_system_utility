package ru.dohod.impl.service;

import com.beust.jcommander.JCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dohod.api.service.ReadDrivesService;
import ru.dohod.api.service.UtilityService;
import ru.dohod.cli.FileChangeCommand;
import ru.dohod.cli.FileFindCommand;
import ru.dohod.cli.ReadDrivesCommand;

@Service("utilityService")
public class UtilityServiceImpl implements UtilityService {
    private ReadDrivesCommand readDrivesCommand;
    private FileChangeCommand fileChangeCommand;
    private FileFindCommand fileFindCommand;
    private ReadDrivesService readDrivesService;
    private static final String READ_DRIVES_COMMAND = "-readDrives";

    @Autowired
    public UtilityServiceImpl(ReadDrivesCommand readDrivesCommand, FileChangeCommand fileChangeCommand, FileFindCommand fileFindCommand, ReadDrivesService readDrivesService) {
        this.readDrivesCommand = readDrivesCommand;
        this.fileChangeCommand = fileChangeCommand;
        this.fileFindCommand = fileFindCommand;
        this.readDrivesService = readDrivesService;
    }

    @Override
    public void start(String[] args) {
        JCommander jCommander = JCommander.newBuilder()
                .addCommand(readDrivesCommand)
                .addCommand(fileChangeCommand)
                .addCommand(fileFindCommand)
                .build();
        jCommander.parse(args);
        String parsedCommand = jCommander.getParsedCommand();
        System.out.println("Command from cli: " + parsedCommand);
        System.out.println("Command params: " + fileFindCommand);
        System.out.println("Command params: " + fileChangeCommand);
        if (READ_DRIVES_COMMAND.equals(parsedCommand)) {
            readDrivesService.readDrives();
        }
    }
}
