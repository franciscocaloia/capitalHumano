package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Factor;
import b1.capitalHumano.Opcion;
import b1.capitalHumano.OpcionRespuesta;
import b1.capitalHumano.PonderacionOpcion;
import b1.capitalHumano.Pregunta;
import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.cuestionario.Cuestionario;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PuestoDAOImp implements PuestoDAO {

	public List<Puesto> getByFilter(String texto) {
		return null;
	};

	public Puesto getById(Integer id) {
		Session session = new Configuration().configure().addAnnotatedClass(Evaluacion.class)
				.addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.class)
				.addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class).addAnnotatedClass(Factor.class)
				.addAnnotatedClass(Pregunta.class).addAnnotatedClass(OpcionRespuesta.class)
				.addAnnotatedClass(PonderacionOpcion.class).addAnnotatedClass(Opcion.class).buildSessionFactory()
				.openSession();
		Puesto puesto = session.get(Puesto.class, id);
		session.close();

		return puesto;
	};

	public List<Puesto> getByFilter(Empresa empresa) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Puesto> criteriaQuery = criteriaBuilder.createQuery(Puesto.class);

		Root<Puesto> root = criteriaQuery.from(Puesto.class);

		Join<Puesto, Empresa> puestoEmpresa = root.join("empresa"); // if you have created the metamodel, adjust
																	// accordingly
		// This is the "WHERE..." part
		Predicate empresaQ = criteriaBuilder.equal(puestoEmpresa.get("idEmpresa"), empresa.getIdEmpresa());

		criteriaQuery.select(root).where(criteriaBuilder.and(empresaQ));

		Query<Puesto> query = session.createQuery(criteriaQuery);

		List<Puesto> puestos = query.getResultList();
		session.close();
		return puestos;
	}

//PonderacionNecesaria
	public List<Puesto> getAllInstances() {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Puesto> criteriaQuery = criteriaBuilder.createQuery(Puesto.class);
		Root<Puesto> root = criteriaQuery.from(Puesto.class);
		criteriaQuery.select(root);
		Query<Puesto> query = session.createQuery(criteriaQuery);
		List<Puesto> puestos = query.getResultList();
		session.close();
		return puestos;
	}

	public void update(Puesto puesto) {

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		try {
			tx = session.getTransaction();
			tx.begin();

			session.remove(session.get(Puesto.class, puesto.getIdPuesto()));

			session.flush();
			session.persist(puesto);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;

		} finally {
			session.close();
		}

	}

	public void insert(Puesto puesto) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		try {
			tx = session.getTransaction();
			tx.begin();
			session.persist(puesto);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;

		} finally {
			session.close();
		}
	}

	public List<Puesto> buscarPuestos(Integer idPuesto, String nombre, String empresa) {

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Puesto> criteriaQuery = criteriaBuilder.createQuery(Puesto.class);

		Root<Puesto> root = criteriaQuery.from(Puesto.class);

		Predicate nombreQ = criteriaBuilder.like(root.get("nombrePuesto"), "%" + nombre + "%");

		Join<Puesto, Empresa> puestoEmpresa = root.join("empresa");
		Predicate empresaQ = criteriaBuilder.like(criteriaBuilder.lower(puestoEmpresa.get("nombreEmpresa")),
				"%" + empresa + "%");

		if (idPuesto != null) {
			Predicate codigoQ = criteriaBuilder.equal(root.get("codigoPuesto"), idPuesto);
			criteriaQuery.select(root).where(criteriaBuilder.and(empresaQ, codigoQ, nombreQ));
		} else {
			criteriaQuery.select(root).where(criteriaBuilder.and(empresaQ, nombreQ));

		}
		Query<Puesto> query = session.createQuery(criteriaQuery);

		List<Puesto> puestos = query.getResultList();
		session.close();
		return puestos;
	}
	/*
	 * public List<Puesto> buscarPuestos(String nombre, String empresa) { //eliminar
	 * esto Session session = new
	 * Configuration().configure().addAnnotatedClass(Evaluacion.class)
	 * .addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.
	 * class)
	 * .addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class).
	 * buildSessionFactory() .openSession(); CriteriaBuilder criteriaBuilder =
	 * session.getCriteriaBuilder(); CriteriaQuery<Puesto> criteriaQuery =
	 * criteriaBuilder.createQuery(Puesto.class);
	 * 
	 * Root<Puesto> root = criteriaQuery.from(Puesto.class);
	 * 
	 * Predicate nombreQ = criteriaBuilder.like(root.get("nombrePuesto"), "%" +
	 * nombre + "%");
	 * 
	 * Join<Puesto, Empresa> puestoEmpresa = root.join("empresa"); // if you have
	 * created the metamodel, adjust // accordingly // This is the "WHERE..." part
	 * Predicate empresaQ =
	 * criteriaBuilder.like(criteriaBuilder.lower(puestoEmpresa.get("nombreEmpresa")
	 * ), "%" + empresa + "%");
	 * 
	 * criteriaQuery.select(root).where(criteriaBuilder.and(empresaQ, nombreQ));
	 * 
	 * Query<Puesto> query = session.createQuery(criteriaQuery);
	 * 
	 * List<Puesto> puestos = query.getResultList(); session.close();
	 * 
	 * return puestos; }
	 */

}
