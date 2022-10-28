package b1.capitalHumano.puesto;
import b1.capitalHumano.Empresa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.MappingException;

public interface PuestoDAO {
	public void insert(Puesto puesto) throws MappingException, IOException;
	public void update(Puesto puesto);
	public void delete(Integer id);
	public List<PuestoDTO> getAllInstances();
	public List<PuestoDTO> getByFilter(Empresa empresa);
	public List<PuestoDTO> getByFilter(String texto);
	public Puesto getById(Integer id);

	
	/* static List<Puesto> puestos = new ArrayList<Puesto>();
	public static List<Puesto> getAllInstances(){
		puestos.add(new Puesto(1,"gerente","Esta a cargo de los empleados"));
		puestos.add(new Puesto(2,"empleado administrativo","realiza trabajos administrativos"));
		puestos.add(new Puesto(3,"empleado limpieza","realiza trabajos de limpieza"));
		return puestos;
	}
*/ 
}
