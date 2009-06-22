package com.pss.features.monitoracao.agente1.repositoy;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.core.util.GenericPersistence;
import com.pss.features.monitoracao.agente1.model.Agente1;
import com.pss.features.monitoracao.agente1.repositoy.interfaces.Agente1ObserverSujeito;
import com.pss.features.seguranca.model.Usuario;

public class Agente1RepositoryHibernate implements Agente1ObserverSujeito {

	private static Agente1RepositoryHibernate instance = null;
	private GenericPersistence<Agente1> genericPersistence = new GenericPersistence<Agente1>(Agente1.class, "defaultUnit");
	
	public static Agente1RepositoryHibernate getInstance() {
		if (instance == null) {
			instance = new Agente1RepositoryHibernate();
		}
		return instance;
	}
	
	public void adicionarObservador(Agente1 agente) throws SQLException {
		genericPersistence.save(agente);
	}

	public List<Agente1> listarSujeitosPorAtivo(Ativo ativo) {
		return genericPersistence.listByQuery("findByAtivo", ativo);
	}

	public void removerObservador(Agente1 agente) throws SQLException, NoResultException {
		genericPersistence.remove(agente, agente.getId());
	}

	public Agente1 buscarAgente1PorId(Integer id) throws NoResultException {
		return genericPersistence.findByNamedQuery("findById", id);
	}

	public List<Agente1> listarAtivoPorUsuario(Usuario usuario) {
		return genericPersistence.listByQuery("findByUsuario", usuario);
	}

	public List<Agente1> listarUsuariosComMonitoracao() {
		return genericPersistence.listByQuery("listAllByUser");
	}

}
