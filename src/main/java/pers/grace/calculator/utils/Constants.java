package pers.grace.calculator.utils;

import com.google.common.collect.ImmutableMap;

import java.math.BigDecimal;
import java.util.Map;

public class Constants {
    public static final String IMAGE_CODE = "IMG";
    public static final String AUDIO_CODE = "FLAC";
    public static final String VIDEO_CODE = "VID";

    public static Map<String, Map<Integer, BigDecimal>> infoTable = new ImmutableMap.Builder<String, Map<Integer,
            BigDecimal>>()
            .put(IMAGE_CODE, new ImmutableMap.Builder<Integer, BigDecimal>()
                    .put(5, new BigDecimal("450"))
                    .put(10, new BigDecimal("800")).build())
            .put(AUDIO_CODE, new ImmutableMap.Builder<Integer, BigDecimal>()
                    .put(3, new BigDecimal("427.5"))
                    .put(6, new BigDecimal("810"))
                    .put(9, new BigDecimal("1147.5"))
                    .build())
            .put(VIDEO_CODE, new ImmutableMap.Builder<Integer, BigDecimal>()
                    .put(3, new BigDecimal("570"))
                    .put(5, new BigDecimal("900"))
                    .put(9, new BigDecimal("1530"))
                    .build())
            .build();

}
