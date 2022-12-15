package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Factor;
import b1.capitalHumano.Opcion;
import b1.capitalHumano.OpcionRespuesta;
import b1.capitalHumano.PonderacionOpcion;
import b1.capitalHumano.Pregunta;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.consultor.Consultor;
import b1.capitalHumano.cuestionario.Bloque;
import b1.capitalHumano.cuestionario.CompetenciaCuestionario;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.cuestionario.FactorCuestionario;
import b1.capitalHumano.cuestionario.OpcionCuestionario;
import b1.capitalHumano.cuestionario.PreguntaCuestionario;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CandidatoDAOImp implements CandidatoDAO {

	public List<Candidato> buscarCandidatos(Integer codigoInput, String nombreInput, String apellidoInput) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class)
				.addAnnotatedClass(Cuestionario.class).addAnnotatedClass(Puesto.class)
				.addAnnotatedClass(PonderacionNecesaria.class).addAnnotatedClass(Competencia.class)
				.addAnnotatedClass(Empresa.class).addAnnotatedClass(Candidato.class).buildSessionFactory()
				.openSession();
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

	public Candidato getById(Integer idCandidato) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class)
				.addAnnotatedClass(Puesto.class).addAnnotatedClass(Cuestionario.class).buildSessionFactory()
				.openSession();
		Candidato candidato = session.get(Candidato.class, idCandidato);
		session.close();
		return candidato;
	}

	public List<Candidato> getByIdList(List<Integer> idCandidatos) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);
		Root<Candidato> root = criteriaQuery.from(Candidato.class);
		In<Integer> in = criteriaBuilder.in(root.get("idCandidato"));
		for (Integer idCandidato : idCandidatos) {
			in.value(idCandidato);
		}
		criteriaQuery.select(root).where(in);
		Query<Candidato> query = session.createQuery(criteriaQuery);
		List<Candidato> candidatos = query.getResultList();
		session.close();
		return candidatos;
	}

	public Candidato getByFilter(String tipo, Integer DNI) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);
		Root<Candidato> root = criteriaQuery.from(Candidato.class);
		Predicate tipoQ = criteriaBuilder.like(root.get("tipo"), "%" + tipo + "%");
		Predicate DNIQ = criteriaBuilder.equal(root.get("DNI"), DNI);
		criteriaQuery.select(root).where(criteriaBuilder.and(tipoQ, DNIQ));
		Query<Candidato> query = session.createQuery(criteriaQuery);
		Candidato candidato = query.getSingleResult();
		session.close();
		return candidato;
	}

	public List<Candidato> buscarCandidatos(String nombreInput, String apellidoInput) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class)
				.addAnnotatedClass(Cuestionario.class).addAnnotatedClass(Puesto.class)
				.addAnnotatedClass(PonderacionNecesaria.class).addAnnotatedClass(Competencia.class)
				.addAnnotatedClass(Empresa.class).addAnnotatedClass(Candidato.class).buildSessionFactory()
				.openSession();
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

		Session session = new Configuration().configure().buildSessionFactory().openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Candidato> criteriaQuery = criteriaBuilder.createQuery(Candidato.class);
		Root<Candidato> root = criteriaQuery.from(Candidato.class);
		criteriaQuery.select(root);
		Query<Candidato> query = session.createQuery(criteriaQuery);
		List<Candidato> candidatos = query.getResultList();
		session.close();
		return candidatos;
	}

	public void update(Set<Candidato> candidatos) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		try {
			tx = session.getTransaction();
			tx.begin();
			for (Candidato candidato : candidatos) {
			session.merge(candidato);
			}
			tx.commit();	
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
