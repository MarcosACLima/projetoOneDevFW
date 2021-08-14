package controle;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.EFuncionario;
import util.SessionContext;

@Named("funcionarioMB")
@SessionScoped
public class FuncionarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FuncionarioMB() throws SQLException {
		
		if (SessionContext.getInstance().getAttribute("usuario") == null) {
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
				
	}
	
	@Inject
	private EFuncionario funcionario;
	
	private String salario;
	
	private List<EFuncionario> listaFuncionario = new ArrayList<EFuncionario>();
	private List<SelectItem> listaCombo = new ArrayList<SelectItem>();
	
	MensagemMB oMsg = new MensagemMB();

	public EFuncionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(EFuncionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public List<EFuncionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<EFuncionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<SelectItem> getListaCombo() {
		return listaCombo;
	}

	public void setListaCombo(List<SelectItem> listaCombo) {
		this.listaCombo = listaCombo;
	}
	
	public void calcular() throws ParseException { 
		this.funcionario.setSalario_bruto(new BigDecimal(this.salario));
		this.funcionario.calcularDescontosImpostos();
		this.listaFuncionario.add(this.funcionario);
		this.funcionario = new EFuncionario();
		this.salario = new String();
	} 
	
	public void limpar() throws ParseException  {
		this.listaFuncionario.clear();
		this.funcionario = new EFuncionario();
	}	

}
