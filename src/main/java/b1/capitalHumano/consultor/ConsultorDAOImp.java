package b1.capitalHumano.consultor;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.candidato.Candidato;
import b1.capitalHumano.cuestionario.Cuestionario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ConsultorDAOImp implements ConsultorDAO{ 
	public  Consultor getByFilter(String consultorUsuario) throws MappingException, IOException {
		Session session = new Configuration().configure().addAnnotatedClass(Consultor.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Consultor> criteriaQuery = criteriaBuilder.createQuery(Consultor.class);

		Root<Consultor> root = criteriaQuery.from(Consultor.class);

		Predicate nombreQ = criteriaBuilder.equal(root.get("usuario"), consultorUsuario);
		
		//Consultor consultor = session.get(Consultor.class, consultorUsuario);

		criteriaQuery.select(root).where(criteriaBuilder.and(nombreQ));

		Query<Consultor> query = session.createQuery(criteriaQuery);

		List<Consultor> consultorValido = query.getResultList();
	
		session.close();
		return consultorValido.size()>0?consultorValido.get(0):null;
	}
}
