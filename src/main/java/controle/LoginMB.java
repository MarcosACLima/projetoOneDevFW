package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.Login;
import util.SessionContext;

@Named("loginMB")
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	MensagemMB oMsg = new MensagemMB();
	
	public LoginMB() throws SQLException {
		
		if (SessionContext.getInstance() != null) {
			SessionContext.getInstance().encerrarSessao();
		}
	}
	
	@Inject
	private Login login;
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public void validarLogin() {
		
		if (!login.getUsuario().equalsIgnoreCase("marcos")) {
			oMsg.mensagemUsuarioInexistente();
			return;
		} 
		
		if (!login.getSenha().equalsIgnoreCase("12345")) {
			oMsg.mensagemSenhaIncorreta();
			return;
		} else {
			SessionContext.getInstance().setAttribute("usuario", login.getUsuario());
			login = new Login();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroProduto.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
