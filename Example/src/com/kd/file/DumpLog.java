package com.kd.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DumpLog {

    private final FileWriter fw;
    private final PrintWriter out;
    private final boolean append_to_file = true;
    private final String separator = File.separator;
    private final Date timenow = new Timestamp(System.currentTimeMillis());

    public DumpLog() throws IOException {
        fw = new FileWriter(getFileName(), append_to_file);
        out = new PrintWriter(fw);
    }

    public void println(String str) {
        out.printf("%s" + "%n", str);
    }

    public void close() {
        out.close();
    }

    private String getFileName() {
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String filename = "." + separator + "output" + separator + "log-" + dateformat.format(timenow) + ".txt";
        return filename;
    }
}
