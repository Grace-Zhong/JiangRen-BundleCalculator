import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

public class CalculateSingleTypeTest {
    private static HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();
    private static final String imageCode = "IMG";
    private static final String audioCode = "FLAC";
    private static final String videoCode = "VID";

    // initialize data table
    public static void createDataTable() {
        TreeMap<Integer, BigDecimal> imageTable = new TreeMap<>();
        imageTable.put(5,new BigDecimal(450));
        imageTable.put(10,new BigDecimal(800));
        data.put(imageCode, imageTable);
        TreeMap<Integer, BigDecimal> audioTable = new TreeMap<>();
        audioTable.put(3,new BigDecimal(427.5));
        audioTable.put(6,new BigDecimal(810));
        audioTable.put(9,new BigDecimal(1147.5));
        data.put(audioCode, audioTable);
        TreeMap<Integer, BigDecimal> videoTable = new TreeMap<>();
        videoTable.put(3,new BigDecimal(570));
        videoTable.put(5,new BigDecimal(900));
        videoTable.put(9,new BigDecimal(1530));
        data.put(videoCode, videoTable);
    }

    @Test
    public void testImg() {
        createDataTable();
        CalculateSingleType testImg = new CalculateSingleType();
        TreeMap<String, BigDecimal> resImg = testImg.calSingleType(10, data.get(imageCode));
        System.out.println(resImg);
    }

    @Test
    public void testAudio() {
        createDataTable();
        CalculateSingleType testAudio = new CalculateSingleType();
        TreeMap<String, BigDecimal> resAudio = testAudio.calSingleType(15, data.get(audioCode));
        System.out.println(resAudio);
    }

    @Test
    public void testVid() {
        createDataTable();
        CalculateSingleType testVid = new CalculateSingleType();
        TreeMap<String, BigDecimal> resVid = testVid.calSingleType(18, data.get(videoCode));
        System.out.println(resVid);
    }


}
