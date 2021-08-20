package util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

// Para controle da variavel setada

public class SessionContext {
	
	private static SessionContext instance;
	
	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		
		return instance;
	}
	
	private SessionContext() {
	}
	
	private ExternalContext currentExternalContext() {
		if (FacesContext.getCurrentInstance() == null) {
			throw new RuntimeException("Deve ser chamado somente em uma requisição HTTP");
		} else {
			return FacesContext.getCurrentInstance().getExternalContext();
		}
	}
	
	public void encerrarSessao() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return;
	}
	
	public Object getAttribute(String nome) {
		return currentExternalContext().getSessionMap().get(nome);
	}
	
	public void setAttribute(String nome, Object valor) {
		currentExternalContext().getSessionMap().put(nome, valor);
	}

}
