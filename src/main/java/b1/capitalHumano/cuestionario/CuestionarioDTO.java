package b1.capitalHumano.cuestionario;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class CuestionarioDTO {

	private Integer idCuestionario;
	private Integer idEvaluacion;
	private Integer idPuesto;
	private Integer idCandidato;
	private String clave;
	private Date fechaInicio;
	private Date ultimoIngreso;
	private Integer nroIngresos;
	private Integer eliminado;
	private EstadoCuestionario estadoCuestionario;
	private Set<Integer> idCompetenciasCuestionario = new HashSet<Integer>();
	private Set<Integer> idBloques = new HashSet<Integer>();
	public CuestionarioDTO(Cuestionario cuestionario) {
		this.idCuestionario=cuestionario.getIdCuestionario();
		this.idEvaluacion= cuestionario.getEvaluación().getIdEvaluacion();
		this.idPuesto=cuestionario.getPuesto().getIdPuesto();
		this.idCandidato=cuestionario.getCandidato().getIdCandidato();
		this.clave=cuestionario.getClave();
		this.fechaInicio=cuestionario.getFechaInicio();
		this.ultimoIngreso=cuestionario.getUltimoIngreso();
		this.nroIngresos=cuestionario.getNroIngresos();
		this.eliminado=cuestionario.getEliminado();
		this.estadoCuestionario=cuestionario.getEstado();
		this.idCompetenciasCuestionario=cuestionario.getCompetenciasCuestionario().stream().map(comp->comp.getIdComp()).collect(Collectors.toSet());
		this.idBloques=cuestionario.getBloques().stream().map(bloque->bloque.getIdBloque()).collect(Collectors.toSet());
	}
	public Integer getIdCuestionario() {
		return idCuestionario;
	}
	public void setIdCuestionario(Integer idCuestionario) {
		this.idCuestionario = idCuestionario;
	}
	public Integer getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(Integer idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public Integer getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	public Integer getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(Integer idCandidato) {
		this.idCandidato = idCandidato;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getUltimoIngreso() {
		return ultimoIngreso;
	}
	public void setUltimoIngreso(Date ultimoIngreso) {
		this.ultimoIngreso = ultimoIngreso;
	}
	public Integer getNroIngresos() {
		return nroIngresos;
	}
	public void setNroIngresos(Integer nroIngresos) {
		this.nroIngresos = nroIngresos;
	}
	public Integer getEliminado() {
		return eliminado;
	}
	public void setEliminado(Integer eliminado) {
		this.eliminado = eliminado;
	}
	public EstadoCuestionario getEstadoCuestionario() {
		return estadoCuestionario;
	}
	public void setEstadoCuestionario(EstadoCuestionario estadoCuestionario) {
		this.estadoCuestionario = estadoCuestionario;
	}
	public Set<Integer> getIdCompetenciasCuestionario() {
		return idCompetenciasCuestionario;
	}
	public void setIdCompetenciasCuestionario(Set<Integer> idCompetenciasCuestionario) {
		this.idCompetenciasCuestionario = idCompetenciasCuestionario;
	}
	public Set<Integer> getIdBloques() {
		return idBloques;
	}
	public void setIdBloques(Set<Integer> idBloques) {
		this.idBloques = idBloques;
	}
	
}
