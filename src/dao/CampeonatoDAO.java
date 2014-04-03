package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class CampeonatoDAO<T> {
	
	private final Class<T> classe;

	public CampeonatoDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	// Lista campeonatos em ordem decrescente por ativo
	
	@SuppressWarnings("unchecked") 
	public List<T> listaCampeonato() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaCampeonato = em.createQuery("select c from " + classe.getName() + " c order by c.ativo desc").getResultList();

		em.close();
		return listaCampeonato;
	}

}
