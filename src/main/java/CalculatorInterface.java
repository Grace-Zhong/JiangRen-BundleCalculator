import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

public interface CalculatorInterface {
    public static HashMap<String, TreeMap<Integer, BigDecimal>> data = new HashMap<>();

    public void calTotal(int inputImgNum, int inputFlacNum, int inputVidNum);
}
