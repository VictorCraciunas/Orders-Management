module com.jfxbase.oopjfxbase {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens com.jfxbase.oopjfxbase to javafx.fxml;
    exports com.jfxbase.oopjfxbase;
    exports com.jfxbase.oopjfxbase.utils;
    opens com.jfxbase.oopjfxbase.utils to javafx.fxml;
    exports com.jfxbase.oopjfxbase.utils.enums;
    opens com.jfxbase.oopjfxbase.utils.enums to javafx.fxml;
    exports Presentation.controllers;
    opens Presentation.controllers to javafx.fxml;

    // Export the Model package to make Client and Product accessible
    exports Model;

    // Export other packages if necessary
    exports Data_Access;
    exports connection;
    exports Business_Logic.Validators;
}