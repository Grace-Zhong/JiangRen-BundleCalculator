import java.lang.module.ModuleDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Calculator{
    private static HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();
    public static TreeMap<String, TreeMap<String, BigDecimal>> res = new TreeMap<>();
    private Media img;
    private Media audio;
    private Media video;

    public static HashMap<String, TreeMap<Integer, BigDecimal>> getData() {
        return data;
    }

    public static void setData(HashMap<String, TreeMap<Integer, BigDecimal>> data) {
        Calculator.data = data;
    }

    public Media getImg() {
        return img;
    }

    public void setImg(Media img) {
        this.img = img;
    }

    public Media getAudio() {
        return audio;
    }

    public void setAudio(Media audio) {
        this.audio = audio;
    }

    public Media getVideo() {
        return video;
    }

    public void setVideo(Media video) {
        this.video = video;
    }

    public Calculator() {
        this.img = null;
        this.audio = null;
        this.video = null;
        this.data = null;
    }

    public Calculator(Media img, Media audio, Media video) {
        setImg(img);;
        setAudio(audio);
        setVideo(video);
        this.data.put(img.getMediaName(), img.getTable());
        this.data.put(audio.getMediaName(), audio.getTable());
        this.data.put(video.getMediaName(), video.getTable());
    }

    public TreeMap<String, TreeMap<String, BigDecimal>> calTotal(int inputImgNum, int inputFlacNum, int inputVidNum) {

        TreeMap<String, BigDecimal> imgRes = getImg().calSingleType(inputImgNum);
        res.put("IMG", imgRes);

        TreeMap<String, BigDecimal> audioRes = getAudio().calSingleType(inputFlacNum);
        res.put("FLAC", audioRes);

        TreeMap<String, BigDecimal> videoRes = getVideo().calSingleType(inputVidNum);
        res.put("VID", videoRes);

        return res;
    }

    public String printRes(int inImg, int inFlac, int inVid) {
        final String total = "TOTAL";
        String output = "";
        for (Map.Entry<String, TreeMap<String, BigDecimal>> entry:res.entrySet()) {
            output += String.valueOf(inImg) + " " + entry.getKey()
                    + " $" + res.get(entry.getKey()).get(total) + "\n";
            for (Map.Entry<String, BigDecimal> innerEntry:res.get(entry.getKey()).entrySet()) {
                if (!innerEntry.getKey().equals(res.get(entry.getKey()).lastKey())
                        && innerEntry.getValue().compareTo(new BigDecimal(0)) != 0) {
                    BigDecimal cost = innerEntry.getValue()
                            .multiply(data.get(entry.getKey()).get(Integer.valueOf(innerEntry.getKey())));

                    output += "  " + innerEntry.getValue() + " x " + innerEntry.getKey()
                            + " $" + cost + "\n";
                }
            }
        }
        return output;
    }
}
