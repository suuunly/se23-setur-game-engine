module com.setur.se23.gameengine.gameengineboilerplate {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.setur.se23.games to javafx.fxml;
    exports com.setur.se23.games;
    exports com.setur.se23.engine.render;
    opens com.setur.se23.engine.render to javafx.fxml;
    exports com.setur.se23.engine.render.common;
    opens com.setur.se23.engine.render.common to javafx.fxml;
}