package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;

import org.hibernate.MappingException;

public interface CandidatoDAO {
	public List<Candidato> buscarCandidatos( String nombreInput, String apellidoInput) ;
	public List<Candidato> buscarCandidatos(Integer codigoInput, String nombreInput, String apellidoInput) ;
	public List<Candidato> getAllInstances() ;
	}
