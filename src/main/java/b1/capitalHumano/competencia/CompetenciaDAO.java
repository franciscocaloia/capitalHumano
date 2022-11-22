package b1.capitalHumano.competencia;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;

public interface CompetenciaDAO {
	public static void insert(Competencia competencia) throws MappingException, IOException {
	}
	public static List<Competencia> getAllInstances() {
		return null;
	}
	public static Competencia getById(Integer id) {
		return null;
	}
}
