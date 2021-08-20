package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidade.EProduto;

public class ProdutoDAO {
	
	private static ProdutoDAO instance;
	protected EntityManager entityManager;

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}

		return instance;
	}

	private ProdutoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit-jsf");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}



	public void salvar(EProduto produto) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(produto);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	
	public EProduto buscarPorID(final long id) {
		return entityManager.find(EProduto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<EProduto> listarTodos() {
		return entityManager.createQuery("FROM " + EProduto.class.getName()).getResultList();
	}


	public void alterar(EProduto produto) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(produto);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remover(EProduto produto) {
		try {
			entityManager.getTransaction().begin();
			produto = entityManager.find(EProduto.class, produto.getId());
			entityManager.remove(produto);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removerPorID(final long id) {
		try {
			EProduto produto = buscarPorID(id);
			remover(produto);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
