package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class PartidaDAO<T> {
	
	private final Class<T> classe;

	public PartidaDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	// Lista as partidas por finalizado e data crescentes

	@SuppressWarnings("unchecked")
	public List<T> listaPartida() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaPartida = em.createQuery("select c from " + classe.getName() + " c order by c.finalizado asc, c.data asc").getResultList();

		em.close();
		return listaPartida;
	}
	
	// Lista partidas não finalizadas e por data crescente
	
	@SuppressWarnings("unchecked")
	public List<T> listaPartidaFinalizado() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaPartidaFinalizado = em.createQuery("select c from " + classe.getName() + " c where c.finalizado = 'Não' order by c.data asc").getResultList();

		em.close();
		return listaPartidaFinalizado;
	}

}
