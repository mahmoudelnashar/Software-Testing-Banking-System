import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

import com.example.banking_system.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ExtendWith(ApplicationExtension.class)
public class Client_Dashboard_test extends ApplicationTest {
    public void cleartxt(FxRobot robot, String s){

        robot.push(KeyCode.CONTROL,KeyCode.A);
        robot.push(KeyCode.BACK_SPACE);
    }

    Stage ts;
    StackPane sceneRoot;

    @BeforeEach
    public void init(FxRobot robot) throws Exception {
        FxToolkit.registerStage(() -> new Stage());
        robot.clickOn("#textf_user").write("omar");
        robot.clickOn("#textf_pass").write("1122");
        robot.clickOn(robot.lookup("#login_btn").queryAs(Button.class));
    }

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
        stage.setAlwaysOnTop(true);
        ts=stage;
    }

    @Test
    @DisplayName("Check balance and transactions")
    void client_dash_test(FxRobot robot){
        Button btn=robot.lookup("#balance_btn").queryAs(Button.class);
        robot.clickOn(btn);
        robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));
        btn=robot.lookup("#history_btn").queryAs(Button.class);
        robot.clickOn(btn);
        robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));

    }

    @Test
    @DisplayName("Transfer money without having sufficient funds")
    void client_dash_test2(FxRobot robot){
        robot.clickOn(robot.lookup("#transfer_btn").queryAs(Button.class));
        robot.clickOn("#txtf_acc_no").write("1111");
        robot.clickOn("#txtf_amount").write("5000");
        robot.clickOn(robot.lookup("#transfer_btn").queryAs(Button.class));
        verifyThat(".error", Node::isVisible);
        interact(()->((Stage)((lookup(".error").query())).getScene().getWindow()).close());
        robot.clickOn(robot.lookup("#cancel_btn").queryAs(Button.class));
    }

    @Test
    @DisplayName("Transfer money and pay bills")
    void client_dash_test3(FxRobot robot){
        robot.clickOn(robot.lookup("#transfer_btn").queryAs(Button.class));
        robot.clickOn("#txtf_acc_no").write("1111");
        robot.clickOn("#txtf_amount").write("500");
        robot.clickOn(robot.lookup("#transfer_btn").queryAs(Button.class));
        verifyThat(".information", Node::isVisible);
        interact(()->((Stage)((lookup(".information").query())).getScene().getWindow()).close());
        robot.clickOn(robot.lookup("#cancel_btn").queryAs(Button.class));
        robot.clickOn(robot.lookup("#pay_btn").queryAs(Button.class));
        robot.clickOn("#txtf_bill_no").write("5678");
        ComboBox comboBox=robot.lookup("#comboBox_bill_type").queryAs(ComboBox.class);
        robot.clickOn(comboBox);
        interact(() -> {
            comboBox.getSelectionModel().select(2);
        });
        robot.clickOn("#txtf_amount").write("500");
        robot.clickOn(robot.lookup("#pay_btn").queryAs(Button.class));
        verifyThat(".information", Node::isVisible);
        interact(()->((Stage)((lookup(".information").query())).getScene().getWindow()).close());
    }


    @Test
    @DisplayName("Edit information")
    void client_dash_test4(FxRobot robot){
        robot.clickOn(robot.lookup("#edit_btn").queryAs(Button.class));
        robot.clickOn("#edit_email_label");

        robot.clickOn("#textf_email");
//        for (int i = 0; i <20 ; i++) {
//            robot.push(KeyCode.BACK_SPACE);
//        }
        cleartxt(robot, "#textf_email");
        robot.write("Omar@gmail.com");

        robot.clickOn("#edit_mob_label");
        cleartxt(robot, "#textf_mob");
        robot.write("01111");

        robot.clickOn(robot.lookup("#save_btn").queryAs(Button.class));
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


}