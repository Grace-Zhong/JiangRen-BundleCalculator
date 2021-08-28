import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.logging.Logger;

public class CalculatorTest {
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";

    private static TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
    private static TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
    private static TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();

    static final Logger logger = Logger.getLogger("Test Logging...");

    @BeforeEach
    void init() {
        imageTable.clear();
        imageTable.put(5,new BigDecimal(450));
        imageTable.put(10,new BigDecimal(800));

        audioTable.clear();
        audioTable.put(3,new BigDecimal(427.5));
        audioTable.put(6,new BigDecimal(810));
        audioTable.put(9,new BigDecimal(1147.5));

        videoTable.clear();
        videoTable.put(3,new BigDecimal(570));
        videoTable.put(5,new BigDecimal(900));
        videoTable.put(9,new BigDecimal(1530));
    }

    @Test
    public void testCalTotal() {
        Calculator calculator = new Calculator(
                new Media(imageCode, imageTable),
                new Media(audioCode, audioTable),
                new Media(videoCode, videoTable)
        );
        int inputImg = 10;
        int inputFlac = 15;
        int inputVid = 13;

        TreeMap<String, TreeMap<String, BigDecimal>> res = calculator.calTotal(inputImg, inputFlac, inputVid);
        logger.info(calculator.printRes(inputImg, inputFlac, inputVid));
    }
}
