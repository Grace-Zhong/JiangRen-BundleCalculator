package pers.grace.calculator.algorithm;

import lombok.*;
import lombok.experimental.Accessors;
import pers.grace.calculator.algorithm.MediaCalculator;
import pers.grace.calculator.model.Media;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class ThreeMediaCalculator {

    private Media img;
    private Media audio;
    private Media video;
    private HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();

    public ThreeMediaCalculator(Media img, Media audio, Media video) {
        img(img).audio(audio).video(video);
        data.put(img.name(), img.table());
        data.put(audio.name(), audio.table());
        data.put(video.name(), video.table());
    }

    public TreeMap<String, TreeMap<String, BigDecimal>> calTotal(int inputImgNum, int inputFlacNum, int inputVidNum) {
        TreeMap<String, TreeMap<String, BigDecimal>> res = new TreeMap<>();

        if (inputImgNum > 0 && inputFlacNum > 0 && inputVidNum > 0) {
            MediaCalculator imgCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> imgRes = imgCalculator.calSingleType(img, inputImgNum);
            res.put("IMG", imgRes);

            MediaCalculator audioCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> audioRes = audioCalculator.calSingleType(audio, inputFlacNum);
            res.put("FLAC", audioRes);

            MediaCalculator videoCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> videoRes = videoCalculator.calSingleType(video, inputVidNum);
            res.put("VID", videoRes);
        } else {
            final Logger logger = Logger.getLogger("Logging single media...");
            logger.info("Input should be positive integer!");
        }

        return res;
    }

}
