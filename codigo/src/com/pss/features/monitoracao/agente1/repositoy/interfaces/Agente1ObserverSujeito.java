package com.pss.features.monitoracao.agente1.repositoy.interfaces;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import com.pss.core.model.Ativo;
import com.pss.features.monitoracao.agente1.model.Agente1;
import com.pss.features.seguranca.model.Usuario;

public interface Agente1ObserverSujeito {

	public void adicionarObservador(Agente1 agente) throws SQLException;
	public void removerObservador(Agente1 agente) throws SQLException, NoResultException;
	public List<Agente1> listarSujeitosPorAtivo(Ativo ativo);
	public List<Agente1> listarAtivoPorUsuario(Usuario usuario);
	public List<Agente1> listarUsuariosComMonitoracao();
	public Agente1 buscarAgente1PorId(Integer id) throws NoResultException;
	
}
