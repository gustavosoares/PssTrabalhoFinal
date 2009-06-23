package com.pss.features.seguranca.bo;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.facade.FacadeBO;
import com.pss.core.facade.FacadeUtil;
import com.pss.features.seguranca.factories.UsuarioRepositoryFactory;
import com.pss.features.seguranca.model.Usuario;
import com.pss.features.seguranca.repository.interfaces.UsuarioRepository;

public class UsuarioBO implements UsuarioRepository {

	private static String PERSISTENCE_TYPE = "hibernate";
	private static UsuarioBO instance = null;
	private static UsuarioRepository instanceRepository = null;
	
	public static UsuarioBO getInstance() {
		if (instance == null) {
			instance = new UsuarioBO();
			instanceRepository = UsuarioRepositoryFactory.getInstance(PERSISTENCE_TYPE);
		}		
		return instance;
	}
	
	public Usuario buscarUsuarioPorEmaileSenha(String email, String senha) throws NoResultException {
		return instanceRepository.buscarUsuarioPorEmaileSenha(email, FacadeUtil.encriptar(senha.trim()));
	}

	public Usuario buscarUsuarioPorId(Integer id) throws NoResultException {
		return instanceRepository.buscarUsuarioPorId(id);
	}

	public void cadastrarUsuario(Usuario usuario) throws SQLException {
		instanceRepository.cadastrarUsuario(usuario);
	}

	public List<Usuario> listarUsuarios() {
		return instanceRepository.listarUsuarios();
	}

	public void removerUsuario(Usuario usuario) throws SQLException, NoResultException {
		
		boolean relacionamento = FacadeUtil.featureHabilitada("relacionamento");
		boolean monitoracao = FacadeUtil.featureHabilitada("monitoracao");
		FacadeUtil.log(this, "verificando se existe relacionamento: "+relacionamento);
		FacadeUtil.log(this, "verificando se existe monitoracao: "+monitoracao);
		if (monitoracao) {
			FacadeUtil.log(this, "removendo as monitoracoes");
			com.pss.features.monitoracao.agente1.bo.Agente1BO agente1BO = FacadeBO.getAgente1BOInstance();
			List lista_agentes = agente1BO.listarAtivoPorUsuario(usuario);
			FacadeUtil.log(this, "agentes: "+lista_agentes);
			for (int i = 0; i < lista_agentes.size(); i++) {
				com.pss.features.monitoracao.agente1.model.Agente1 agente1 = (com.pss.features.monitoracao.agente1.model.Agente1) lista_agentes.get(i);
				FacadeUtil.log(this, "removendo agente: "+agente1);
				agente1BO.removerObservador(agente1);
			}

		}
		
		instanceRepository.removerUsuario(usuario);
	}

}
