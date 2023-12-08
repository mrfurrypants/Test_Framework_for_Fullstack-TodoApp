package readDataFromXLSX;
import com.poiji.bind.Poiji;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class XLSX_to_POJOs {
    public static <T> List<T> xlsxDataToListOfPOJOs(String xlsxFileName, Class<T> POJO){
        String absolutePath = Paths.get("src","test","resources",xlsxFileName).toFile().getAbsolutePath();
        return Poiji.fromExcel(new File(absolutePath), POJO);
    }
}