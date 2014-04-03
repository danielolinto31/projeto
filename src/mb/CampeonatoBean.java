package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Campeonato;
import modelo.Clube;
import dao.CampeonatoDAO;
import dao.DAO;

@ManagedBean @ViewScoped
public class CampeonatoBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Campeonato campeonato = new Campeonato();
	private List<Campeonato> campeonatos;
	private List<Campeonato> campeonatosOrdem;
	
	private String idCampeao;
	
	public String grava() {
		DAO<Campeonato> dao = new DAO<Campeonato>(Campeonato.class);
		
		if (campeonato.getId() == null) {
			dao.adiciona(campeonato);
		} else {
			DAO<Clube> daoCampeao = new DAO<Clube>(Clube.class);
			Clube campeao = daoCampeao.buscaPorId(Long.parseLong(idCampeao));
			campeonato.setCampeao(campeao);
			
			dao.atualiza(campeonato);
		}
		campeonatos = dao.listaTodos();
		this.campeonato = new Campeonato();
		
		return "campeonatos?faces-redirect=true";
	}

	public List<Campeonato> getCampeonatos() {
		if (campeonatos == null) {
			System.out.println("Carregando campeonatos...");
			campeonatos = new DAO<Campeonato>(Campeonato.class).listaTodos();
		}
		return campeonatos;
	}
	
	public List<Campeonato> getCampeonatosOrdem() {
		if (campeonatosOrdem == null) {
			System.out.println("Carregando campeonatos...");
			campeonatosOrdem = new CampeonatoDAO<Campeonato>(Campeonato.class).listaCampeonato();
		}
		return campeonatosOrdem;
	}
	
	public String remove(Campeonato campeonato) {
		DAO<Campeonato> dao = new DAO<Campeonato>(Campeonato.class);
		CampeonatoDAO<Campeonato> campeonatodao = new CampeonatoDAO<Campeonato>(Campeonato.class);
		campeonato.setCampeao(new Clube());
		dao.remove(campeonato);
		this.campeonatosOrdem = campeonatodao.listaCampeonato();
		
		return "campeonatos?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.campeonato = new Campeonato();
	}
	
	public Campeonato getCampeonato() {
		return this.campeonato;
	}
	
	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public String getIdCampeao() {
		return idCampeao;
	}

	public void setIdCampeao(String idCampeao) {
		this.idCampeao = idCampeao;
	}
	
}
