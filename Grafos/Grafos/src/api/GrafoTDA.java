package api;

public interface GrafoTDA {
	void inicializarGrafo();		// Sin precondiciones
	void agregarVertice(int v);		// Grafo inicializado
	void eliminarVertice(int v);	// Grafo inicializado y vértice existente
	ConjuntoTDA vertices();			// Grafo inicializado
	void agregarArista(int origen, int destino, int peso);	// Grafo inicializado, vértices existentes
	void eliminarArista(int origen, int destino);			// Grafo inicializado, arista existente
	boolean existeArista(int origen, int destino);			// Grafo inicializado, vértices existentes
	int pesoArista(int origen, int destino);
}
