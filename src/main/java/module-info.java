module com.mycompany.the_coffee_farm {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.the_coffee_farm to javafx.fxml;
    exports com.mycompany.the_coffee_farm;
}
