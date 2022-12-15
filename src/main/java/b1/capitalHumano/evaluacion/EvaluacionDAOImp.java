package b1.capitalHumano.evaluacion;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.puesto.Puesto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EvaluacionDAOImp implements EvaluacionDAO {
	public void insert(Evaluacion evaluacion) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		Integer id;
		try {
			tx = session.getTransaction();
			tx.begin();
			session.persist(evaluacion);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;

		} finally {
			session.close();
		}
	}

	@Override
	public List<Evaluacion> getByPuesto(int idPuesto) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Evaluacion> criteriaQuery = criteriaBuilder.createQuery(Evaluacion.class);
		Root<Evaluacion> root = criteriaQuery.from(Evaluacion.class);
		Predicate puestoQ = criteriaBuilder.equal(root.get("idPuesto"), idPuesto);
		criteriaQuery.select(root).where(criteriaBuilder.and(puestoQ));
		Query<Evaluacion> query = session.createQuery(criteriaQuery);
		List<Evaluacion> puestos = query.getResultList();
		session.close();
		return puestos;
	}
	
	public void delete(Integer idEvaluacion) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.getTransaction();
		try {
			tx = session.getTransaction();
			tx.begin();
			session.remove(session.get(Evaluacion.class, idEvaluacion));
			session.flush();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;

		} finally {
			session.close();
		}

	}
}
