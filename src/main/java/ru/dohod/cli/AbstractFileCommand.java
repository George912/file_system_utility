package ru.dohod.cli;

import com.beust.jcommander.Parameter;

public abstract class AbstractFileCommand {
    @Parameter(names = "--searchPath", description = "Absolute search path")
    private String searchPath;
    @Parameter(names = "--searchCondition", description = "Part/full file name")
    private String searchCondition;

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    @Override
    public String toString() {
        return "AbstractFileCommand{" +
                "searchPath='" + searchPath + '\'' +
                ", searchCondition='" + searchCondition + '\'' +
                '}';
    }
}
