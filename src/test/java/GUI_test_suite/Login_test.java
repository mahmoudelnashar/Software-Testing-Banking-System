package GUI_test_suite;

import com.example.banking_system.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class Login_test {

    public String getTextfield(FxRobot robot, String txt){
        TextField t=robot.lookup(txt).queryAs(TextField.class);
        return t.getText();
    }

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Admin Login")
    void test1(FxRobot robot){
        Button btn=robot.lookup("#login_btn").queryAs(Button.class);;
        robot.clickOn("#textf_user").write("admin123");
        robot.clickOn("#textf_pass").write("admin");

        String txt = getTextfield(robot,"#textf_user");
        assertEquals("admin123", txt);
        txt = getTextfield(robot,"#textf_pass");
        assertEquals("admin", txt);

        robot.clickOn(btn);
    }

    @AfterEach
    public void clear() throws Exception {
        FxToolkit.hideStage();
    }



}