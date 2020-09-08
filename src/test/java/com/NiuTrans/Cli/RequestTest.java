package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RequestTest {
    Config config = Settings.getInstance();

    @Test
    public void requestTest() {
        assertEquals(
                "{\"to\":\"en\",\"error_code\":\"13001\",\"from\":\"zh\",\"error_msg\":\"apikey error OR apikey unauthorized OR service package running out\",\"src_text\":\"测试\",\"apikey\":\"531cb65d16b3ed2df2b4c6a6e44171ec\"}",
                Request.post("zh", "en", "测试", config.getUrl("text"), config.getKey()));
    }
}