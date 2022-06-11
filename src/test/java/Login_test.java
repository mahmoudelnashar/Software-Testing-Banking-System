import static org.junit.jupiter.api.Assertions.*;

import com.example.banking_system.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
class Login_test {
    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void test1(FxRobot robot){
        Button btn=robot.lookup("#login_btn").queryAs(Button.class);
        robot.clickOn("#textf_user").write("admin123");
        robot.clickOn("#textf_pass").write("admin");
        robot.clickOn(btn);
        btn=robot.lookup("#reg_client_btn").queryAs(Button.class);
        robot.clickOn(btn);
    }

}