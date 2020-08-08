package com.NiuTrans.Cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class App {
    @Parameter(names = { "from" }, description = "Language of source text")
    private static String from = "auto";
    @Parameter(names = { "to" }, description = "Language of target text")
    private static String to = "en";
    @Parameter(description = "Source text")
    private static String src = "";
    @Parameter(names = { "via" }, description = "Translation method")
    private static String type = null;
    @Parameter(names = { "help", "--help", "-help", "-h" }, help = true, description = "Print these info and exit")
    private static boolean help;

    public static void main(String[] args) {
        App main = new App();
        var commander = JCommander.newBuilder().addObject(main).build();
        commander.parse(args);
        if (help) {
            commander.usage();
            return;
        }
        try {
            Parse.parse(from, to, src, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
