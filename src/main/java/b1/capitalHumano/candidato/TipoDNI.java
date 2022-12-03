package b1.capitalHumano.candidato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="TipoDNI")
@Table(name="tipoDNI")
public class TipoDNI {
		
		@Id
		@Column(name = "idCandidato")
		Integer idCandidato;
		public Integer getIdCandidato() {
			return idCandidato;
		}
		public void setIdCandidato(Integer idCandidato) {
			this.idCandidato = idCandidato;
		}
		public String getTipoDNI() {
			return tipoDNI;
		}
		public void setTipoDNI(String tipoDNI) {
			this.tipoDNI = tipoDNI;
		}
		@Id
		@Column(name = "tipoDNI")
		String tipoDNI;
}
