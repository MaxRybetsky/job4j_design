package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 10;
        short sh = 500;
        int i = 100000;
        long l = 10000000000L;
        boolean boo = true;
        double d = 109.89;
        float f = 12.23F;
        char ch = 'c';
        LOG.debug("Variables information: \n"
                        + "b = {}\n"
                        + "sh = {}\n"
                        + "i = {}\n"
                        + "l = {}\n"
                        + "boo = {}\n"
                        + "d = {}\n"
                        + "f = {}\n"
                        + "ch = {}\n",
                b, sh, i, l, boo, d, f, ch);
    }
}