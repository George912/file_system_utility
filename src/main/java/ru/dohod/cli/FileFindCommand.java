package ru.dohod.cli;

import com.beust.jcommander.Parameters;
import org.springframework.stereotype.Component;

@Component("fileFindCommand")
@Parameters(commandNames = {"-find"}, commandDescription = "File find by its name: full match, partial match"
        , separators = "=")
public class FileFindCommand extends AbstractFileCommand {
    @Override
    public String toString() {
        return "FileFindCommand{" +
                "searchPath='" + searchPath + '\'' +
                ", searchCondition='" + searchCondition + '\'' +
                "} " + super.toString();
    }
}
