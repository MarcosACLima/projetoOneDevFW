package controle;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.ECliente;

// Classe ManagedBean
@Named("cMB")
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 5110913467100493418L;

	@Inject
	ECliente cliente;
	
	public ECliente getCliente() {
		return cliente;
	}
	
}
