package b1.capitalHumano.empresa;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;

import b1.capitalHumano.puesto.Puesto;
import b1.capitalHumano.puesto.PuestoDTO;

public interface EmpresaDAO {
	public static void insert(Empresa empresa) throws MappingException, IOException {
	}
	public List<Empresa> getAllInstances();
	public Empresa getById(Integer id);
}
