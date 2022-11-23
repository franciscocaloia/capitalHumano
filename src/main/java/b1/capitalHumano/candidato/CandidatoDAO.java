package b1.capitalHumano.candidato;

import java.io.IOException;

import org.hibernate.MappingException;

public interface CandidatoDAO {
	public Candidato getByFilter(String string) throws MappingException, IOException;
}
