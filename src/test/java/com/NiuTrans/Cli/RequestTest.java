package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RequestTest {
    Config config = new Settings();

    @Test
    public void requestTest() {
        assertEquals("{\"tgt_text\":\"Test\",\"to\":\"en\",\"from\":\"zh\"}",
                Request.post("zh", "en", "测试", config.getUrl("text"), config.getKey()));
    }
}