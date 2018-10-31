package ru.dohod.cli;

import com.beust.jcommander.Parameters;
import org.springframework.stereotype.Component;

@Component("fileChangeCommand")
@Parameters(commandNames = {"-change"}, commandDescription = "Change found files: found file displayed first and add "
        + " an arbitrary line to the beginning of each found file",separators = "=")
public class FileChangeCommand extends AbstractFileCommand{
    @Override
    public String toString() {
        return "FileChangeCommand{" +
                "searchPath='" + searchPath + '\'' +
                ", searchCondition='" + searchCondition + '\'' +
                "} " + super.toString();
    }
}
