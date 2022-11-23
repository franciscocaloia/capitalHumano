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
    
    opens b1.capitalHumano.competencia to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.competencia;
    
    opens b1.capitalHumano.empresa to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.empresa;
      
    opens b1.capitalHumano.usuario to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.usuario;

    opens b1.capitalHumano.consultor to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.consultor;
    
    opens b1.capitalHumano.candidato to javafx.fxml,org.hibernate.orm.core;
    exports b1.capitalHumano.candidato;
}
