package ru.dohod.cli;

import com.beust.jcommander.Parameters;
import org.springframework.stereotype.Component;

@Component("fileFindCommand")
@Parameters(commandNames = {"-search"}, commandDescription = "File search by its name: full match, partial match"
        , separators = "=")
public class FileSearchCommand extends AbstractFileCommand {
    @Override
    public String toString() {
        return "FileSearchCommand{} " + super.toString();
    }
}
