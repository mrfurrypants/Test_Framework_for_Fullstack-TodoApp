package readProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.load("tests.conf");
    }
    String EXECUTION_MODE = readConfig().getString("execution_mode");
    String BROWSER = readConfig().getString("browser");
    String URL_UI = readConfig().getString("url_ui");
    String URL_API = readConfig().getString("url_api");
    String USER_NAME = readConfig().getString("user_name");
    String VALID_EMAIL = readConfig().getString("usersCreds.validCredentials.email");
    String VALID_PASSWORD = readConfig().getString("usersCreds.validCredentials.password");
    String INVALID_EMAIL = readConfig().getString("usersCreds.invalidCredentials.email");
    String INVALID_PASSWORD = readConfig().getString("usersCreds.invalidCredentials.password");
}