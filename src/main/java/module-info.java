module com.setur.se23.gameengine.gameengineboilerplate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    exports com.setur.se23.engine.render;
    opens com.setur.se23.engine.render to javafx.fxml;
    exports com.setur.se23.engine.render.common;
    opens com.setur.se23.engine.render.common to javafx.fxml;
    exports com.setur.se23;
    opens com.setur.se23 to javafx.fxml;
}