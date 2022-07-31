package com.study.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.enums.Color;
import com.study.java.model.Apple;

@ExtendWith(MockitoExtension.class)
public class Chapter01Test {

	List<Apple> apples = new ArrayList<Apple>();

	@BeforeEach
	void setUp() {
		apples.add(Apple.builder().color(Color.RED).build());
		apples.add(Apple.builder().color(Color.RED).build());
		apples.add(Apple.builder().color(Color.GREEN).build());
		apples.add(Apple.builder().color(Color.GREEN).build());
		apples.add(Apple.builder().color(Color.RED).build());
	}

	@Test
	void filterGreenApples() {
		List<Apple> greenApples = Apple.filterGreenApples(apples);
		System.out.println(greenApples);
		assertThat(greenApples).hasSize(2);
	}

	@Test
	void filterApplesByColor() {
		List<Apple> greenApples = Apple.filterApplesByColor(apples, Color.GREEN);
		List<Apple> redApples = Apple.filterApplesByColor(apples, Color.RED);

		System.out.println(greenApples);
		System.out.println(redApples);

		assertThat(greenApples).hasSize(2);
		assertThat(redApples).hasSize(3);
	}
}
