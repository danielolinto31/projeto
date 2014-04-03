package modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Partida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer rodada;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
		
	private String mandante;
	private Integer resultado1;
	private Integer resultado2;
	private String visitante;
	private String finalizado;
	
	@ManyToOne
	private Estadio estadio;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getRodada() {
		return rodada;
	}
	public void setRodada(Integer rodada) {
		this.rodada = rodada;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getMandante() {
		return mandante;
	}
	public void setMandante(String mandante) {
		this.mandante = mandante;
	}
	public Integer getResultado1() {
		return resultado1;
	}
	public void setResultado1(Integer resultado1) {
		this.resultado1 = resultado1;
	}
	public Integer getResultado2() {
		return resultado2;
	}
	public void setResultado2(Integer resultado2) {
		this.resultado2 = resultado2;
	}
	public String getVisitante() {
		return visitante;
	}
	public void setVisitante(String visitante) {
		this.visitante = visitante;
	}
	public String getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}
	public Estadio getEstadio() {
		return estadio;
	}
	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}
	
}