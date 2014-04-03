package mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Estadio;
import modelo.Partida;
import dao.DAO;
import dao.PartidaDAO;

@ManagedBean @ViewScoped
public class PartidaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Partida partida = new Partida();
	
	private String idEstadio;
	
	private List<Partida> partidas;
	private List<Partida> partidasOrdem;
	private List<Partida> partidasOrdemFinalizado;
	
	public String grava() {
		DAO<Estadio> daoEstadio = new DAO<Estadio>(Estadio.class);
		
		Estadio estadio = daoEstadio.buscaPorId(Long.parseLong(idEstadio));
		partida.setEstadio(estadio);
		
		DAO<Partida> dao = new DAO<Partida>(Partida.class);
		PartidaDAO<Partida> partidadao = new PartidaDAO<Partida>(Partida.class);
		if(partida.getId() == null) {
			dao.adiciona(partida);
		} else {
			dao.atualiza(this.partida);
		}
		
		this.partida = new Partida();
		partidas = partidadao.listaPartida();
		
		return "campeonato?faces-redirect=true";
	}
	
	public List<Partida> getPartidas() {
		if (partidas == null) {
			DAO<Partida> dao = new DAO<Partida>(Partida.class);
			partidas = dao.listaTodos();
		}
		return partidas;
	}
	
	public List<Partida> getPartidasOrdem() {
		if (partidasOrdem == null) {
			PartidaDAO<Partida> partidadao = new PartidaDAO<Partida>(Partida.class);
			partidasOrdem = partidadao.listaPartida();
			this.partida.setFinalizado("Não");
		}
		return partidasOrdem;
	}
	
	public List<Partida> getPartidasOrdemFinalizado() {
		if (partidasOrdemFinalizado == null) {
			PartidaDAO<Partida> partidadao = new PartidaDAO<Partida>(Partida.class);
			partidasOrdemFinalizado = partidadao.listaPartidaFinalizado();
		}
		return partidasOrdemFinalizado;
	}
	
	public String remove(Partida partida) {
		DAO<Partida> dao = new DAO<Partida>(Partida.class);
		PartidaDAO<Partida> partidadao = new PartidaDAO<Partida>(Partida.class);
		dao.remove(partida);
		this.partidasOrdem = partidadao.listaPartida();
		
		return "campeonato?faces-redirect=true";
	}
	
	public void cancela() {
		this.partida = new Partida();
	}

	public Partida getPartida() {
		return partida;
	}
	
	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public String getIdEstadio() {
		return idEstadio;
	}

	public void setIdEstadio(String idEstadio) {
		this.idEstadio = idEstadio;
	}
	
}
