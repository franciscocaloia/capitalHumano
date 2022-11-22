package b1.capitalHumano.puesto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.MappingException;

import b1.capitalHumano.empresa.Empresa;

public interface PuestoDAO {
	public static void insert(Puesto puesto) throws MappingException, IOException {
	}
	public void update(Puesto puesto);
	public void delete(Integer id);
	public static List<PuestoDTO> getAllInstances() {
		return null;
	}
	public List<PuestoDTO> getByFilter(Empresa empresa);
	public List<PuestoDTO> getByFilter(String texto);
	public Puesto getById(Integer id);
	}
