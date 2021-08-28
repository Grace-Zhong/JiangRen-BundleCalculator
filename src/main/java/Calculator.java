import java.lang.module.ModuleDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

public class Calculator extends Media{
    private static HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();
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

    public Calculator(Media img, Media audio, Media video) {
        this.img = img;
        this.audio = audio;
        this.video = video;
        this.data.put(img.getMediaName(), img.getTable());
        this.data.put(audio.getMediaName(), audio.getTable());
        this.data.put(video.getMediaName(), video.getTable());
    }

    public void calTotal(int inputImgNum, int inputFlacNum, int inputVidNum) {
        TreeMap<String, BigDecimal> imgRes = img.calSingleType(inputImgNum);
        System.out.println("Image:");
        System.out.println(imgRes);

        TreeMap<String, BigDecimal> audioRes = audio.calSingleType(inputFlacNum);
        System.out.println("Audio:");
        System.out.println(audioRes);

        TreeMap<String, BigDecimal> videoRes = video.calSingleType(inputVidNum);
        System.out.println("Video:");
        System.out.println(videoRes);
    }
}
