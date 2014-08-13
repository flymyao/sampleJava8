package com.britesnow.j8.test.yao;

import java.util.Objects;

public interface MyFunctional <T>{

	T apply(T t1,T t2);
	
	default  MyFunctional<T> compose(MyFunctional<T> before, T t) {
        Objects.requireNonNull(before);
        return (T t1,T t2) -> apply(before.apply(t1, t2), t);
    }
	
	default <V> MyFunctional<T> andThen(MyFunctional<T> after,T t) {
        Objects.requireNonNull(after);
        return (T t1, T t2) -> after.apply(apply(t1,t2), t);
    }
}
