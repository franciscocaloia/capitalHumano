package b1.capitalHumano.candidato;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.hibernate.MappingException;

public interface CandidatoDAO {
	public List<Candidato> buscarCandidatos( String nombreInput, String apellidoInput) ;
	public List<Candidato> buscarCandidatos(Integer codigoInput, String nombreInput, String apellidoInput) ;
	public List<Candidato> getAllInstances() ;
	public Candidato getById(Integer idCandidato);
	public Candidato getByFilter(String tipo, Integer DNI );
	public List<Candidato> getByIdList(List<Integer> idCandidatos);
	public void update(Set<Candidato> candidatos);
	}
