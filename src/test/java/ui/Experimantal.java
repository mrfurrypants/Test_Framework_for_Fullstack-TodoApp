package ui;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import readDataFromXLSX.tasks_providerPOJO1;
import ui.Page_Objects.TasksPage;

import java.util.stream.Stream;

import prepareTestEnvironment.PrepareTestEnvironment;
import static readDataFromXLSX.XLSX_to_POJOs.xlsxDataToListOfPOJOs;

public class Experimantal {

    @Test
    public void chch() {
        Selenide.open("http://localhost/#/tasks");

        var cookiePayload = PrepareTestEnvironment.getJsonResponseAsMap();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(cookiePayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Selenide.executeJavaScript("localStorage.setItem('loggedAppUser', '"+ jsonPayload +"');");
        Selenide.open("http://localhost/#/tasks");

        TasksPage.clickProject_button();
    }
}
