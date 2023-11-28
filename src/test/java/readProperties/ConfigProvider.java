package readProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.load("tests.conf");
    }
    String URL = readConfig().getString("url");
    String DEMO_LOGIN = readConfig().getString("usersCreds.demo.login");
    String DEMO_PASSWORD = readConfig().getString("usersCreds.demo.password");
}