package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class EstadioDAO<T> {
	
	private final Class<T> classe;

	public EstadioDAO(Class<T> classe) {
		this.classe = classe;
	}

	// Lista estádios pelo apelido em ordem crescente
	
	@SuppressWarnings("unchecked")
	public List<T> listaEstadio() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaEstadio = em.createQuery("select c from " + classe.getName() + " c order by c.apelido asc").getResultList();

		em.close();
		return listaEstadio;
	}
		
}
