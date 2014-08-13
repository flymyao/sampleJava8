package com.britesnow.j8.test.yao;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Test;

public class FunctionTest {
	
	@Test
	public void testMyFunction(){
		MyFunctional<Integer> adds = (x,y) -> x + y;
		MyFunctional<Integer> minus = (x,y) -> x - y;
		System.out.println("TestMyFunction (adds) => 1 + 2 = " + adds.apply(1,2));
		System.out.println("TestMyFunction (minus) => 1 - 2 = " + minus.apply(1,2));
		System.out.println("TestMyFunction (adds,minus) => (8 - 2) + 3 = " + adds.compose(minus,3).apply(8,2));
		System.out.println("TestMyFunction (adds,minus) => (8 + 2) - 3 = " + minus.compose(adds,3).apply(8,2));
		System.out.println("TestMyFunction (adds,minus) => (3 + 2) - 1 = " + adds.andThen(minus,1).apply(3,2));
		System.out.println("TestMyFunction (adds,minus) => (3 - 1) + 3 = " + minus.andThen(adds,3).apply(3,1));
	}

	@Test
    public void testConsumer(){
	    Consumer<Integer> consumer = System.out::println;
	    consumer.accept(1);
	    System.out.println("==========");
	    Consumer<Integer> consumer1 = t -> {
	        System.out.println("Do something!");
	    };
	    consumer.andThen(consumer1).accept(2);
    }
	
	@Test
	public void testPredicate(){
	    Predicate<Integer> predicate = t -> { return t >= 0 ? true : false;};
	    System.out.println("judge if t>=0: "+predicate.test(0));
	    Predicate<Integer> predicate1 = t -> { return t % 2 == 0 ? true : false;};
	    System.out.println("return the negate result:: "+predicate.negate().test(3));
	    System.out.println("judge if predicate && predicate1 is true: "+predicate.and(predicate1).test(3));
	    System.out.println("judge if predicate || predicate1 is true: "+predicate.or(predicate1).test(1));
	    System.out.println("judge if t>=0 and 2.equal(2): "+Predicate.isEqual(2).test(2));
	    System.out.println("judge if t>=0 and 2.equal(1): "+Predicate.isEqual(2).test(1));
	}
}
