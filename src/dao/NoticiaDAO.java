package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class NoticiaDAO<T> {
	
	private final Class<T> classe;

	public NoticiaDAO(Class<T> classe) {
		this.classe = classe;
	}
	
	// Lista notícias em ordem decrescente pelo ID
	
	@SuppressWarnings("unchecked") 
	public List<T> listaNoticia() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaNoticia = em.createQuery("select c from " + classe.getName() + " c order by c.id desc").getResultList();

		em.close();
		return listaNoticia;
	}
	
	public T buscaPorTitulo(String titulo) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, titulo);
		em.close();
		return instancia;
	}

}
