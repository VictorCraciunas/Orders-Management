package Presentation.controllers;


import com.jfxbase.oopjfxbase.utils.SceneController;
import com.jfxbase.oopjfxbase.utils.enums.SCENE_IDENTIFIER;
import javafx.fxml.FXML;


public class HomeController extends SceneController {

    @FXML
    protected void goToClientsView() {
        this.changeScene(SCENE_IDENTIFIER.Clients_View);
    }

    @FXML
    protected void goToProductsView() {
        this.changeScene(SCENE_IDENTIFIER.Products_View);
    }
}