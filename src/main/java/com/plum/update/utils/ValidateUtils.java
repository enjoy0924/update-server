package com.plum.update.utils;

import java.io.File;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

/**
 * Created by G_dragon on 2015/12/31.
 */
public class ValidateUtils {

    public static long getCheckSumForString(String subject){

        if(null == subject || subject.isEmpty())
            return 0;

        Adler32 adler32 = new Adler32();
        adler32.update(subject.getBytes());
        return adler32.getValue();
    }

}
