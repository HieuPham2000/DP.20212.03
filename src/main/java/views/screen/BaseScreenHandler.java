package views.screen;

import controller.BaseController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Logger;

public abstract class BaseScreenHandler extends FXMLScreenHandler {

	/*
	COUPING-COHESION subteam 2: Biến LOGGER và method forward trong class này đang ở mức độ cohesion là co-incidental
	do nó chỉ được khởi tạo mà không được sử dụng trong class và các sub-class
	*/

	private static final Logger LOGGER = Utils.getLogger(BaseScreenHandler.class.getName());

	private Scene scene;
	private BaseScreenHandler prev;
	protected final Stage stage;
	protected HomeScreenHandler homeScreenHandler;
	protected Hashtable<String, String> messages;
	private BaseController bController;

	protected BaseScreenHandler(Stage stage, String screenPath) throws IOException {
		super(screenPath);
		this.stage = stage;
	}

	public void setPreviousScreen(BaseScreenHandler prev) {
		this.prev = prev;
	}

	public BaseScreenHandler getPreviousScreen() {
		return this.prev;
	}

	public void show() {
		if (this.scene == null) {
			this.scene = new Scene(this.content);
		}
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public void setScreenTitle(String string) {
		this.stage.setTitle(string);
	}

	public void setBController(BaseController bController){
		this.bController = bController;
	}

	public BaseController getBController(){
		return this.bController;
	}

	public void forward(Hashtable messages) {
		this.messages = messages;
	}

	public void setHomeScreenHandler(HomeScreenHandler HomeScreenHandler) {
		this.homeScreenHandler = HomeScreenHandler;
	}

	protected void handleIOException() {
		try {
			PopupScreen.error("Error when loading resources.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
