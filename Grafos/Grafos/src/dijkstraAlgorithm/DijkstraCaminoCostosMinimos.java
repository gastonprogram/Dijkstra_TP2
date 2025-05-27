package dijkstraAlgorithm;

import api.ConjuntoTDA;
import api.GrafoTDA;
import imp.ConjuntoLD;
import imp.GrafoMA;

public class DijkstraCaminoCostosMinimos {

    public static GrafoTDA dijkstraCaminosReales(GrafoTDA grafo, int origen) {
        ConjuntoTDA conjuntoVertices = grafo.vertices();
        int[] etiquetas = new int[55];
        int[] distancias = new int[55];
        int[] predecesores = new int[55];
        boolean[] visitados = new boolean[55]; // cloud
        int cantidadVertices = 0;

        //copiar vertices
        while (!conjuntoVertices.conjuntoVacio()) {
            int x = conjuntoVertices.elegir();
            conjuntoVertices.sacar(x);
            etiquetas[cantidadVertices++] = x;
        }
        
        // inicializar el valor de las distancias/peso
        for (int i = 0; i < cantidadVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            predecesores[i] = -1;
            visitados[i] = false;
            if (etiquetas[i] == origen)
                distancias[i] = 0;
        }
        
        //principal
        for (int i = 0; i < cantidadVertices - 1; i++){
            int indiceMinimo = seleccionarVerticeMinimo(distancias, visitados, cantidadVertices);
            if (indiceMinimo == -1) {
            	break;
            }
            visitados[indiceMinimo] = true;
            
          //relaxation
            for (int v = 0; v < cantidadVertices; v++){
                if (!visitados[v] && grafo.existeArista(etiquetas[indiceMinimo], etiquetas[v])) {
                    int peso = grafo.pesoArista(etiquetas[indiceMinimo], etiquetas[v]);
                    int nuevaDistancia = distancias[indiceMinimo] + peso;
                    if (nuevaDistancia < distancias[v]){
                        distancias[v] = nuevaDistancia;
                        predecesores[v] = indiceMinimo;
                    }
                }
            }
        }

        // armar grafo con caminos reales
        GrafoTDA grafoResultado = new GrafoMA();
        grafoResultado.inicializarGrafo();
        for (int i = 0; i < cantidadVertices; i++){
            grafoResultado.agregarVertice(etiquetas[i]);
        }

        for (int i = 0; i < cantidadVertices; i++){
            if (predecesores[i] != -1) {
                int desde = etiquetas[predecesores[i]];
                int hasta = etiquetas[i];
                int pesoReal = grafo.pesoArista(desde, hasta);
                grafoResultado.agregarArista(desde, hasta, pesoReal);
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
}
