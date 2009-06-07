package com.pss.core.testes;

public class Vertice {
    
    private java.util.Set adjacentes;
    private int id;
    
    /**
     * Cria um vertice vazio
     * @param id Identificador do grafo
     */
    public Vertice(int id) {
		this.setId(id);
		adjacentes = new java.util.HashSet();
    }
    
    /**
     * 
     * @param vertice
     * @return
     */
    public boolean isAdjacente(Vertice vertice) {
    	return adjacentes.contains(vertice);
    }
    
    /**
     * Avalia o grau do vertice.
     * @return Grau do vertice
     */
    public int getGrau() {
    	return adjacentes.size();
    }
    
    /**
     * Liga-se com um segundo vertice
     * @param vertice Segundo vertice
     * @throws Exception Se houver uma tentativa de criar loops
     */
    public void ligar(Vertice vertice) throws Exception {
	
		if (vertice == this)
		    throw new Exception("Grafo nao pode conter loops.");
		
		if (!this.adjacentes.contains(vertice)) {
		    this.adjacentes.add(vertice);
		}
		
		if (!vertice.adjacentes.contains(this)) {
		    vertice.adjacentes.add(this);
		}
		
    }
    
    /**
     * Retorna todos os vertices adjacentes
     * a esse.
     * @return Conjunto de vertices adjacentes
     */
    public java.util.Set getAdjacentes() {
    	return adjacentes;
    }
    
    /**
     * Remove uma aresta que liga a este vertice
     * @param vertice vertice que liga a aresta
     */
    public void desligar(Vertice vertice) {
		if (adjacentes.contains(vertice)) {
		    adjacentes.remove(vertice);
		}
		if (vertice.adjacentes.contains(this)) {
		    vertice.adjacentes.remove(this);
		}
    }
    
    /**
     * Remove todas arestas que ligam a este vertice
     */
    void desligarTodos() {
		/* Itera vertices adjacentes */
		for (java.util.Iterator i = adjacentes.iterator(); i.hasNext(); ) {
		    Vertice vertice = (Vertice)i.next();
		    
		    if (vertice.adjacentes.contains(this)) {
			vertice.adjacentes.remove(this);
		    }
		}
		adjacentes.clear();
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
}

