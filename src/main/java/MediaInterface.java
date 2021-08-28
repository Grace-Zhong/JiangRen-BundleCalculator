import java.math.BigDecimal;
import java.util.TreeMap;

public interface MediaInterface {
    public static final String total = "TOTAL";
    public String mediaName = "";
    public TreeMap<Integer, BigDecimal> table = null;

    // getter & setter
    public String getMediaName();
    public void setMediaName(String mediaName);
    public TreeMap<Integer, BigDecimal> getTable();
    public void setTable(TreeMap<Integer, BigDecimal> table);

    public TreeMap<String, BigDecimal> calSingleType(int inputNum);
}
