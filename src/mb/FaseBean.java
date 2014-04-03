package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Campeonato;
import modelo.Clube;
import modelo.Fase;
import dao.DAO;

@ManagedBean @ViewScoped
public class FaseBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Fase fase = new Fase();
	
	private String idCampeonato;
	private String idCampeao;
	
	private List<Fase> fases;
	
	public String grava() {
		DAO<Campeonato> daoCampeonato = new DAO<Campeonato>(Campeonato.class);
		Campeonato campeonato = daoCampeonato.buscaPorId(Long.parseLong(idCampeonato));
		fase.setCampeonato(campeonato);
		
		DAO<Fase> dao = new DAO<Fase>(Fase.class);
		if (fase.getId() == null) {
			dao.adiciona(fase);
		} else {
			DAO<Clube> daoCampeao = new DAO<Clube>(Clube.class);
			Clube campeao = daoCampeao.buscaPorId(Long.parseLong(idCampeao));
			fase.setCampeao(campeao);
			
			dao.atualiza(fase);
		}
		fases = dao.listaTodos();
		this.fase = new Fase();
		
		return "fases?faces-redirect=true";
	}

	public List<Fase> getFases() {
		if (fases == null) {
			System.out.println("Carregando fases do campeonato...");
			fases = new DAO<Fase>(Fase.class).listaTodos();
		}
		return fases;
	}
	
	public String remove(Fase fase) {
		DAO<Fase> dao = new DAO<Fase>(Fase.class);
		dao.remove(fase);
		this.fases = dao.listaTodos();
		
		return "fases?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.fase = new Fase();
	}
	
	public Fase getFase() {
		return this.fase;
	}
	
	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public String getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getIdCampeao() {
		return idCampeao;
	}

	public void setIdCampeao(String idCampeao) {
		this.idCampeao = idCampeao;
	}
	
}