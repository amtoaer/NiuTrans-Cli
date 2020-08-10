package com.NiuTrans.Cli;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.JCommander;

public class App {
    @Parameter(names = { "help", "-h", "--help" }, help = true)
    private static boolean help;

    public static void main(String[] args) {
        JCommander jc = JCommander.newBuilder().addObject(new App()).addCommand(new Translate())
                .addCommand(new Customize()).build();
        jc.parse(args);
        if (help) {
            jc.usage();
            return;
        }
        String subCommand = jc.getParsedCommand();
        switch (subCommand) {
            case "translate" -> Translate.run();
            case "customize" -> Customize.run();
        }
    }
}

@Parameters(commandNames = { "translate" }, commandDescription = "Translate content")
class Translate {
    @Parameter(names = { "from" }, description = "Language of source text")
    public static String from = "auto";
    @Parameter(names = { "to" }, description = "Language of target text")
    public static String to = "en";
    @Parameter(description = "srcText")
    public static String src = "";
    @Parameter(names = { "via" }, description = "Translation method(accept text/XML/compare)")
    public static String type = null;

    public static void run() {
        try {
            Top.translate(from, to, src, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@Parameters(commandNames = { "customize" }, commandDescription = "customize translation")
class Customize {
    @Parameter(names = { "from" }, description = "Source text")
    public static String src = "";
    @Parameter(names = { "to" }, description = "Target text")
    public static String tgt = "";
    @Parameter(names = { "fromLanguage", "fl" }, description = "Language of source text")
    public static String from = "auto";
    @Parameter(names = { "toLanguage", "tl" }, description = "Language of target text")
    public static String to = "en";
    @Parameter(description = "Customize method(accept word/sentence)")
    public static String type = null;

    public static void run() {
        try {
            Top.customize(from, to, src, tgt, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}