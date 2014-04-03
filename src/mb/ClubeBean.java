package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Clube;
import dao.ClubeDAO;
import dao.DAO;

@ManagedBean @ViewScoped
public class ClubeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Clube clube = new Clube();
	private List<Clube> clubes;
	private List<Clube> clubesOrdem; 

	public String grava() {
		DAO<Clube> dao = new DAO<Clube>(Clube.class);
		
		if (clube.getId() == null) {
			dao.adiciona(clube);
		} else {
			dao.atualiza(clube);
		}
		clubes = dao.listaTodos();
		this.clube = new Clube();

		return "clubes?faces-redirect=true";
	}

	public List<Clube> getClubes() {
		if (clubes == null) {
			System.out.println("Carregando clubes...");
			clubes = new DAO<Clube>(Clube.class).listaTodos();
		}
		return clubes;
	}
	
	public List<Clube> getClubesOrdem() {
		if (clubesOrdem == null) {
			System.out.println("Carregando clubes...");
			clubesOrdem = new ClubeDAO<Clube>(Clube.class).listaClube();
		}
		return clubesOrdem;
	}
	
	public String remove(Clube clube) {
		DAO<Clube> dao = new DAO<Clube>(Clube.class);
		ClubeDAO<Clube> clubedao = new ClubeDAO<Clube>(Clube.class);
		dao.remove(clube);
		this.clubes = clubedao.listaClube();
		
		return "clubes?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.clube = new Clube();
	}
	
	public Clube getClube() {
		return this.clube;
	}
	
	public void setClube(Clube clube) {
		this.clube = clube;
	}   
	
}
