package b1.capitalHumano.empresa;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class EmpresaDAOImp implements EmpresaDAO{
	public List<Empresa> getAllInstances() {
		Session session = new Configuration().configure().addAnnotatedClass(Empresa.class).buildSessionFactory()
				.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteriaQuery = criteriaBuilder.createQuery(Empresa.class);
		Root<Empresa> root = criteriaQuery.from(Empresa.class);
		criteriaQuery.select(root);
		Query<Empresa> query = session.createQuery(criteriaQuery);
		List<Empresa> Empresas = query.getResultList();
		session.close();
		return Empresas;
	}
	public Empresa getById(Integer id) {
		Session session = new Configuration().configure().addAnnotatedClass(Empresa.class).buildSessionFactory()
				.openSession();
		Empresa empresa = session.get(Empresa.class, id);
		session.close();
		return empresa;
	}
}
