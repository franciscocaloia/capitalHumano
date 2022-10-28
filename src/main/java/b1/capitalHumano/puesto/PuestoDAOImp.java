package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.List;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import b1.capitalHumano.Empresa;

public class PuestoDAOImp implements PuestoDAO {

	public List<PuestoDTO> getByFilter(String texto) {
		return null;
	};

	public Puesto getById(Integer id) {
		return null;
	};

	public List<PuestoDTO> getByFilter(Empresa empresa)  {
		return null;
	}

	public List<PuestoDTO> getAllInstances(){
		return null;
	}

	public void delete(Integer id) {
	}

	public void update(Puesto puesto) {
		PuestoDTO puestoDTO = new PuestoDTO(puesto.getIdPuesto(), puesto.empresa.getId(), puesto.getNombrePuesto(),
				puesto.getDescripcionPuesto(), puesto.getEliminado());
	}

	public void insert(Puesto puesto) throws MappingException, IOException 
	{
		PuestoDTO puestoDTO = new PuestoDTO(1, puesto.empresa.getId(), puesto.getNombrePuesto(),
				puesto.getDescripcionPuesto(), puesto.getEliminado());
      SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(PuestoDTO.class).buildSessionFactory();
      Session session = sessionFactory.openSession();
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
