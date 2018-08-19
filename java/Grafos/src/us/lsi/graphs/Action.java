package us.lsi.graphs;

public interface Action<V> {
	/**
	 * @pre isApplicable(a)
	 * @param v Un v�rtice
	 * @return El vecino tras tomar esa acci�n
	 */
	public abstract V neighbor(V v);
	/**
	 * @param v Un v�rtice
	 * @return Si la acci�n es aplicable en este v�rtice
	 */
	public abstract boolean isApplicable(V v);
}
