package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.List;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.empresa.Empresa;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PuestoDAOImp implements PuestoDAO {

	public List<PuestoDTO> getByFilter(String texto) {
		return null;
	};

	public Puesto getById(Integer id) {
		Session session = new Configuration().configure().addAnnotatedClass(Puesto.class).buildSessionFactory()
				.openSession();
		Puesto puesto = session.get(Puesto.class, id);
		return puesto;
	};

	public List<PuestoDTO> getByFilter(Empresa empresa) {
		return null;
	}
//PonderacionNecesaria
	public static List<Puesto> getAllInstances() {
		Session session = new Configuration().configure().addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.class).addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Puesto> criteriaQuery = criteriaBuilder.createQuery(Puesto.class);
		Root<Puesto> root = criteriaQuery.from(Puesto.class);
		criteriaQuery.select(root);
		Query<Puesto> query = session.createQuery(criteriaQuery);
		List<Puesto> puestos = query.getResultList();
		session.close();
		return puestos;
	}

	public void delete(Integer id) {
		
	}

	public void update(Puesto puesto) {

	}

	public static void insert(Puesto puesto) throws MappingException, IOException {
		Session session = new Configuration().configure().addAnnotatedClass(Puesto.class).addAnnotatedClass(PonderacionNecesaria.class).addAnnotatedClass(Competencia.class).addAnnotatedClass(Empresa.class).buildSessionFactory()
				.openSession();
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
}
