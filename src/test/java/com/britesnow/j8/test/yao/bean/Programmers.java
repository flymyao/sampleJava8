package com.britesnow.j8.test.yao.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Programmers {
	Programmer[] value();
}
