module dungeon.quest {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    // Open packages to javafx.fxml for reflection
    opens gui.controllers to javafx.fxml;
    opens gui to javafx.fxml;

    // Export packages to allow access
    exports main;
    exports gui;
    exports gui.controllers;
    exports entities;
    exports items;
    exports factories;
    exports combat;
    exports utils;
}
