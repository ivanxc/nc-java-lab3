module com.ivanxc.netcracker.thirdhw {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.ivanxc.netcracker.lab to javafx.fxml;
    exports com.ivanxc.netcracker.lab;
}