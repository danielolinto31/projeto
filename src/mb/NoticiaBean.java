package mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Noticia;
import dao.DAO;
import dao.NoticiaDAO;

@ManagedBean @ViewScoped
public class NoticiaBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Noticia noticia = new Noticia();
	private List<Noticia> noticias;
	private List<Noticia> noticiasOrdem;
	
	public String grava() {
		DAO<Noticia> dao = new DAO<Noticia>(Noticia.class);
		
		if (noticia.getId() == null) {
			noticia.setData(new Date());
			dao.adiciona(noticia);
		} else {
			dao.atualiza(noticia);
		}
		noticias = dao.listaTodos();
		this.noticia = new Noticia();
		
		return "noticias?faces-redirect=true";
	}

	public List<Noticia> getNoticias() {
		if (noticias == null) {
			System.out.println("Carregando notícias...");
			noticias = new DAO<Noticia>(Noticia.class).listaTodos();
		}
		return noticias;
	}
	
	public List<Noticia> getNoticiasOrdem() {
		if (noticiasOrdem == null) {
			System.out.println("Carregando notícias...");
			noticiasOrdem = new NoticiaDAO<Noticia>(Noticia.class).listaNoticia();
		}
		return noticiasOrdem;
	}
	
	public String remove(Noticia noticia) {
		DAO<Noticia> dao = new DAO<Noticia>(Noticia.class);
		NoticiaDAO<Noticia> noticiadao = new NoticiaDAO<Noticia>(Noticia.class);
		dao.remove(noticia);
		this.noticiasOrdem = noticiadao.listaNoticia();
		
		return "noticias?faces-redirect=true";
	}
	
	public void cancela() {
		FacesContext.getCurrentInstance().getViewRoot().getChildren().clear();
		this.noticia = new Noticia();
	}
	
	public Noticia getNoticia() {
		return this.noticia;
	}
	
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
}
