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
    private final File config;
    private static Config instance;

    private Settings() throws Exception {
        map = new HashMap<>();
        props = new Properties();
        config = new File(System.getProperty("user.home"), ".NiuTrans");

        // 文本翻译接口
        map.put("text", "https://free.niutrans.com/NiuTransServer/translation");
        // XML格式接口
        map.put("XML", "https://free.niutrans.com/NiuTransServer/translationXML");
        // 双语对照接口
        map.put("compare", "https://free.niutrans.com/NiuTransServer/translationAlign");
        // 术语词典接口
        map.put("word", "https://apis.niutrans.com/NiuTransServerDict/addDictionary");
        // 翻译记忆接口
        map.put("sentence", "https://apis.niutrans.com/NiuTransServerDict/addMemoryStore");

        initDefaultConfig();
        readConfig();
    }

    private void initDefaultConfig() throws Exception {
        if (!config.exists()) {
            try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("settings.properties");
                    OutputStream output = new FileOutputStream(config)) {
                input.transferTo(output);
            }
        }
    }

    private void readConfig() throws Exception {
        try (InputStream defaultConfig = this.getClass().getClassLoader().getResourceAsStream("settings.properties");
                InputStream customConfig = new FileInputStream(config)) {
            props.load(defaultConfig);
            props.load(customConfig);
        }
    }

    private void saveConfig() throws Exception {
        try (OutputStream output = new FileOutputStream(config)) {
            this.props.store(output, null);
        }
    }

    private void setProperties(String key, String value) throws Exception {
        if (!this.props.containsKey(key)) {
            throw new Exception(String.format("key %s is not found", key));
        }
        this.props.setProperty(key, value);
    }

    public static Config getInstance() {
        if (instance == null) {
            try {
                instance = new Settings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
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

    @Override
    public void setPropertiesAndSave(String key, String value) throws Exception {
        setProperties(key, value);
        saveConfig();
    }

    @Override
    public String getProperties(String key) throws Exception {
        if (!this.props.containsKey(key)) {
            throw new Exception(String.format("key %s not found", key));
        }
        return this.props.getProperty(key);
    }
}

interface Config {

    void setPropertiesAndSave(String key, String value) throws Exception;

    String getProperties(String key) throws Exception;

    String getKey();

    String getUrl();

    String getUrl(String type);

    String getType();
}