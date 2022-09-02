package trees;


/**
 * Un gráfo es un conjunto de vértices v={v1, v2, ..., vn} y de aristas E en donde cada arista es una pareja de vértices.
 * Un árbol es un gráfo conexo y acíclico en donde un  y solo un vértice es designado como raíz.
 * Un camino es una secuencia de vertices en V p=(v1, v2, vi, ..., vj) don dónde (vi, vi+1) pertenece a E.
 * Es conexo si para cualquier pareja de vertices vi,vj que existe en V existe un camino p(vi, vj).
 * Es acíclico si para toda vi no existe p(vi, vi) sin repetir vértices.
 * Un nodo sucesor Vi es sucesor de Vj si existe camino p={raíz, ..., hoja} en donde vj es visitado antes que vi.
 * Un nodo descendiente es un descendiente si existe camino p={raíz, ..., hoja} pero no tiene que estar (Vj,Vi) pertenece a E.
 * Un nodo hoja es aquel que no tiene sucesores.
 * La altura del arbol es la longitud del camino más largo de la raíz a una hoja. 
 */

public interface TreeADT<T> {

	public int size();
	public boolean find(T element);
	public boolean isEmpty();

}
