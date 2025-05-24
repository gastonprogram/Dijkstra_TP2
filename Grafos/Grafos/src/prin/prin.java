package prin;

import api.ConjuntoTDA;

import api.GrafoTDA;
import dijkstraAlgorithm.DijkstraCostosMinimos;
import dijkstraAlgorithm.DijkstraCaminoCostosMinimos;
import imp.GrafoMA;

public class prin {

	public static int contarVertices(GrafoTDA g) {
		int cant = 0;
		int x;
		ConjuntoTDA c = g.vertices();
		while(!c.conjuntoVacio()) {
			x = c.elegir();
			c.sacar(x);
			cant++;
		}
		return cant;
	}
		
	public static void mostrarGrafo(GrafoTDA g) {
		String cadena = "";
		ConjuntoTDA v = g.vertices();
		int cantidad = contarVertices(g);
		int[] vertices = new int[cantidad];
		cadena = cadena + "    ";
		int inx = 0;
		while(!v.conjuntoVacio()) {
			int x = v.elegir();
			v.sacar(x);
			vertices[inx] = x;
			cadena = cadena + x + "   ";
			inx++;
		}
		System.out.println(cadena);
		for (int i = 0; i < cantidad; i++) {
			cadena = "";
			cadena = cadena + vertices[i] + "   ";
			for (int j = 0; j < cantidad; j++) 
				if(g.existeArista(vertices[i], vertices[j]))
					cadena = cadena + g.pesoArista(vertices[i], vertices[j]) + "   ";
				else
					cadena = cadena + "0   ";
			System.out.println(cadena);
		}
	}
	
	public static void main(String[] args) {
		
		GrafoTDA grafoPrueba = new GrafoMA();
		grafoPrueba.inicializarGrafo();
		
		//añadimos vertices y aristas 
		grafoPrueba.agregarVertice(1);
		grafoPrueba.agregarVertice(2);
		grafoPrueba.agregarVertice(3);
		grafoPrueba.agregarVertice(4);
		grafoPrueba.agregarVertice(5);
		grafoPrueba.agregarVertice(6);
		grafoPrueba.agregarArista(1, 2, 2);
		grafoPrueba.agregarArista(1, 3, 1);
		grafoPrueba.agregarArista(1, 4, 7);
		grafoPrueba.agregarArista(1, 5, 5);
		grafoPrueba.agregarArista(2, 6, 2);
		grafoPrueba.agregarArista(3, 2, 2);
		grafoPrueba.agregarArista(3, 5, 2);
		grafoPrueba.agregarArista(4, 1, 3);
		grafoPrueba.agregarArista(5, 4, 2);
		grafoPrueba.agregarArista(5, 6, 2);
		grafoPrueba.agregarArista(6, 3, 3);
		
		//mostramos el grafo original completo
		System.out.println("Grafo completo:");
		mostrarGrafo(grafoPrueba);

		System.out.println("");
		//msotrar el grafo de costo minimo (no camino real)
		System.out.println("Grafo de costo minimo:");
		mostrarGrafo(DijkstraCostosMinimos.dijkstraCostosMinimos(grafoPrueba, 1));

		//mostrar el grafo de caminos de costos minimos (camino real)
		System.out.println("");
		System.out.println("Grafo de caminos costos minimos:");
		mostrarGrafo(DijkstraCaminoCostosMinimos.dijkstraCaminosReales(grafoPrueba, 1));
	}

}