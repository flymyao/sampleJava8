package com.britesnow.j8.test.yao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GenericInference {

	@Test
	@SuppressWarnings("unused")
	public void testGenericInference () {
		//1.5
		Map<String, String> oneMap = new HashMap<String, String>();
		//1.7
		Map<String, String> twoMap = new HashMap<>();
		
		List<Integer> leftList = new ArrayList<>();
		leftList.add(2);
		//1.8
		List rightList = new ArrayList<>();
		leftList.addAll(new ArrayList<>());
		
		rightList.add("AAA");
		leftList.addAll(rightList);
		
		//here will throw a Class Cast Exception
		Integer a = leftList.get(1);
	}
}
