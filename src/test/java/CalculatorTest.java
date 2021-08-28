import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

public class CalculatorTest {
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";

    @Test
    public void testCalTotal() {
        TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        imageTable.put(5,new BigDecimal(450));
        imageTable.put(10,new BigDecimal(800));

        TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        audioTable.put(3,new BigDecimal(427.5));
        audioTable.put(6,new BigDecimal(810));
        audioTable.put(9,new BigDecimal(1147.5));

        TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();
        videoTable.put(3,new BigDecimal(570));
        videoTable.put(5,new BigDecimal(900));
        videoTable.put(9,new BigDecimal(1530));

        Calculator calculator = new Calculator(
                new Media(imageCode, imageTable),
                new Media(audioCode, audioTable),
                new Media(videoCode, videoTable)
        );
        calculator.calTotal(10, 15, 13);

    }
}
