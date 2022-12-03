package b1.capitalHumano.competencia;

import java.io.IOException;
import java.util.List;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import b1.capitalHumano.empresa.Empresa;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CompetenciaDAOImp implements CompetenciaDAO{
	public List<Competencia> getAllInstances(){
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Competencia.class).buildSessionFactory();
		Session session = factory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Competencia> criteriaQuery = criteriaBuilder.createQuery(Competencia.class);
		Root<Competencia> root = criteriaQuery.from(Competencia.class);
		criteriaQuery.select(root);
		Query<Competencia> query = session.createQuery(criteriaQuery);
		List<Competencia> competencias = query.list();
		session.close();
		return competencias;
	}
	public Competencia getById(Integer id) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Competencia.class).buildSessionFactory();
		Session session = factory.openSession();
		Competencia competencia = session.get(Competencia.class,id);
		session.close();
		return competencia;
	}
	@Override
	public void insert(Competencia competencia) throws MappingException, IOException {
		// TODO Auto-generated method stub
		
	}
}
