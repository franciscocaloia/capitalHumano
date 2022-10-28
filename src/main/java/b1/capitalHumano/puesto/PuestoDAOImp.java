package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.List;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import b1.capitalHumano.Empresa;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PuestoDAOImp implements PuestoDAO {

	public List<PuestoDTO> getByFilter(String texto) {
		return null;
	};

	public Puesto getById(Integer id) {
		return null;
	};

	public List<PuestoDTO> getByFilter(Empresa empresa) {
		return null;
	}

	public static List<PuestoDTO> getAllInstances() {
		Session session = new Configuration().configure().addAnnotatedClass(PuestoDTO.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<PuestoDTO> criteriaQuery = criteriaBuilder.createQuery(PuestoDTO.class);
		Root<PuestoDTO> root = criteriaQuery.from(PuestoDTO.class);
		criteriaQuery.select(root);
		Query<PuestoDTO> query = session.createQuery(criteriaQuery);
		List<PuestoDTO> Puestos = query.getResultList();
		return Puestos;

	}

	public void delete(Integer id) {
	}

	public void update(Puesto puesto) {

	}

	public static void insert(Puesto puesto) throws MappingException, IOException {
		PuestoDTO puestoDTO = new PuestoDTO(puesto.getIdPuesto(), puesto.empresa.getIdEmpresa(), puesto.getNombrePuesto(),
				puesto.getDescripcionPuesto(), puesto.getEliminado());
		Session session = new Configuration().configure().addAnnotatedClass(PuestoDTO.class).buildSessionFactory()
				.openSession();
		Transaction tx = session.getTransaction();
		try {
			tx = session.getTransaction();
			tx.begin();
			session.persist(puestoDTO);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;

		} finally {
			session.close();
		}
	}
}
