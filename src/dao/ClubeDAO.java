package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class ClubeDAO<T> {

	private final Class<T> classe;

	public ClubeDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	// Lista clubes em ordem crescente pelo nome
	
	@SuppressWarnings("unchecked")
	public List<T> listaClube() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaClube = em.createQuery("select c from " + classe.getName() + " c order by c.nome asc").getResultList();

		em.close();
		return listaClube;
	}
	
}
