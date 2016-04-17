package com.iydsj.sw.common.test;

import com.iydsj.sw.common.utils.BarCodeUtils;
import com.iydsj.sw.common.utils.BarCodeUtils.EncodeResult;
import com.iydsj.sw.common.utils.BarCodeUtils.DecodeResult;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by tao on 2/18/16.
 */
public class BarCodeUtilsTest {

    @Test
    public void testEncode() throws Exception {
        for (int i = 0; i < 1000; i++) {
            String phone = randomPhone();
            EncodeResult encodeResult = BarCodeUtils.encode(phone);
            DecodeResult decodeResult = BarCodeUtils.decode(encodeResult.barCode);
            assertEquals(phone, decodeResult.phone);
            assertEquals(encodeResult.code, decodeResult.code);
        }
    }

    private String randomPhone() {
        Random rand = new Random(System.nanoTime());
        StringBuilder phoneBuilder = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            phoneBuilder.append((byte) rand.nextInt(10));
        }
        return phoneBuilder.toString();
    }
}