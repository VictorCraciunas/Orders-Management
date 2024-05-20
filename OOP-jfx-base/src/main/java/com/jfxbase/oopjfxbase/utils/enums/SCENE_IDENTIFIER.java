package com.jfxbase.oopjfxbase.utils.enums;

public enum SCENE_IDENTIFIER {
    Home("home-view.fxml"),
    Clients_View("clients-view.fxml"),
    Products_View("products-view.fxml"),

    Orders_View("orders-view.fxml");

    public final String label;

    SCENE_IDENTIFIER(String label) {
        this.label = label;
    }
}
