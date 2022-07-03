package views.screen.intro;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class IntroScreenHandler extends BaseScreenHandler {

    /*
    COUPING-COHESION subteam 2: Method setupData của class đang có stamp coupling do có truyền tham số
    nhưng phần implement lại không sử dụng
     */
    private static final Logger LOGGER = Utils.getLogger(IntroScreenHandler.class.getName());

    @FXML
    ImageView logo;

    public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        try {
            setupData(null);
            setupFunctionality();
        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
            handleIOException();
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
            PopupScreen.error(ex.getMessage());
        }
    }


    protected void setupData(Object dto) throws Exception {
        return;
    }

    protected void setupFunctionality() {
        File file = new File(ViewsConfig.LOGO_PATH);
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}