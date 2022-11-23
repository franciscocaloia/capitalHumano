package b1.capitalHumano.consultor;

import java.io.IOException;

import org.hibernate.MappingException;

public interface ConsultorDAO {
	
	public  Consultor getByFilter(String consultorUsuario) throws MappingException, IOException;
}
