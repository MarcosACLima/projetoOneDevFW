package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.EProduto;
import util.ProdutoDAO;
import util.SessionContext;

@Named("produtoMB")
@SessionScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 6667574492052227400L;

	public ProdutoMB() throws SQLException {

		if (SessionContext.getInstance().getAttribute("usuario") == null) {

			try {

				boolean respostaComprometida = FacesContext.getCurrentInstance().getExternalContext()
						.isResponseCommitted();

				if (!respostaComprometida) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		this.preencherCombo();
		this.carregarLista();
	}
		
	@Inject
	EProduto produto;
	
	private List<EProduto> listaProduto = new ArrayList<>();
//	private List<SelectItem> listaCombo = new ArrayList<SelectItem>();

	MensagemMB oMsg = new MensagemMB();

	public EProduto getProduto() {
		return produto;
	}

	public void setProduto(EProduto produto) {
		this.produto = produto;
	}

	public List<EProduto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<EProduto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public void salvar() {
		ProdutoDAO.getInstance().salvar(produto);
		this.carregarLista();
		limpar();
	}

	public void carregarLista() {
		listaProduto.clear();
		listaProduto = ProdutoDAO.getInstance().listarTodos();
	}

	public void excluir() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Object parametro = params.get("parametroIdent");
		produto.setId(Long.parseLong(parametro.toString()));
		ProdutoDAO.getInstance().remover(produto);
		this.limpar();
		this.carregarLista();
	}

	public void alterar() {
		ProdutoDAO.getInstance().alterar(produto);
		this.limpar();
		this.carregarLista();
	}

	public void limpar() {
		this.produto = new EProduto();
		this.refresh();
	}

	public void prepararEdicao() throws ParseException {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Object parametroId = params.get("parametroId");
		this.produto = ProdutoDAO.getInstance().buscarPorID(Long.parseLong(parametroId.toString()));
		this.refresh();
	}

	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}	
	
}
