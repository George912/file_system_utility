package ru.dohod.app;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.dohod.api.service.UtilityService;

/**
 * -readDrives
 * -find --searchPath=path --searchCondition=cond
 * -change --searchPath=path --searchCondition=cond
 */
public class Main {
    private static final String CONTEXT_CONFIG_PATH = "classpath:context.xml";

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load(CONTEXT_CONFIG_PATH);
        ctx.refresh();
        UtilityService utilityService = ctx.getBean("utilityService", UtilityService.class);
        utilityService.start(args);
    }
}
