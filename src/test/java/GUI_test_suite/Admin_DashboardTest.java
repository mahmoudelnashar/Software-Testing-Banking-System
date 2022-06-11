package GUI_test_suite;

import com.example.banking_system.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import com.example.banking_system.services.Account;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class Admin_DashboardTest extends ApplicationTest {
    public String getTextfield(FxRobot robot, String txt){
        TextField t=robot.lookup(txt).queryAs(TextField.class);
        return t.getText();
    }
    Stage st;
    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
        stage.setAlwaysOnTop(true);
        st=stage;
    }


    @BeforeEach
    public void init(FxRobot robot) throws Exception {
        FxToolkit.registerStage(() -> new Stage());
        robot.clickOn("#textf_user").write("admin123");
        robot.clickOn("#textf_pass").write("admin");
        robot.clickOn(robot.lookup("#login_btn").queryAs(Button.class));
    }

    @Test
    @DisplayName("Admin dashboard adding client without all information")
    void admin_dash_test(FxRobot robot){
        Button btn=robot.lookup("#reg_client_btn").queryAs(Button.class);
        robot.clickOn(btn);
        robot.clickOn("#textf_user").write("omar");
        robot.clickOn("#textf_pass").write("1122");
        btn=robot.lookup("#save_btn").queryAs(Button.class);
        robot.clickOn(btn);
        verifyThat(".error", Node::isVisible);
        interact(()->((Stage)((lookup(".error").query())).getScene().getWindow()).close());
        btn=robot.lookup("#cancel_btn").queryAs(Button.class);
        robot.clickOn(btn);
    }

    @Test
    @DisplayName("Admin dashboard adding client with all information")
    void admin_dash_test2(FxRobot robot){
        robot.clickOn(robot.lookup("#reg_client_btn").queryAs(Button.class));
        robot.clickOn("#textf_user").write("omar");
        robot.clickOn("#textf_pass").write("1122");
        robot.clickOn("#textf_mob").write("1122");
        robot.clickOn("#textf_email").write("omar@gmail.com");
        robot.clickOn("#textf_tele").write("1122");
        robot.clickOn("#textf_addr").write("omar");
        robot.clickOn("#textf_occ").write("omar");
        robot.clickOn("#textf_salary").write("1122");
        robot.clickOn("#textf_mart").write("omar");
        robot.clickOn("#textf_deposit_amnt").write("1122");
        robot.clickOn("#textf_name").write("omar");
        robot.clickOn("#textf_ssn").write("1122");

        String txt = getTextfield(robot,"#textf_name");
        assertEquals("omar", txt);
        txt = getTextfield(robot,"#textf_ssn");
        assertEquals("1122", txt);

        robot.clickOn(robot.lookup("#save_btn").queryAs(Button.class));
        verifyThat(".information", Node::isVisible);
        interact(()->((Stage)((lookup(".information").query())).getScene().getWindow()).close());
        robot.clickOn(robot.lookup("#cancel_btn").queryAs(Button.class));
    }


    @Test
    @DisplayName("Admin dashboard deposit")
    void admin_dash_test3(FxRobot robot){
        robot.clickOn(robot.lookup("#deposit_btn").queryAs(Button.class));
        robot.clickOn("#txtf_acc_no").write("1122");
        robot.clickOn("#txtf_amount").write("1022");

        String txt = getTextfield(robot,"#txtf_acc_no");
        assertEquals("1122", txt);
        txt = getTextfield(robot,"#txtf_amount");
        assertEquals("1022", txt);

        robot.clickOn(robot.lookup("#deposit_btn").queryAs(Button.class));
        verifyThat(".information", Node::isVisible);
        interact(()->((Stage)((lookup(".information").query())).getScene().getWindow()).close());
        robot.clickOn(robot.lookup("#cancel_btn").queryAs(Button.class));
    }


    @AfterEach
    public void stop() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    public static void main(String[] args) {
System.out.println(55);
    }


}