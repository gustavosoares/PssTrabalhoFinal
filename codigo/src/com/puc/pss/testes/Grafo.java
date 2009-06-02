package com.puc.pss.testes;

/**
 * Implementa um grafo naoo direcionado, sem loops, sem duplas arestas e
 * sem pesos.
 */
public class Grafo {
    
    private java.util.Set vertices;
    
    /**
     * Cria um grafo vazio
     */
    public Grafo() {
    	vertices = new java.util.HashSet();
    }
    
    /**
     * Adiciona um vertice ao grafo
     * @param vertice Vertice a ser adicionado
     */
    public void adicionar(Vertice vertice) {
		if (!vertices.contains(vertice)) vertices.add(vertice);
    }
    
    /**
     * Cria uma aresta nao direcional no grafo entre dois vertices
     * @param A vertice A
     * @param B vertice B
     * @throws Exception Se houver uma tentativa de criar loops
     */
    public void adicionarAresta(Vertice A, Vertice B) throws Exception {
    	A.ligar(B);
    }
    
    /**
     * Remove um vertice do grafo
     * @param vertice Vertice a ser removido
     */
    public void remover(Vertice vertice) {
		if (vertices.contains(vertice)) {
		    vertice.desligarTodos();
		    vertices.remove(vertice);
		}
    }
    
    /**
     * Remove aresta, se existir, entre dois vertices do grafo
     * @param A vertice A
     * @param B vertice B
     */
    public void removerAresta(Vertice A, Vertice B) {
    	A.desligar(B);
    }
    
    /**
     * Retorna tamanho do grafo
     * @return Tamanho do grafo(nÃºmero de vÃ©rtices)
     */
    public int getTamanho() {
    	return vertices.size();
    }
    
    /**
     * Limpa grafo. Remove todos os vÃ©rtices
     */
    public void clear() {
    	vertices.clear();
    }
    
    /**
     * Retorna vertices do grafo
     * @return Conjunto de vertices do grafo
     */
    public java.util.Set getVertices() {
    	return vertices;
    }
    
}
