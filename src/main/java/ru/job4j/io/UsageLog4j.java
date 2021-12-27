package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        String fileTrace = "This is trace file";
        String pathTrace = "./project/trace.txt";
        String info = "Print log";
        int number = 2;
        String warning = "Dont delete file";
        String fileWarning = "txt.txt";
        String error = "Connection refused to host";
        String errorName = "https://job4j.ru/";
        LOG.trace("Trace info: {}, {}", fileTrace, pathTrace);
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.info("You need to: {} {} times", info, number);
        LOG.warn("Warning! {}: {}", warning, fileWarning);
        LOG.error("Error! {}: {}", error, errorName);
    }
}
