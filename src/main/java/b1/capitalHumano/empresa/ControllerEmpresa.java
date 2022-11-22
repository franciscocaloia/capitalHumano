package b1.capitalHumano.empresa;

import java.util.ArrayList;
import java.util.List;


public class ControllerEmpresa {
	public static List<EmpresaDTO> getEmpresas() {
		List<Empresa> empresas = EmpresaDAOImp.getAllInstances();
		List<EmpresaDTO> empresasDTO = new ArrayList<>();
		for(Empresa empresa : empresas) {
			empresasDTO.add(new EmpresaDTO(empresa.getIdEmpresa(),empresa.getNombreEmpresa()));
		}
		return empresasDTO;
	}
}
