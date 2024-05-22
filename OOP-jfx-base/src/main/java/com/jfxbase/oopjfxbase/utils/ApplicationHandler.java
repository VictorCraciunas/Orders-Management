package com.jfxbase.oopjfxbase.utils;

import com.jfxbase.oopjfxbase.JFXBaseApplication;
import com.jfxbase.oopjfxbase.utils.enums.SCENE_IDENTIFIER;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ApplicationHandler {
    private final HashMap<SCENE_IDENTIFIER, Pane> views = new HashMap<>();
    private Stage stage;

    private ApplicationHandler() {}

    public void startApplication(Stage stage){
        this.initializeViews();

        this.stage = stage;
        this.stage.setTitle(Environment.APP_TITLE);
        this.stage.setFullScreen(Environment.IS_FULLSCREEN);
        this.stage.setScene(new Scene(this.views.get(SCENE_IDENTIFIER.Home), 700, 650));
        this.stage.show();

        Logger.info("Application started..");
    }

    public void changeScene(SCENE_IDENTIFIER newScene) {
        this.stage.getScene().setRoot(views.get(newScene));
    }

    public void closeApplication(){
        Platform.exit();
        System.exit(0);
    }

    private void initializeViews() {
        try {
            for (SCENE_IDENTIFIER value : SCENE_IDENTIFIER.values()) {
                this.views.put(value, FXMLLoader.load(Objects.requireNonNull(JFXBaseApplication.class.getResource(value.label))));
            }
        } catch (IOException | NullPointerException exception) {
            Logger.error("Could not initialize views. Please check the resources folder.");
            this.closeApplication();
        }
    }

    public static ApplicationHandler _instance = null;

    public static ApplicationHandler getInstance() {
        if(ApplicationHandler._instance == null){
            ApplicationHandler._instance = new ApplicationHandler();
        }

        return ApplicationHandler._instance;
    }
    public void navigateToClientsView() {
        try {
            this.views.put(SCENE_IDENTIFIER.Clients_View, FXMLLoader.load(Objects.requireNonNull(JFXBaseApplication.class.getResource("clients-view.fxml"))));
            this.changeScene(SCENE_IDENTIFIER.Clients_View);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    public void navigateToOrdersView() {
        try {
            this.views.put(SCENE_IDENTIFIER.Orders_View, FXMLLoader.load(Objects.requireNonNull(JFXBaseApplication.class.getResource("orders-view.fxml"))));
            this.changeScene(SCENE_IDENTIFIER.Orders_View);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    public void navigateToProductsView() {
        try {
            this.views.put(SCENE_IDENTIFIER.Products_View, FXMLLoader.load(Objects.requireNonNull(JFXBaseApplication.class.getResource("products-view.fxml"))));
            this.changeScene(SCENE_IDENTIFIER.Products_View);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
    public void navigateToBillsView() {
        try {
            this.views.put(SCENE_IDENTIFIER.Bills_View, FXMLLoader.load(Objects.requireNonNull(JFXBaseApplication.class.getResource("bills-view.fxml"))));
            this.changeScene(SCENE_IDENTIFIER.Bills_View);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
