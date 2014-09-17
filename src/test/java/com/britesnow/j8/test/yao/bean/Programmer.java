package com.britesnow.j8.test.yao.bean;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(Programmers.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Programmer {
	String name();
}
