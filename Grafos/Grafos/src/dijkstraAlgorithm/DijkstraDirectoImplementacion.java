package dijkstraAlgorithm;

import api.GrafoTDA;
import imp.GrafoMA;

public class DijkstraDirectoImplementacion {

    public static void dijkstra(GrafoMA grafo, int origen) {
        int n = grafo.cantidad;
        int[] dist = new int[n];
        boolean[] sptSet = new boolean[n];
        int[] prev = new int[n];
        int[] etiquetas = grafo.etiquetas;

        // Inicialización
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            prev[i] = -1;
        }

        int origenIndex = grafo.vertice2indice(origen);
        dist[origenIndex] = 0;

        // Lógica principal de Dijkstra
        for (int count = 0; count < n - 1; count++) {
            int u = minDist(dist, sptSet, n);
            if (u == -1) break; // Si no se puede continuar
            sptSet[u] = true;

            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && grafo.mAdy[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + grafo.mAdy[u][v] < dist[v]) {

                    dist[v] = dist[u] + grafo.mAdy[u][v];
                    prev[v] = u;
                }
            }
        }

        // Mostrar resultados
        for (int i = 0; i < n; i++) {
            if (i != origenIndex && dist[i] != Integer.MAX_VALUE) {
                System.out.print("Camino de " + origen + " a " + etiquetas[i] + ": ");
                imprimirCamino(grafo, prev, i);
                System.out.println(" - Costo: " + dist[i]);
            }
        }
    }

    private static int minDist(int[] dist, boolean[] sptSet, int n) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < n; i++) {
            if (!sptSet[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void imprimirCamino(GrafoMA grafo, int[] prev, int i) {
        if (prev[i] != -1) {
            imprimirCamino(grafo, prev, prev[i]);
        }
        System.out.print(grafo.etiquetas[i] + " ");
    }
}
