package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Estado;
import dao.DAO;

@ManagedBean @ViewScoped
public class EstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Estado estado = new Estado();
	private List<Estado> estados;
	
	public String grava() {
		DAO<Estado> dao = new DAO<Estado>(Estado.class);
		
		if (estado.getId() == null) {
			dao.adiciona(estado);
		} else {
			dao.atualiza(estado);
		}
		estados = dao.listaTodos();
		this.estado = new Estado();
		
		return "estados?faces-redirect=true";
	}

	public List<Estado> getEstados() {
		if (estados == null) {
			System.out.println("Carregando estados...");
			DAO<Estado> dao = new DAO<Estado>(Estado.class);
			estados = dao.listaTodos();
		}
		return estados;
	}
	
	public String remove(Estado estado) {
		DAO<Estado> dao = new DAO<Estado>(Estado.class);
		dao.remove(estado);
		this.estados = dao.listaTodos();
		
		return "estados?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.estado = new Estado();
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
