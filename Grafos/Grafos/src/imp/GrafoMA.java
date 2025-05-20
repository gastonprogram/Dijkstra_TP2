package imp;

import api.ConjuntoTDA;
import api.GrafoTDA;

public class GrafoMA implements GrafoTDA {

	public int[][] mAdy;
	public int[] etiquetas;
	public int cantidad;
	int mx = 55;
	
	@Override
	public void inicializarGrafo() {
		mAdy = new int[mx][mx];
		etiquetas = new int[mx];
		cantidad = 0;
	}

	@Override
	public void agregarVertice(int v) {
		etiquetas[cantidad] = v;
		for (int i = 0; i <= cantidad; i++) {
			mAdy[i][cantidad] = 0;
			mAdy[cantidad][i] = 0;
		}
		cantidad++;
	}

	@Override
	public void eliminarVertice(int v) {
		int inx = vertice2indice(v);
		etiquetas[inx] = etiquetas[cantidad - 1];
		for(int i = 0; i < cantidad; i++)
			mAdy[inx][i] = mAdy[cantidad - 1][i];
		for(int i = 0; i < cantidad; i++)
			mAdy[i][inx] = mAdy[i][cantidad - 1];
		cantidad--;
	}

	@Override
	public ConjuntoTDA vertices() {
		ConjuntoTDA v = new ConjuntoLD();
		v.inicializarConjunto();
		for (int i = 0; i < cantidad; i++)
			v.agregar(etiquetas[i]);
		return v;
	}

	@Override
	public void agregarArista(int origen, int destino, int peso) {
		int o = vertice2indice(origen);
		int d = vertice2indice(destino);
		mAdy[o][d] = peso;
	}

	@Override
	public void eliminarArista(int origen, int destino) {
		int o = vertice2indice(origen);
		int d = vertice2indice(destino);
		mAdy[o][d] = 0;
	}

	@Override
	public boolean existeArista(int origen, int destino) {
		int o = vertice2indice(origen);
		int d = vertice2indice(destino);
		return (mAdy[o][d] != 0);
	}

	@Override
	public int pesoArista(int origen, int destino) {
		int o = vertice2indice(origen);
		int d = vertice2indice(destino);
		return mAdy[o][d];
	}

	public int vertice2indice(int v) {
		int i = cantidad - 1;
		while (i >= 0 && etiquetas[i] != v)
			i--;
		return i;
	}
}
