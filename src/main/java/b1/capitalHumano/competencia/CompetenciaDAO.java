package b1.capitalHumano.competencia;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;

public interface CompetenciaDAO {
	public void insert(Competencia competencia) throws MappingException, IOException;
	public List<Competencia> getAllInstances();
	public Competencia getById(Integer id);
}
