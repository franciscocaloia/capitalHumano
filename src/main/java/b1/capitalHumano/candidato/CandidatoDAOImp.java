package b1.capitalHumano.candidato;

import java.io.IOException;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import b1.capitalHumano.empresa.Empresa;


public class CandidatoDAOImp implements CandidatoDAO {

	public  Candidato getByFilter(String DNI) throws MappingException, IOException {
		//conectar y traer de la DB -- PROBAR
		Session session = new Configuration().configure().addAnnotatedClass(Candidato.class).buildSessionFactory()
				.openSession();
		Candidato candidato = session.get(Candidato.class, DNI);
		return candidato;
	}

}
