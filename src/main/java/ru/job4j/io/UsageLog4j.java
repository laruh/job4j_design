package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        char trace = 'f';
        byte number = 2;
        boolean bool = false;
        float flo = 4;
        short shor = 3;
        long lon = 404;
        double doubleNumber = 3200;
        String error = "Connection refused to host";
        LOG.trace("Trace info: {}, {}", trace, bool);
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.info("Use these numbers: {} and {}", flo, number);
        LOG.warn("Warning! {}: {}", shor, doubleNumber);
        LOG.error("Error! {}: {}", error, lon);
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
