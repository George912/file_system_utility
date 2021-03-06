package ru.dohod.cli;

import com.beust.jcommander.Parameters;
import org.springframework.stereotype.Component;

@Component("readDrivesCommand")
@Parameters(commandNames = {"-readDrives"}, commandDescription = "Reading the list of local drives and displaying them"
        + "on the screen, without network ones, but including flash drives and CD / DVD.")
public class ReadDrivesCommand {
}
