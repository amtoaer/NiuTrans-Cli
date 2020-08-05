package com.NiuTrans.Cli;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.Properties;

public class Settings implements Config {
    private final Properties props;

    public Settings() {
        props = new Properties();
        File config = new File(System.getProperty("user.home"), ".NiuTrans");
        try (InputStream defaultConfig = new FileInputStream(new File("src", "settings.properties"))) {
            // 读取默认配置
            props.load(defaultConfig);
            // 判断用户配置是否存在，不存在则写入默认配置
            if (!config.exists()) {
                // 因为流只能使用一次，故在此处重新打开了src/settings.properties
                try (InputStream input = new FileInputStream(new File("src", "settings.properties"));
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
        return this.props.getProperty("url");
    }
}

interface Config {
    String getKey();

    String getUrl();
}