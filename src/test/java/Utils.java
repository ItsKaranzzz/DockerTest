import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {
    public static Properties getConfigs() {
        Properties properties = null;
        {
            try {
                FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
                try {
                    properties = new Properties();
                    properties.load(fileInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Properties file not found!");
            }
        }
        return properties;
    }
}
