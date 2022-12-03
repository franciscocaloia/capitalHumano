package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;

public interface CandidatoDAO {
	public Candidato getByFilter(String string, String tipoDNI) ;
	public List<Candidato> getAllInstances() ;
	}
