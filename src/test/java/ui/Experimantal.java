package ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Cookie;
import readDataFromXLSX.tasks_providerPOJO1;
import readProperties.ConfigProvider;
import ui.Page_Objects.ProjectsPage;
import ui.Page_Objects.TasksPage;

import java.util.stream.Stream;

import static ui.Page_Objects.LoginPage.getJsonResponseAsMap;
import static readDataFromXLSX.XLSX_to_POJOs.xlsxDataToListOfPOJOs;

public class Experimantal {
    public static Stream<tasks_providerPOJO1> meth(){
        return xlsxDataToListOfPOJOs("tasks_provider.xlsx", tasks_providerPOJO1.class).stream();
    }
    @ParameterizedTest(name = "Invocation N°{index}")
    @MethodSource("meth")
    @Tag("DataDrivenTest")
    @DisplayName("Simple Excel Test")
    public void exceltest(tasks_providerPOJO1 row){
        System.out.println(row.getDue_date());
    }
    @Test
    public void chch() {
        Selenide.open("http://localhost/#/tasks");

        var cookiePayload = getJsonResponseAsMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(cookiePayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Selenide.executeJavaScript("localStorage.setItem('loggedAppUser', '"+ jsonPayload +"');");
        Selenide.open("http://localhost/#/tasks");
        TasksPage tasksPage = new TasksPage();
        tasksPage.clickProject_button();
    }
}
