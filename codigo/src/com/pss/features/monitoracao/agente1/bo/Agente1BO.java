package com.pss.features.monitoracao.agente1.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.bo.AtivoBO;
import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.core.model.Ativo;
import com.pss.features.monitoracao.agente1.factories.Agente1RepositoryFactory;
import com.pss.features.monitoracao.agente1.model.Agente1;
import com.pss.features.monitoracao.agente1.repositoy.interfaces.Agente1ObserverSujeito;
import com.pss.features.seguranca.model.Usuario;

public class Agente1BO implements Agente1ObserverSujeito {

	private static String PERSISTENCE_TYPE = "hibernate";
	private static Agente1BO instance = null;
	private static AtivoBO instanceAtivo = null;
	private static Agente1ObserverSujeito instanceRepository = null;

	private Agente1BO() {
		
	}
	
	public static Agente1BO getInstance() {
		if (instance == null) {
			instance = new Agente1BO();
			instanceAtivo = FacadeBO.getAtivoBOInstance();
			instanceRepository = Agente1RepositoryFactory.getInstance(PERSISTENCE_TYPE);
		}		
		return instance;
	}
	
	public void adicionarObservador(Agente1 agente) throws SQLException {
		if (FacadeBO.ativoExiste(agente.getAtivo())) {
			instanceRepository.adicionarObservador(agente);
		}
	}

	public List<Agente1> listarSujeitosPorAtivo(Ativo ativo) {
		return instanceRepository.listarSujeitosPorAtivo(ativo);
	}

	public void removerObservador(Agente1 agente) throws SQLException, NoResultException {
		instanceRepository.removerObservador(agente);
	}
	
	public void notificarObservadores(Ativo ativo) {
		List<Agente1> lista_observadores = listarSujeitosPorAtivo(ativo);
		FacadeUtil.log(this, "Notificando os observadores do ativo "+ativo.getNome());
		for (int i=0; i < lista_observadores.size(); i++) {
			Agente1 agente = (Agente1) lista_observadores.get(i);
			Usuario observador = agente.getUsuario();
			FacadeUtil.log(this, "Notificando observador: "+observador.getEmail());
			observador.notificar(ativo.getNome());
		}
	}

	public Agente1 buscarAgente1PorId(Integer id) throws NoResultException {
		return instanceRepository.buscarAgente1PorId(id);
	}

	public List<Agente1> listarAtivoPorUsuario(Usuario usuario) {
		return instanceRepository.listarAtivoPorUsuario(usuario);
	}

	public List<Agente1> listarUsuariosComMonitoracao() {
		return instanceRepository.listarUsuariosComMonitoracao();
	}

}
