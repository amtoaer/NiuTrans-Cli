package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SettingsTest {
    Settings settings = new Settings();

    @Test
    public void settingsTest() {
        assertEquals("1423235123123", settings.getKey());
        assertEquals("https://free.niutrans.com/NiuTransServer/translation", settings.getUrl());
    }
}