package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidade.EPessoa;

public class PessoaDAO {
	
	private static PessoaDAO instance;
	protected EntityManager entityManager;
	
//	verificar se instancia nao esta vazia
	public static PessoaDAO getInstance() {
		if (instance == null) {
			instance = new PessoaDAO();
		}
		
		return instance;
	}
	
//	construtor
	private PessoaDAO() {
		entityManager = getEntityManager();
	}

//	criar tabela no banco se caso não existir
	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("unit-jsf"); // passar nome da unit do persistence.xml
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		
		return entityManager;
	}
	
	public void salvar(EPessoa pessoa) {
		try {
			entityManager.getTransaction().begin(); // inicia
			entityManager.persist(pessoa); // persiste objeto
			entityManager.getTransaction().commit(); // confirma
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback(); // cancelar operação
		}
	}
	
	public EPessoa buscarPorID(final long id) {  // final pq nao vai alterar valor apos passado
		return entityManager.find(EPessoa.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<EPessoa> listarTodos() {
		return entityManager.createQuery("FROM " + EPessoa.class.getName()).getResultList();
	}


	public void alterar(EPessoa pessoa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(pessoa); // altera ou salva
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remover(EPessoa pessoa) {
		try {
			entityManager.getTransaction().begin();
			pessoa = entityManager.find(EPessoa.class, pessoa.getId());
			entityManager.remove(pessoa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removerPorId(final long id) {
		try {
			EPessoa pessoa = buscarPorID(id);
			remover(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
