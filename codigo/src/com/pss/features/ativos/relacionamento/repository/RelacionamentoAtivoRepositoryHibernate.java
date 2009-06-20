package com.pss.features.ativos.relacionamento.repository;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.exception.ConstraintViolationException;

import com.pss.core.model.Ativo;
import com.pss.core.util.GenericPersistence;
import com.pss.features.ativos.relacionamento.model.RelacionamentoAtivo;
import com.pss.features.ativos.relacionamento.repository.interfaces.RelacionamentoAtivoRepository;

public class RelacionamentoAtivoRepositoryHibernate implements RelacionamentoAtivoRepository {
	
	private static RelacionamentoAtivoRepositoryHibernate instance = null;
	private GenericPersistence<RelacionamentoAtivo> genericPersistence = new GenericPersistence<RelacionamentoAtivo>(RelacionamentoAtivo.class, "defaultUnit");
	
	public static RelacionamentoAtivoRepositoryHibernate getInstance() {
		if (instance == null) {
			instance = new RelacionamentoAtivoRepositoryHibernate();
		}
		return instance;
	}

	public List buscarAtivosFilhosPorAtivoPai(Ativo ativoPai) throws NoResultException {
		return genericPersistence.listByQuery("findByAtivoIdPai", ativoPai);
	}

	public void cadastrarRelacionamento(Ativo ativoPai,Ativo ativoFilho) throws SQLException, NoResultException, ConstraintViolationException {
		RelacionamentoAtivo r = null;
		r = new RelacionamentoAtivo();
		r.setAtivoPai(ativoPai);
		r.setAtivoFilho(ativoFilho);
		genericPersistence.save(r);
	}

	public RelacionamentoAtivo buscarRelacionamento(Ativo ativoPai, Ativo ativoFilho) throws NoResultException {
		return genericPersistence.findByNamedQuery("findByRelacionamento", ativoPai, ativoFilho);
	}

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoFilho(Ativo ativoFilho) throws NoResultException {
		return genericPersistence.listByQuery("findByAtivoFilho", ativoFilho);
	}

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivoPai(Ativo ativoPai) throws NoResultException {
		return genericPersistence.listByQuery("findByAtivoPai", ativoPai);
	}

	public void removerRelacionamento(RelacionamentoAtivo relacionamento) throws NoResultException, SQLException {
		genericPersistence.remove(relacionamento, relacionamento.getId());
	}

	public List<RelacionamentoAtivo> buscarRelacionamentoPorAtivo(Ativo ativo) throws NoResultException {
		return genericPersistence.listByQuery("findByAtivo", ativo);
	}

	public List<RelacionamentoAtivo> listarRelacionamentos() {
		return genericPersistence.listAll();
	}

	public RelacionamentoAtivo buscarRelacionamentoPorId(Integer id) throws NoResultException {
		return genericPersistence.findByNamedQuery("findByRelacionamentoId", id);
	}
		
}
