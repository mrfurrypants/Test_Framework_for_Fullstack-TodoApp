package readProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();
    static Config readConfig(){
        return ConfigFactory.load("tests.conf");
    }
    String URL = readConfig().getString("url");
    String USER_NAME = readConfig().getString("user_name");
    String VALID_EMAIL = readConfig().getString("usersCreds.validCredentials.email");
    String VALID_PASSWORD = readConfig().getString("usersCreds.validCredentials.password");
    String INVALID_EMAIL = readConfig().getString("usersCreds.invalidCredentials.email");
    String INVALID_PASSWORD = readConfig().getString("usersCreds.invalidCredentials.password");
}