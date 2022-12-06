package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Cuestionario;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CandidatoDAOImp implements CandidatoDAO {
	
	public List<Candidato> buscarCandidatos(Integer codigoInput, String nombreInput, String apellidoInput) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class).addAnnotatedClass(Cuestionario.class)
				.addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.class)
				.addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class)
				.addAnnotatedClass(Candidato.class).buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);

		Root<Candidato> root = criteriaQuery.from(Candidato.class);

		Predicate codigoQ = criteriaBuilder.equal(root.get("idCandidato"), codigoInput);

		Predicate nombreQ = criteriaBuilder.like(root.get("nombre"), "%" + nombreInput + "%");
		Predicate apellidoQ = criteriaBuilder.like(root.get("apellido"), "%" + apellidoInput + "%");

		// Predicate empresaQ = criteriaBuilder.like(root.join("idEmpresa"), empresa);

		criteriaQuery.select(root).where(criteriaBuilder.and(apellidoQ, codigoQ, nombreQ));

		Query<Candidato> query = session.createQuery(criteriaQuery);

		List<Candidato> candidatos = query.getResultList();
		session.close();
		return candidatos;

	}

	public List<Candidato> buscarCandidatos(String nombreInput, String apellidoInput) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class).addAnnotatedClass(Cuestionario.class)
				.addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.class)
				.addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class)
				.addAnnotatedClass(Candidato.class).buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);

		Root<Candidato> root = criteriaQuery.from(Candidato.class);

		Predicate nombreQ = criteriaBuilder.like(root.get("nombre"), "%" + nombreInput + "%");
		Predicate apellidoQ = criteriaBuilder.like(root.get("apellido"), "%" + apellidoInput + "%");

		// Predicate empresaQ = criteriaBuilder.like(root.join("idEmpresa"), empresa);

		criteriaQuery.select(root).where(criteriaBuilder.and(apellidoQ, nombreQ));

		Query<Candidato> query = session.createQuery(criteriaQuery);

		List<Candidato> candidatos = query.getResultList();
		session.close();
		return candidatos;

	}

	public List<Candidato> getAllInstances() {
		// conectar y traer de la DB -- PROBAR

		Session session = new Configuration().configure().addAnnotatedClass(Candidato.class)
				.addAnnotatedClass(TipoDNI.class).addAnnotatedClass(Cuestionario.class)
				.addAnnotatedClass(Consultor.class).buildSessionFactory().openSession();

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
