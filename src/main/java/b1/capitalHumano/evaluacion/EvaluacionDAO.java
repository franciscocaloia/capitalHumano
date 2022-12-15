package b1.capitalHumano.evaluacion;

import java.util.List;
import java.util.Set;

public interface EvaluacionDAO {
	public  void insert(Evaluacion evaluacion);

	public List<Evaluacion> getByPuesto(int idPuesto);

	public void delete(Integer idEmpresa);
}
