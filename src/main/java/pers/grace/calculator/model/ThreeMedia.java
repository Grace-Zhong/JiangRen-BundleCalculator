package pers.grace.calculator.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

@Data
@Accessors(chain = true, fluent = true)
public class ThreeMedia {
    private Media img;
    private Media audio;
    private Media video;
    private HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();

    public ThreeMedia() {
        final String IMAGE_CODE = "IMG";
        final String AUDIO_CODE = "FLAC";
        final String VIDEO_CODE = "VID";

        TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();

        imageTable.put(5, new BigDecimal("450"));
        imageTable.put(10, new BigDecimal("800"));

        audioTable.put(3, new BigDecimal("427.5"));
        audioTable.put(6, new BigDecimal("810"));
        audioTable.put(9, new BigDecimal("1147.5"));

        videoTable.put(3, new BigDecimal("570"));
        videoTable.put(5, new BigDecimal("900"));
        videoTable.put(9, new BigDecimal("1530"));

        this.img = new Media(IMAGE_CODE, imageTable);
        this.audio = new Media(AUDIO_CODE, audioTable);
        this.video = new Media(VIDEO_CODE, videoTable);

        data.put(IMAGE_CODE, imageTable);
        data.put(AUDIO_CODE, audioTable);
        data.put(VIDEO_CODE, videoTable);
    }
}
