package readProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.load("tests.conf");
    }
    String URL = readConfig().getString("url");
    String DEMO_EMAIL = readConfig().getString("usersCreds.demo.email");
    String DEMO_PASSWORD = readConfig().getString("usersCreds.demo.password");
}