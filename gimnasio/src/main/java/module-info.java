module co.edu.uniquindio.gimnasio {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.gimnasio to javafx.fxml;
    exports co.edu.uniquindio.gimnasio;
}