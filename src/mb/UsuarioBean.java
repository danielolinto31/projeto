package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Usuario;
import dao.DAO;
import dao.UsuarioDAO;

@ManagedBean @ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	private List<Usuario> usuariosOrdem;
	
	public String grava() {
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		if(usuario.getId() == null) {
			dao.adiciona(usuario);
		} else {
			dao.atualiza(this.usuario);
		}
		
		this.usuario = new Usuario();
		usuarios = dao.listaTodos();
		
		return "usuarios?faces-redirect=true";
	}
	
	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
			usuarios = dao.listaTodos();
		}
		return usuarios;
	}
	
	public List<Usuario> getUsuariosOrdem() {
		if (usuariosOrdem == null) {
			UsuarioDAO<Usuario> usuariodao = new UsuarioDAO<Usuario>(Usuario.class);
			usuariosOrdem = usuariodao.listaUsuario();
		}
		return usuariosOrdem;
	}
	
	/*
	public void remove(Usuario usuario) {
		FacesContext context = FacesContext.getCurrentInstance();
		LoginBean loginBean = context.getApplication().evaluateExpressionGet(context, "#{loginBean}", LoginBean.class);
		
		Usuario logado = loginBean.getUsuario();
		
		DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);
		
		if(!logado.equals(usuario)) {
			dao.remove(usuario);
		}
		
		this.usuariosOrdem = dao.listaUsuario();
	}
	*/
	public void cancela() {
		this.usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
