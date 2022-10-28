module b1.capitalHumano {
    requires transitive javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires org.hibernate.orm.core;
	requires java.naming;
	requires java.sql;
	requires jakarta.persistence;

    opens b1.capitalHumano to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano;
    
    opens b1.capitalHumano.puesto to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.puesto;
}
