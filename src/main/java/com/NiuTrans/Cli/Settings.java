package com.NiuTrans.Cli;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

public class Settings implements Config {
    private final Properties props;
    private final Map<String, String> map;

    public Settings() {
        map = new HashMap<>();
        // 文本翻译接口
        map.put("text", "https://free.niutrans.com/NiuTransServer/translation");
        // XML格式接口
        map.put("XML", "https://free.niutrans.com/NiuTransServer/translationXML");
        // 双语对照接口
        map.put("compare", "https://free.niutrans.com/NiuTransServer/translationAlign");
        props = new Properties();
        File config = new File(System.getProperty("user.home"), ".NiuTrans");
        try (InputStream defaultConfig = this.getClass().getClassLoader().getResourceAsStream("settings.properties")) {
            // 读取默认配置
            props.load(defaultConfig);
            // 判断用户配置是否存在，不存在则写入默认配置
            if (!config.exists()) {
                // 因为流只能使用一次，故在此处重新打开了settings.properties
                try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("settings.properties");
                        OutputStream output = new FileOutputStream(config)) {
                    input.transferTo(output);
                }
            }
            // 覆盖读取用户配置
            props.load(new FileInputStream(config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getKey() {
        return this.props.getProperty("api-key");
    }

    @Override
    public String getUrl() {
        return this.map.get(this.props.getProperty("type"));
    }

    @Override
    public String getUrl(String type) {
        return this.map.get(type);
    }

    @Override
    public String getType() {
        return this.props.getProperty("type");
    }
}

interface Config {
    String getKey();

    String getUrl();

    String getUrl(String type);

    String getType();
}