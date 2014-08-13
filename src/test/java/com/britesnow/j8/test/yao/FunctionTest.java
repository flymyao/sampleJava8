package com.britesnow.j8.test.yao;

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

}
