package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SettingsTest {
    Config settings = Settings.getInstance();

    @Test
    public void settingsTest() {
        assertEquals("https://free.niutrans.com/NiuTransServer/translation", settings.getUrl("text"));
    }
}