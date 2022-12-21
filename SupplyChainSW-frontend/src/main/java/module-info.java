module fgroup.supplychainswfrontend {
    requires javafx.controls;
    requires javafx.fxml;


    opens fgroup.supplychainswfrontend to javafx.fxml;
    exports fgroup.supplychainswfrontend;
}