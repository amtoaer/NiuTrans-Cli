package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SettingsTest {
    Config settings = new Settings();

    @Test
    public void settingsTest() {
        assertEquals("531cb65d16b3ed2df2b4c6a6e44171ec", settings.getKey());
    }
}