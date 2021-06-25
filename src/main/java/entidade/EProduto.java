package entidade;

import java.io.Serializable;

public class EProduto implements Serializable {

	private static final long serialVersionUID = -8876113972003862875L;

	private String nome;
	private String descricao;
	private Double preco;
	private Integer quantidade;
	private Double valorTotal = 0.00;
//	private String concatenado;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
//	public String getConcatenado() {
//		return concatenado;
//	}
//	public void setConcatenado(String concatenado) {
//		this.concatenado = concatenado;
//	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
		
}
