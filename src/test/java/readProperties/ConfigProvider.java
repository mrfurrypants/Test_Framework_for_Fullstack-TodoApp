package readProperties;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    static Config readConfig(){
        return ConfigFactory.load("tests.conf");
    }
    Config config = readConfig();
    String EXECUTION_MODE = readConfig().getString("execution_mode");
    static String url_ui() {
        if(EXECUTION_MODE.equalsIgnoreCase("docker_run")) {
            return readConfig().getString("url_ui_docker_run");
        } else {
            return readConfig().getString("url_ui_local_run");
        }
    }
    static String url_api() {
        if(EXECUTION_MODE.equalsIgnoreCase("docker_run")) {
            return readConfig().getString("url_api_docker_run");
        } else {
            return readConfig().getString("url_api_local_run");
        }
    }
    String URL_REMOTE = readConfig().getString("url_remote_docker_run");
    String BROWSER = readConfig().getString("browser");
    String USER_NAME = readConfig().getString("user_name");
    String VALID_EMAIL = readConfig().getString("usersCreds.validCredentials.email");
    String VALID_PASSWORD = readConfig().getString("usersCreds.validCredentials.password");
    String INVALID_EMAIL = readConfig().getString("usersCreds.invalidCredentials.email");
    String INVALID_PASSWORD = readConfig().getString("usersCreds.invalidCredentials.password");
}