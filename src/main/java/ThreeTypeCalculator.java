import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Logger;

@NoArgsConstructor
@ToString
@Accessors(chain = true, fluent = true)
public class ThreeTypeCalculator {

    @Getter
    @Setter
    private Media img;
    @Getter
    @Setter
    private Media audio;
    @Getter
    @Setter
    private Media video;
    @Getter
    @Setter
    private static HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();

    public ThreeTypeCalculator(Media img, Media audio, Media video) {
        img(img).audio(audio).video(video);
        this.data.put(img.name(), img.table());
        this.data.put(audio.name(), audio.table());
        this.data.put(video.name(), video.table());
    }

    /***
     * Calculate the cheapest solution of number of bundle.
     * @param inputImgNum number of image that users need
     * @param inputFlacNum number of audio that users need
     * @param inputVidNum number of video that users need
     * @return A table of the cheapest solutions for three types of media respectively
     */
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
