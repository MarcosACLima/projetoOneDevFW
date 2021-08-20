package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import entidade.EPessoa;

@ManagedBean(name="GraficoAreaMB")
public class Grafico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LineChartModel areaModel;
	
	@PostConstruct
	public void init() {
		createAreaModel();
	}
	
	public LineChartModel getAreaModel() {
		return areaModel;
	}

	private void createAreaModel() {
		List<EPessoa> listaPessoa = new ArrayList<>();
		listaPessoa = PessoaDAO.getInstance().listarTodos();
    	areaModel = new LineChartModel();
       
    	// conteudo interno do grafico
    	LineChartSeries legenda = new LineChartSeries();
        legenda.setFill(true); // prencher area grafico
        legenda.setLabel("Linha de valores");
        for(EPessoa ePessoa : listaPessoa) {
        	// eixo X, dps virgula segundo parametro interno valores
        	legenda.set(ePessoa.getCpf_cnpj() + "-" + ePessoa.getNome(), ePessoa.getSalario());
        }
        areaModel.addSeries(legenda);
        
        areaModel.setTitle("Gráfico");
        areaModel.setLegendPosition("ne");
        areaModel.setShowPointLabels(true); // ponto de marcação 
        areaModel.setSeriesColors("66cc66, 93b75f, E7E658, cc6666");

        Axis xAxis = new CategoryAxis("Pessoas");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Salário");
        yAxis.setMin(0);
        yAxis.setMax(10000);
	}

}
