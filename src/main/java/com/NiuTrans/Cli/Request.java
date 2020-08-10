package com.NiuTrans.Cli;

import java.util.Map;
import cn.hutool.http.HttpUtil;
import java.util.HashMap;

public class Request {
    public static String post(String from, String to, String src, String url, String api_key) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("apikey", api_key);
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("src_text", src);
        return HttpUtil.post(url, paramMap);
    }

    public static String post(String fromText, String toText, String srcText, String tgtText, String url,
            String api_key) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromText", fromText);
        paramMap.put("toText", toText);
        paramMap.put("apikey", api_key);
        paramMap.put("srcText", srcText);
        paramMap.put("tgtText", tgtText);
        return HttpUtil.post(url, paramMap);
    }
}