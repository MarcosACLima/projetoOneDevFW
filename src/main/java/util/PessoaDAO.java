package util;

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

}
