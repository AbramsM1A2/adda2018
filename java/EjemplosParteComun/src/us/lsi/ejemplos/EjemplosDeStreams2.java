package us.lsi.ejemplos;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Collectors2;
import us.lsi.common.Multiset;
import us.lsi.common.SeqAccumulators;
import us.lsi.common.Streams2;
import us.lsi.common.Strings2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple2;
import us.lsi.math.Math2;


public class EjemplosDeStreams2 {
	
	public static List<Tuple2<Long,Long>> primosPar(Long m, Long n, Integer k){
		var r = Stream.iterate(Math2.siguientePrimo(m),x->x < n,x->Math2.siguientePrimo(x));
		var r2 = Streams2.consecutivePairs(r);
		var rr2 = r2.filter(t->t.v2-t.v1==k).collect(Collectors.toList());
		return rr2;
	}
	
	public static Stream<Long> divisores(Long n){
		return Stream.iterate(2L, x-> x <= (long) Math.sqrt(n), x -> x+1).filter(x->n%x==0);
	}
	
	public static Stream<Tuple2<Long,Long>> primos(Long a){
		var r = Stream.iterate(Math2.siguientePrimo(a),x->Math2.siguientePrimo(x));
		var r2 = Streams2.consecutivePairs(r);
		
		return r2;
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		var s1 = List.of(1,2,3,4,5,6,7);
		var s2 = List.of(11,12,13,14,15,16);
		var r2 = List.of(11,12,13,14,15,16,34,57);
		Stream.generate(()->Math2.getEnteroAleatorio(0, 100))
			.limit(4)
			.forEach(System.out::println);
		System.out.println("______");
		var rr = Stream.generate(()->Math2.getEnteroAleatorio(0, 100)).filter(x->x%7==0);
		System.out.println(Streams2.accumulateLeft(rr, SeqAccumulators.first()).get());
		System.out.println("______");
		var s3 = r2.stream().map(x->x.toString());
		var a = SeqAccumulators.joiningAccumulator(" ","{","}");
		var r = Streams2.accumulateLeft(s3, a);
		System.out.println(r);	
		System.out.println("______");
		var s4 = Stream.iterate(0, x->x+1);
		var s5 = Streams2.zip(s2.stream(),s4,(x,y)->Tuple.create(x, y));
		s5.forEach(System.out::println);
		System.out.println("______");
		var n = 14L;
		var b = 7L;
		var s6 = Stream.iterate(n,x->x>0,x->x/2);
		var a6 = SeqAccumulators
				.reduce(1L, (x,y)->y%2==0?x*x:x*x*b);
		var r1 = Streams2.accumulateRight(s6, a6);
		var s7 = Stream.iterate(Tuple.create(n,b),
								t->t.v1>0,
								t->Tuple.create(t.v1/2,t.v2*t.v2))
						.filter(t->t.v1%2!=0)
						.map(t->t.v2)
						.reduce(1L,(x,y)->x*y);
					   
		System.out.println(s7+","+r1+","+Math.pow(b,n));
		System.out.println("______");
		var ss = Streams2.elementsAndPosition(r2.stream());
		var r3 = ss.map(t->t.toString()).collect(Collectors.joining(","));
		System.out.println(r3);
		System.out.println("______");
		var enteros = Stream.iterate(0,x->x+1);
		var ss2 = Streams2.limit(enteros, 4);
		var r4 = ss2.map(t->t.toString()).collect(Collectors.joining(","));
		System.out.println(r4);
		System.out.println("______");
		var pp = primosPar(1000L,100000L,2);
		Strings2.toConsole(pp,"Pares de primos");
		System.out.println("______");
		Long m1 = 1000L;
		Long n1 = 2000L;
		Multiset<Long> rr4 = 
				Stream.iterate(m1,x->x<n1,x->x+1)
				.flatMap(x->divisores(x))
				.collect(Collectors2.toMultiset());
		System.out.println(rr4);
		System.out.println("______");
	}

}
