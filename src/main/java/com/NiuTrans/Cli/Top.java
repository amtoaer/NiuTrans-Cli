package com.NiuTrans.Cli;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import java.util.List;

public class Top {
    public static void translate(String from, String to, String src, String type) throws Exception {
        Config config = new Settings();
        List<String> validType = List.of("text", "XML", "compare");
        // 使用type参数覆盖配置文件中的type
        String finalType = (type == null) ? config.getType() : type;
        if (!validType.contains(finalType)) {
            throw new Exception("invalid type");
        }
        String result = Request.post(from, to, src, config.getUrl(finalType), config.getKey());
        JSONObject json = JSONUtil.parseObj(result);
        // 检查错误并throw
        if (json.get("error_msg") != null) {
            String errorMessage = (String) json.get("error_msg");
            throw new Exception(errorMessage);
        }
        // 分别解析
        switch (finalType) {
            case "text" -> textParser(json);
            case "XML" -> XMLParser(json);
            case "compare" -> bilingualParser(json);
        }
    }

    private static void textParser(JSONObject json) {
        System.out.println(Ansi.colorize("文本翻译：", Attribute.YELLOW_TEXT()));
        System.out.println(Ansi.colorize((String) json.get("tgt_text"), Attribute.RED_TEXT()));
    }

    private static void XMLParser(JSONObject json) throws Exception {
        // XML接口比起普通的文本翻译，可以防止翻译XML标签
        // 使用hutool-core中的XmlUtil进行处理，格式化输出XML字符串
        String target = (String) json.get("tgt_text");
        var document = XmlUtil.parseXml(target);
        System.out.println(Ansi.colorize("XML翻译：", Attribute.YELLOW_TEXT()));
        // 对XML文档进行格式化输出
        System.out.println(Ansi.colorize(XmlUtil.format(document), Attribute.RED_TEXT()));
    }

    private static void bilingualParser(JSONObject json) throws Exception {
        json = (JSONObject) json.get("align");
        // 据测试，在目标语言与源语言相同时，返回结果为{from:...,to:...,tgt_text:...}
        if (json == null) {
            throw new Exception("please ensure that target language is different from source language");
        }
        System.out.println(Ansi.colorize("双语对照翻译：", Attribute.YELLOW_TEXT()));
        int i = 0;
        JSONObject row, sentence;
        while ((row = (JSONObject) json.get(String.valueOf(i))) != null) {
            int j = 0;
            while ((sentence = (JSONObject) row.get(String.valueOf(j))) != null) {
                System.out.println(Ansi.colorize((String) sentence.get("src"), Attribute.RED_TEXT()));
                System.out.println(Ansi.colorize((String) sentence.get("tgt"), Attribute.CYAN_TEXT()));
                j++;
            }
            i++;
        }
    }
}