package ru.dohod.app;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.dohod.api.service.UtilityService;

import java.io.IOException;

/**
 * Command line arguments samples:
 * -readDrives
 * -search --searchPath=F:\Git\github\test_tasks\file_system_utility\tmp --searchCondition=pom
 * -change --searchPath=F:\Git\github\test_tasks\file_system_utility\tmp --searchCondition=pom
 */
public class Main {
    private static final String CONTEXT_CONFIG_PATH = "classpath:context.xml";

    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load(CONTEXT_CONFIG_PATH);
        ctx.refresh();
        UtilityService utilityService = ctx.getBean("utilityService", UtilityService.class);
        utilityService.start(args);
    }
}
