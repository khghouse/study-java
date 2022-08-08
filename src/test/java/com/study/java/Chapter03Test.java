package com.study.java;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.enums.Color;
import com.study.java.interfaces.BufferedReaderProcessor;
import com.study.java.interfaces.Predicate;
import com.study.java.model.Apple;

@ExtendWith(MockitoExtension.class)
public class Chapter03Test {

	List<Apple> apples = new ArrayList<Apple>();

	@BeforeEach
	void setUp() {
		apples.add(Apple.builder().color(Color.RED).weight(40).build());
		apples.add(Apple.builder().color(Color.GREEN).weight(160).build());
		apples.add(Apple.builder().color(Color.RED).weight(80).build());
		apples.add(Apple.builder().color(Color.GREEN).weight(120).build());
		apples.add(Apple.builder().color(Color.RED).weight(200).build());
	}

	@Test
	void processFileTest() throws Exception {
		String result = processFile();
		assertThat(result).isEqualTo("안녕");
	}

	@Test
	void processFileOneLines() throws Exception {
		String result = this.processFile((BufferedReader br) -> br.readLine());
		assertThat(result).isEqualTo("안녕");
	}

	@Test
	void processFileTwoLines() throws Exception {
		String result = this.processFile((BufferedReader br) -> br.readLine() + br.readLine());
		assertThat(result).isEqualTo("안녕하세요");
	}

	@Test
	void predicate() {
		List<String> list = Arrays.asList("", "", "안녕", "", "", "자바8");

		Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
		List<String> nonEmpty = Apple.filter(list, nonEmptyStringPredicate);
		System.out.println(nonEmpty);
	}

	@Test
	void consumer() {
		forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
	}

	@Test
	void function() {
		List<Integer> list = map(Arrays.asList("almbdas", "in", "action"), s -> s.length());
		System.out.println(list);
	}

	@Test
	void methodReference() {
		// apples.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		apples.sort(comparing(Apple::getWeight));

		System.out.println(apples);
	}

	@Test
	void constructorReference() {
		Supplier<Apple> c1 = Apple::new;
		Apple a1 = c1.get();
		assertThat(a1).isNotNull();

		Function<Integer, Apple> c2 = Apple::new;
		Apple a2 = c2.apply(110);
		assertThat(a2.getWeight()).isEqualTo(110);

		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		List<Apple> apples = this.map(weights, Apple::new);
		assertThat(apples.size()).isEqualTo(4);
		assertThat(apples.get(2).getWeight()).isEqualByComparingTo(4);

		BiFunction<Color, Integer, Apple> c3 = Apple::new;
		Apple a3 = c3.apply(Color.GREEN, 110);
		assertThat(a3.getColor()).isEqualTo(Color.GREEN);
		assertThat(a3.getWeight()).isEqualTo(110);
	}

	public String processFile() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("C:/data.txt"))) {
			return br.readLine();
		}
	}

	public String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("C:/data.txt"))) {
			return p.process(br);
		}
	}

	public <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

	public <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<R>();
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}
}
