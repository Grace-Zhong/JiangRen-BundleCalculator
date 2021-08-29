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
public class Calculator {

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
    public static TreeMap<String, TreeMap<String, BigDecimal>> res = new TreeMap<>();

    public Calculator(Media img, Media audio, Media video) {
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

        if (inputImgNum > 0 && inputFlacNum > 0 && inputVidNum > 0) {
            TreeMap<String, BigDecimal> imgRes = img.calSingleType(inputImgNum);
            res.put("IMG", imgRes);

            TreeMap<String, BigDecimal> audioRes = audio.calSingleType(inputFlacNum);
            res.put("FLAC", audioRes);

            TreeMap<String, BigDecimal> videoRes = video.calSingleType(inputVidNum);
            res.put("VID", videoRes);
        } else {
            final Logger logger = Logger.getLogger("Logging single media...");
            logger.info("Input should be positive integer!");
        }

        return res;
    }

}
