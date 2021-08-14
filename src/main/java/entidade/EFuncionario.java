package entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.text.NumberFormat;

public class EFuncionario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	private String nome;
	
	private String sexo;
	
	private Date data_nasc;
	
	private BigDecimal salario_bruto;
	
	private BigDecimal salario_liquido;
	
	private BigDecimal desc_Inss;
	
	private BigDecimal desc_IR;
	
	/* metodos de calculo de salario  */
	
	private BigDecimal calculaDescontoInss() {
		BigDecimal porcentagem = new BigDecimal("0.11");
		BigDecimal descontoInss = this.salario_bruto.multiply(porcentagem);
		BigDecimal salarioDescontadoInss = this.salario_bruto.subtract(descontoInss);
		this.desc_Inss = descontoInss;
		
		return salarioDescontadoInss;
	}
	
	private void calculaDescontoIR() {
		BigDecimal aliquota;
		BigDecimal descontoIR;
		
		if (this.salario_bruto.compareTo(new BigDecimal("1903.99")) == -1 ) {
			this.desc_IR = new BigDecimal("0");
		}
		
		else if ( this.salario_bruto.compareTo(new BigDecimal("2826.66")) == -1 ) {
			aliquota = new BigDecimal("0.075");
			descontoIR = this.salario_bruto.multiply(aliquota);
			this.desc_IR = descontoIR;
		}
		
		else if (this.salario_bruto.compareTo(new BigDecimal("3751.06")) == -1 ) {
			aliquota = new BigDecimal("0.15");
			descontoIR = this.salario_bruto.multiply(aliquota);
			this.desc_IR = descontoIR;
		}
		
		else if (this.salario_bruto.compareTo(new BigDecimal("4664.68")) == -1 ) {
			aliquota = new BigDecimal("0.225");
			descontoIR = this.salario_bruto.multiply(aliquota);
			this.desc_IR = descontoIR;
		}
		
		else {
			aliquota = new BigDecimal("0.275");
			descontoIR = this.salario_bruto.multiply(aliquota);
			this.desc_IR = descontoIR;
		}
		
	}
	
	
	public void calcularDescontosImpostos() {
		this.calculaDescontoInss();
		this.calculaDescontoIR();
		
		BigDecimal descontos = this.desc_Inss.add(desc_IR);
		this.salario_liquido = this.salario_bruto.subtract(descontos);
	}
	
	public String formatarSalario(BigDecimal salario) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(salario);
	}
	
	/* Getter e Setter  */
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public BigDecimal getSalario_bruto() {
		return salario_bruto;
	}

	public void setSalario_bruto(BigDecimal salario_bruto) {
		this.salario_bruto = salario_bruto;
	}

	public BigDecimal getSalario_liquido() {
		return salario_liquido;
	}

	public void setSalario_liquido(BigDecimal salario_liquido) {
		this.salario_liquido = salario_liquido;
	}

	public BigDecimal getDesc_Inss() {
		return desc_Inss;
	}

	public void setDesc_Inss(BigDecimal desc_Inss) {
		this.desc_Inss = desc_Inss;
	}

	public BigDecimal getDesc_IR() {
		return desc_IR;
	}

	public void setDesc_IR(BigDecimal desc_IR) {
		this.desc_IR = desc_IR;
	}
	
}
