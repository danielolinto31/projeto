package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class FaseDAO<T> {
	
	private final Class<T> classe;

	public FaseDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	// Lista fases em ordem decrescente pelo ano
	
	@SuppressWarnings("unchecked") 
	public List<T> listaFase() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaFase = em.createQuery("select c from " + classe.getName() + " c order by c.ano desc").getResultList();

		em.close();
		return listaFase;
	}

}
