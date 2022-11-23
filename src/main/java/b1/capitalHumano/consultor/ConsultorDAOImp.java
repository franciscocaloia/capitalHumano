package b1.capitalHumano.consultor;

import java.io.IOException;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import b1.capitalHumano.candidato.Candidato;

public class ConsultorDAOImp implements ConsultorDAO{
	public  Consultor getByFilter(String consultorUsuario) throws MappingException, IOException {
		Session session = new Configuration().configure().addAnnotatedClass(Candidato.class).buildSessionFactory()
				.openSession();
		Consultor consultor = session.get(Consultor.class, consultorUsuario);
		return consultor;
	}
}
