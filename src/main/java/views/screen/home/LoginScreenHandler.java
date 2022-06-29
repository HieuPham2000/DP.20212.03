package views.screen.home;

import controller.AuthenticationController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.logging.Logger;


public class LoginScreenHandler extends BaseScreenHandler{

    /*
    COUPING-COHESION subteam 2: Method backToHomeScreen của class đang có stamp coupling do có tham số MouseEvent
    nhưng trong phần implement không cần sử dụng biến này
     */

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        try {
            setupData(null);
            setupFunctionality();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }

    protected void setupData(Object dto) {
    }

    protected void setupFunctionality() {
    }

    @FXML
    void login(MouseEvent event) throws IOException {
        try {
            getBController().login(email.getText(), password.getText());
            PopupScreen.success("Login Successfully!");
            backToHomeScreen(event);
        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
        }
    }

    @FXML
    void backToHomeScreen(MouseEvent event) {
        this.homeScreenHandler.show();
    }
}
