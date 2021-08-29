import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.TreeMap;

public class MediaCalculatorTest {
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";

    @Test
    public void testCalSingleType() {
        TreeMap<Integer, BigDecimal> mediaTable = new TreeMap<>();
        mediaTable.put(5, new BigDecimal(450));
        mediaTable.put(10, new BigDecimal(800));

        Media media = new Media(imageCode, mediaTable);
        MediaCalculator singleCalculator = new MediaCalculator();
        TreeMap<String, BigDecimal> res = singleCalculator.calSingleType(media,50);
    }
}
