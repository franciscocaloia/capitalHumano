package b1.capitalHumano.puesto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.MappingException;

import b1.capitalHumano.competencia.CompetenciaDAOImp;
import b1.capitalHumano.empresa.Empresa;
import b1.capitalHumano.empresa.EmpresaDAOImp;





public class ControllerPuestos {
	
	public static List<PuestoDTO> getPuestos() {
		List<Puesto> puestos = PuestoDAOImp.getAllInstances();
		List<PuestoDTO> puestosDTO = new ArrayList<>();
		for(Puesto puesto : puestos) {
			System.out.println(puesto.getCaracteristicas());
			puestosDTO.add(new PuestoDTO(puesto.getIdPuesto(),puesto.getNombrePuesto(),puesto.getEmpresa().getIdEmpresa(),puesto.getEmpresa().getNombreEmpresa(),puesto.getDescripcionPuesto(),puesto.getEliminado()));
		}
		return puestosDTO;
	}
	public static void darDeAltaPuesto(PuestoDTO puestoDTO) {
		Empresa empresa = EmpresaDAOImp.getById(puestoDTO.getIdEmpresa());
		Set<PonderacionNecesaria> caracteristicas = new HashSet<PonderacionNecesaria>();
		for (PonderacionNecesariaDTO pondDTO:puestoDTO.getCaracteristicasDTO()) {
			caracteristicas.add(new PonderacionNecesaria(pondDTO.getPonderacionNecesaria(),CompetenciaDAOImp.getById(pondDTO.getIdComp())));
		}
		try {
			PuestoDAOImp.insert(new Puesto(puestoDTO.getIdPuesto(),puestoDTO.getNombrePuesto(),puestoDTO.getDescripcionPuesto(),empresa,caracteristicas));
		} catch (MappingException e) {
			//Integer idPuesto, String nombrePuesto, String descripcion, Empresa empresa,ArrayList<PonderacionNecesaria> caracteristicas
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 