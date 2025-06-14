package dijkstraAlgorithm;

import api.ConjuntoTDA;
import api.GrafoTDA;
import imp.ConjuntoLD;
import imp.GrafoMA;

public class DijkstraCostosMinimos {

    public static GrafoTDA dijkstraCostosMinimos(GrafoTDA grafo, int origen) {
        ConjuntoTDA conjuntoVertices = grafo.vertices();
        int[] etiquetas = new int[55];
        int[] distancias = new int[55];
        boolean[] visitados = new boolean[55];
        int cantidadVertices = 0;

        //copiar vertices
        while (!conjuntoVertices.conjuntoVacio()) {
            int vertice = conjuntoVertices.elegir();
            conjuntoVertices.sacar(vertice);
            etiquetas[cantidadVertices++] = vertice;
        }

        // inicializar el valor de las distancias/peso
        for (int i = 0; i < cantidadVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitados[i] = false;
            if (etiquetas[i] == origen)
                distancias[i] = 0;
        }

        //principal
        for (int i = 0; i < cantidadVertices - 1; i++) {
            int indiceMinimo = seleccionarVerticeMinimo(distancias, visitados, cantidadVertices);
            if (indiceMinimo == -1) {
            	break;
            }
            visitados[indiceMinimo] = true;

            //relaxation
            for (int j = 0; j < cantidadVertices; j++) {
                if (!visitados[j] && grafo.existeArista(etiquetas[indiceMinimo], etiquetas[j])) {
                    int nuevaDistancia = distancias[indiceMinimo] + grafo.pesoArista(etiquetas[indiceMinimo], etiquetas[j]);
                    if (nuevaDistancia < distancias[j])
                        distancias[j] = nuevaDistancia;
                }
            }
        }

        // armar grafo costos
        GrafoTDA grafoResultado = new GrafoMA();
        grafoResultado.inicializarGrafo();
        for (int i = 0; i < cantidadVertices; i++)
            grafoResultado.agregarVertice(etiquetas[i]);

        int indiceOrigen = buscarIndice(etiquetas, cantidadVertices, origen);
        for (int i = 0; i < cantidadVertices; i++) {
            if (i != indiceOrigen && distancias[i] != Integer.MAX_VALUE) {
                grafoResultado.agregarArista(origen, etiquetas[i], distancias[i]);
            }
        }

        return grafoResultado;
    }

    //buscamos en distancias el indice del vertice no visitado con menor distancia
    private static int seleccionarVerticeMinimo(int[] distancias, boolean[] visitados, int cantidad) {
        int minimo = Integer.MAX_VALUE; 
        int indiceMinimo = -1;
        for (int i = 0; i < cantidad; i++) {
            if (!visitados[i] && distancias[i] < minimo) {
                minimo = distancias[i];
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }

    
    //buscamos en eitquetas el indice de origen
    private static int buscarIndice(int[] etiquetas, int cantidad, int valorBuscado) {
        for (int i = 0; i < cantidad; i++) {
        	if (etiquetas[i] == valorBuscado) {
        		return i;
        	}
        }
            
        return -1;
    }
}
