package com.britesnow.j8.test.yao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CustomCollectorTest {

	private List<Person> persons;
	
	public CustomCollectorTest () {
		Object[][] personArray = {
                {"yao", "boss", true, 18, 300.01, Person.COLOR.GREEN, new String[]{"one","two","three"}}, 
                {"flym", "boss", false, 23, 200.01, Person.COLOR.RED, new String[]{"1","2","3"}},
                {"hisoka", "HR", true, 30, 100.01, Person.COLOR.YELLOW, new String[]{"I","II","III"}}
            };
		persons = Stream.of(personArray)
				.map(data -> new Person(data[0],data[1],data[2],data[3],data[4],data[5],data[6]))
				.collect(Collectors.toList());
	}

	@Test
	public void  test(){
		List personList = persons.stream().collect(CustomCollector.toList());
		System.out.println("PersonList: " + personList);
		
		Set personSet = persons.stream().collect(CustomCollector.toSet());
		System.out.println("personSet: " + personSet);
		
		List personPropertyList = persons.stream().collect(CustomCollector.propertyToList(Person::getTitle));
		System.out.println("personPropertyList: " + personPropertyList);
		
		List personPropertySet = persons.stream().collect(CustomCollector.propertyToList(Person::getTitle));
		System.out.println("personPropertySet: " + personPropertySet);
	}
	
	/**
	 * 
	 * @param <T> the type of input elements to the reduction operation
	 * @param <A> the mutable accumulation type of the reduction operation (often
	 *            hidden as an implementation detail)
	 * @param <R> the result type of the reduction operation
	 * @author Administrator
	 *
	 * @param <T>
	 * @param <A>
	 * @param <R>
	 */
	static class CustomCollector<T, A, R> implements Collector<T, A, R> {

		private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        private static <I, R> Function<I, R> castingIdentity() {
            return i -> (R) i;
        }

		public CustomCollector(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner) {
			super();
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
			this.finisher = castingIdentity();
			this.characteristics = Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
		}

		@Override
		public Supplier supplier() {
			return supplier;
		}

		@Override
		public BiConsumer accumulator() {
			return accumulator;
		}

		@Override
		public BinaryOperator<A> combiner() {
			return combiner;
		}

		@Override
		public Function finisher() {
			return finisher;
		}

		@Override
		public Set characteristics() {
			return characteristics;
		}

		public static <T> Collector<T, ?, List<T>> toList ( ) {
	        return new CustomCollector<>((Supplier<List<T>>) ArrayList::new, List::add,
	                                   (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, Set<R>> toSet ( ) {
			return new CustomCollector<>((Supplier<Set<T>>) HashSet::new, Set::add,
                    (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, List<R>> propertyToList(Function<T,R> property) {
			BiConsumer<List<R>,T > accumulator = (list,element) ->  list.add(property.apply(element));
			
			return new CustomCollector<>((Supplier<List<R>>) ArrayList::new, accumulator,
                       (left, right) -> { left.addAll(right); return left; });
	    }

		public static <T,R> Collector<T, ?, Set<R>> propertyToSet(Function<T,R> property) {
			BiConsumer<Set<R>,T > accumulator = (set,element) ->  set.add(property.apply(element));
			
			return new CustomCollector<>((Supplier<Set<R>>) HashSet::new, accumulator,
                       (left, right) -> { left.addAll(right); return left; });
	    }
	}
}
