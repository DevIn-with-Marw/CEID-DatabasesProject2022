module com.projectdb {
    requires transitive javafx.graphics;
    requires transitive java.sql;
    requires transitive javafx.controls;
    requires javafx.fxml;
    
    
    opens com.projectdb to javafx.fxml;
    exports com.projectdb;
    exports com.projectdb.model;
}
