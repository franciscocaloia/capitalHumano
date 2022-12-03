package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.empresa.Empresa;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CandidatoDAOImp implements CandidatoDAO {

	public Candidato getByFilter(String DNI, String tipoDNIInput)  {
		// conectar y traer de la DB -- PROBAR

		Session session = new Configuration().configure().addAnnotatedClass(Candidato.class)
				.addAnnotatedClass(TipoDNI.class).addAnnotatedClass(Cuestionario.class).buildSessionFactory()
				.openSession();

		Candidato candidato = session.get(Candidato.class, DNI);

		session.close();

		for (TipoDNI tipoDNI : candidato.getTipoDNI()) {
			if (tipoDNI.getTipoDNI() == tipoDNIInput) {
				return candidato;
			}
		}
		return null;

	}

	public List<Candidato> getAllInstances() {
		// conectar y traer de la DB -- PROBAR

		Session session = new Configuration().configure().addAnnotatedClass(Candidato.class)
				.addAnnotatedClass(TipoDNI.class).addAnnotatedClass(Cuestionario.class).buildSessionFactory()
				.openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);
		Root<Candidato> root = criteriaQuery.from(Candidato.class);
		criteriaQuery.select(root);
		Query<Candidato> query = session.createQuery(criteriaQuery);
		List<Candidato> candidato = query.getResultList();
		session.close();
		return candidato;

	}

}
