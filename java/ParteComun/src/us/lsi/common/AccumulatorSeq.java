package us.lsi.common;

/**
 * @author Miguel Toro
 *
 * @param <E> El tipo de lo elementos del Stream a acumular
 * @param <B> El tipo de la la base del acumulador
 * @param <R> El tipo del resultado del acumulador
 */
public interface AccumulatorSeq<E, B, R> {
	/**
	 * Establece el esatdo inicial de la base del acumulador
	 */
	B getInitial();
	/**
	 * @post El elemento que queda acumulado en la base
	 * @param <E> el tipo del elemento
	 * @param e Un elemento
	 */
	void add(E e);
	/**
	 * @param <R> El tipo del resultado
	 * @return Transforma el valor de la base. Es el valor que devolver� el acumulador
	 */
	R result();
	/**
	 * @return Si el acumulador ha terminado el c�lculo
	 */
	Boolean isDone();
}
