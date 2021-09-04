package pers.grace.calculator.algorithm;

import pers.grace.calculator.model.ThreeMedia;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.logging.Logger;

public class ThreeMediaCalculator {

    public TreeMap<String, TreeMap<String, BigDecimal>> calTotal(ThreeMedia threeMedia, int inputImgNum,
                                                                 int inputFlacNum,
                                                                 int inputVidNum) {
        TreeMap<String, TreeMap<String, BigDecimal>> res = new TreeMap<>();

        if (inputImgNum > 0 && inputFlacNum > 0 && inputVidNum > 0) {
            MediaCalculator imgCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> imgRes = imgCalculator.calSingleType(threeMedia.img(), inputImgNum);
            res.put("IMG", imgRes);

            MediaCalculator audioCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> audioRes = audioCalculator.calSingleType(threeMedia.audio(), inputFlacNum);
            res.put("FLAC", audioRes);

            MediaCalculator videoCalculator = new MediaCalculator();
            TreeMap<String, BigDecimal> videoRes = videoCalculator.calSingleType(threeMedia.video(), inputVidNum);
            res.put("VID", videoRes);
        } else {
            final Logger logger = Logger.getLogger("Logging single media...");
            logger.info("Input should be positive integer!");
        }

        return res;
    }

}
