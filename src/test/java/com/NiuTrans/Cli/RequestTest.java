package com.NiuTrans.Cli;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequestTest {
    @Test
    public void requestTest() {
        assertEquals("{\"tgt_text\":\"测试\",\"to\":\"zh\",\"from\":\"en\"}", Request.post("en", "zh", "test"));
        assertEquals(
                "{\"to\":\"zh\",\"error_code\":\"10000\",\"from\":\"en\",\"error_msg\":\"Input is empty\",\"src_text\":\"\"}",
                Request.post("en", "zh", ""));
    }
}