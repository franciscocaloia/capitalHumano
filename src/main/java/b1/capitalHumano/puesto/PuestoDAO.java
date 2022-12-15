package b1.capitalHumano.puesto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.MappingException;

import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.evaluacion.Evaluacion;

public interface PuestoDAO {
	public  void insert(Puesto puesto) ;
	public void update(Puesto puesto);

	public List<Puesto> getAllInstances();
	public List<Puesto> getByFilter(Empresa empresa);
	public List<Puesto> getByFilter(String texto);
	public Puesto getById(Integer id);
	public List<Puesto> buscarPuestos(Integer codigo, String nombre, String empresa);
	}
