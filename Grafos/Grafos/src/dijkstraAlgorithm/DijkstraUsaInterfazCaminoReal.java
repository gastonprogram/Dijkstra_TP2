package dijkstraAlgorithm;

import api.ConjuntoTDA;
import api.GrafoTDA;
import imp.ConjuntoLD;
import imp.GrafoMA;

public class DijkstraUsaInterfazCaminoReal {

    public static GrafoTDA dijkstraCaminosReales(GrafoTDA grafo, int origen) {
        ConjuntoTDA conjuntoVertices = grafo.vertices();
        int[] etiquetas = new int[55];
        int[] distancias = new int[55];
        int[] predecesores = new int[55];
        boolean[] visitados = new boolean[55];
        int cantidadVertices = 0;

        // Copiar vértices
        ConjuntoTDA aux = new ConjuntoLD(); aux.inicializarConjunto();
        while (!conjuntoVertices.conjuntoVacio()) {
            int x = conjuntoVertices.elegir();
            conjuntoVertices.sacar(x);
            etiquetas[cantidadVertices++] = x;
            aux.agregar(x);
        }
        while (!aux.conjuntoVacio()) {
            int x = aux.elegir(); aux.sacar(x);
            conjuntoVertices.agregar(x);
        }

        for (int i = 0; i < cantidadVertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
            predecesores[i] = -1;
            visitados[i] = false;
            if (etiquetas[i] == origen)
                distancias[i] = 0;
        }

        for (int i = 0; i < cantidadVertices - 1; i++) {
            int u = seleccionarVerticeMinimo(distancias, visitados, cantidadVertices);
            if (u == -1) break;
            visitados[u] = true;

            for (int v = 0; v < cantidadVertices; v++) {
                if (!visitados[v] && grafo.existeArista(etiquetas[u], etiquetas[v])) {
                    int peso = grafo.pesoArista(etiquetas[u], etiquetas[v]);
                    int nuevaDistancia = distancias[u] + peso;
                    if (nuevaDistancia < distancias[v]) {
                        distancias[v] = nuevaDistancia;
                        predecesores[v] = u;
                    }
                }
            }
        }

        // Construir grafo de caminos reales
        GrafoTDA grafoResultado = new GrafoMA();
        grafoResultado.inicializarGrafo();
        for (int i = 0; i < cantidadVertices; i++) {
            grafoResultado.agregarVertice(etiquetas[i]);
        }

        for (int i = 0; i < cantidadVertices; i++) {
            if (predecesores[i] != -1) {
                int desde = etiquetas[predecesores[i]];
                int hasta = etiquetas[i];
                int pesoOriginal = grafo.pesoArista(desde, hasta);
                grafoResultado.agregarArista(desde, hasta, pesoOriginal);
            }
        }

        return grafoResultado;
    }

    private static int seleccionarVerticeMinimo(int[] distancias, boolean[] visitados, int cantidad) {
        int minimo = Integer.MAX_VALUE, indice = -1;
        for (int i = 0; i < cantidad; i++) {
            if (!visitados[i] && distancias[i] < minimo) {
                minimo = distancias[i];
                indice = i;
            }
        }
        return indice;
    }
}
