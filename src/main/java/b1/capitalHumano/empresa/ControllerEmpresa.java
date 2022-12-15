package b1.capitalHumano.empresa;

import java.util.ArrayList;
import java.util.List;

import b1.capitalHumano.Singleton;


public class ControllerEmpresa implements Singleton {
	private static ControllerEmpresa instance = null;
	public ControllerEmpresa(){};
	public static ControllerEmpresa getInstance() {
		// TODO Auto-generated method stub
		if (instance == null) {
			instance = new ControllerEmpresa();
		}
		return instance;

	}
	public List<EmpresaDTO> getEmpresas() {
		EmpresaDAO empresaDAO = new EmpresaDAOImp();
		List<Empresa> empresas = empresaDAO.getAllInstances();
		List<EmpresaDTO> empresasDTO = new ArrayList<>();
		for(Empresa empresa : empresas) {
			empresasDTO.add(new EmpresaDTO(empresa.getIdEmpresa(),empresa.getNombreEmpresa()));
		}
		return empresasDTO;
	}
}
