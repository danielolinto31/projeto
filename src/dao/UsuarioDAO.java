package dao;

import java.util.List;

import javax.persistence.EntityManager;

public class UsuarioDAO<T> {
	
	private final Class<T> classe;

	public UsuarioDAO(Class<T> classe) {
		this.classe = classe;
	}

	// Lista usuários por nome crescente
	
	@SuppressWarnings("unchecked")
	public List<T> listaUsuario() {
		EntityManager em = new JPAUtil().getEntityManager();
		List<T> listaUsuario = em.createQuery("select c from " + classe.getName() + " c order by c.nome asc").getResultList();
		
		em.close();
		return listaUsuario;
	}
}
