package ru.dohod.cli;

import com.beust.jcommander.Parameter;

public abstract class AbstractFileCommand {
    @Parameter(names = "--searchPath", description = "Absolute search path")
    protected String searchPath;
    @Parameter(names = "--searchCondition", description = "Part/full file name")
    protected String searchCondition;
}
