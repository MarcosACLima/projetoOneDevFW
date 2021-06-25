package controle;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entidade.EProduto;

@Named("pMB")
@SessionScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 6667574492052227400L;

	@Inject
	EProduto produto;

	public EProduto getProduto() {
		return produto;
	}

	public void setProduto(EProduto produto) {
		this.produto = produto;
	}

	public void calcularPrecoTotal() {
		produto.setValorTotal(produto.getPreco() * produto.getQuantidade());
	}

	public void limparProduto() {
		produto = new EProduto();
	}

//	public void concatenar() {
//		produto.setConcatenado(produto.getNome()
//				.concat(" " + produto.getDescricao().concat(" " + produto.getPreco().toString().concat(" " + produto.getQuantidade().toString()))));
//	}
	
}
