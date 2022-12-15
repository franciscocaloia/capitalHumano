package b1.capitalHumano;
import java.util.Random;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import b1.capitalHumano.competencia.Competencia;
import b1.capitalHumano.puesto.PonderacionNecesaria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Factor")
@Table(name = "factor")
public class Factor {
	
	@Id
	@Column(name = "idFactor")
	Integer idFactor;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="idComp")
	Competencia competencia;
	@Column(name = "nombreFactor")
	String nombreFactor;
	@Column(name = "descripcion")
	String descripcion;
	@Column(name = "eliminado")
	Boolean eliminado;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)	
	@JoinColumn(name = "idFactor")
	Set<Pregunta> preguntas;
	
	public Integer getIdFactor() {
		return idFactor;
	}
	public void setIdFactor(Integer idFactor) {
		this.idFactor = idFactor;
	}
	public Competencia getCompetencia() {
		return this.competencia;
	}
	public void setIdComp(Competencia competencia) {
		this.competencia = competencia;
	}
	public String getNombreFactor() {
		return nombreFactor;
	}
	public void setNombreFactor(String nombreFactor) {
		this.nombreFactor = nombreFactor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public Set<Pregunta> getPreguntasRandom(){//devuelve 2 preguntas aleatoreas
		Set<Pregunta> preguntasEncontradas = new HashSet<Pregunta>();
		ArrayList<Pregunta> preguntasArray = new ArrayList<Pregunta>();
		Random rand = new Random();
		preguntasArray.addAll(preguntas);
		for(int i = 0 ; i<2;i++) {
			int index =  rand.nextInt(preguntasArray.size());
			preguntasEncontradas.add(preguntasArray.get(index));
			preguntasArray.remove(index);
		}
		return preguntasEncontradas;
	}
}
