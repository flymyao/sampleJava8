package com.britesnow.j8.test.yao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CollectorsTest {

	@Test
	public void collectorTest(){
		List<Person> persons = personList();
		attributeList(persons);
		attributeSet(persons);
		objectStrings(persons);
		summingOperator(persons);
		averagingOperator(persons);
		partitioningOperator(persons);
		attributeList(persons);
		attributeList(persons);
		groupingByOperator(persons);
		ObjectToMap(persons);
	}
	
	private void attributeList(List<Person> persons){
		List<String> list = persons.stream()
                               .map(Person::getName)
                               .collect(Collectors.toList());
       System.out.println(list);
	}
	
	private void attributeSet(List<Person> persons){
		 Set<String> set = persons.stream()
                               .map(Person::getName)
                               .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);
	}
	
	private void objectStrings(List<Person> persons){
		String joined = persons.stream()
                               .map(Object::toString)
                                .collect(Collectors.joining(","));
		System.out.println(joined);
	}
	
	private void summingOperator(List<Person> persons){
		Double total = persons.stream().collect(Collectors.summingDouble(Person::getSalary));
		System.out.println(total);
	}
	
	private void averagingOperator(List<Person> persons){
		double averagingSalary= persons.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println(averagingSalary);
	}
	
	private void partitioningOperator(List<Person> persons){
		Map<Boolean,List<Person>> passingFailing = persons.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 20));
        System.out.println(passingFailing);
	}
	
	private void groupingByOperator(List<Person> persons){
		 Map<String, Double> salaryByName =persons.stream().
                                collect(Collectors.groupingBy(Person::getName, Collectors.summingDouble(Person::getSalary)));
		 System.out.println(salaryByName);
	}
	
	private void ObjectToMap(List<Person> persons){
		Map salaryByName = persons.stream().collect(()->new HashMap<>(), (hashMap,item) -> {hashMap.put(item.getName(), item.getSalary());}, (hashMapOld, hashMapNew) -> hashMapOld.putAll(hashMapNew));
		System.out.println(salaryByName);
	}
	
	private List<Person> personList(){
		Object[][] personArray = {
                {"yao", "boss", true, 18, 10000.01, Person.COLOR.GREEN, new String[]{"one","two","three"}}, 
                {"flym", "manager", false, 23, 1000.01, Person.COLOR.RED, new String[]{"1","2","3"}},
                {"hisoka", "HR", true, 30, 100.01, Person.COLOR.YELLOW, new String[]{"I","II","III"}}
            };
		List<Person> persons = Stream.of(personArray)
				.map(data -> new Person(data[0],data[1],data[2],data[3],data[4],data[5],data[6]))
				.collect(Collectors.toList());
		return persons;
	}
}
