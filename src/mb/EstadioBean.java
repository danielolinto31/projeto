package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Estadio;
import dao.DAO;
import dao.EstadioDAO;

@ManagedBean @ViewScoped
public class EstadioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Estadio estadio = new Estadio();
	private List<Estadio> estadios;
	private List<Estadio> estadiosOrdem;
	
	public String grava() {
		DAO<Estadio> dao = new DAO<Estadio>(Estadio.class);
		
		if (estadio.getId() == null) {
			dao.adiciona(estadio);
		} else {
			dao.atualiza(estadio);
		}
		estadios = dao.listaTodos();
		this.estadio = new Estadio();
		
		return "estadios?faces-redirect=true";
	}

	public List<Estadio> getEstadios() {
		if (estadios == null) {
			System.out.println("Carregando estádios...");
			DAO<Estadio> dao = new DAO<Estadio>(Estadio.class);
			estadios = dao.listaTodos();
		}
		return estadios;
	}
	
	public List<Estadio> getEstadiosOrdem() {
		if (estadiosOrdem == null) {
			System.out.println("Carregando estádios...");
			EstadioDAO<Estadio> estadiodao = new EstadioDAO<Estadio>(Estadio.class);
			estadiosOrdem = estadiodao.listaEstadio();
		}
		return estadiosOrdem;
	}
	
	public String remove(Estadio estadio) {
		DAO<Estadio> dao = new DAO<Estadio>(Estadio.class);
		EstadioDAO<Estadio> estadiodao = new EstadioDAO<Estadio>(Estadio.class);
		dao.remove(estadio);
		this.estadiosOrdem = estadiodao.listaEstadio();
		
		return "estadios?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.estadio = new Estadio();
	}
	
	public Estadio getEstadio() {
		return this.estadio;
	}
	
	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}
	
}
