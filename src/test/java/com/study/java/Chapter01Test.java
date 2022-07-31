package com.study.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.enums.Color;
import com.study.java.interfaces.ApplePredicate;
import com.study.java.model.Apple;
import com.study.java.model.AppleGreenColorPredicate;
import com.study.java.model.AppleHeavyWeightPredicate;

@ExtendWith(MockitoExtension.class)
public class Chapter01Test {

	List<Apple> apples = new ArrayList<Apple>();

	@BeforeEach
	void setUp() {
		apples.add(Apple.builder().color(Color.RED).weight(40).build());
		apples.add(Apple.builder().color(Color.RED).weight(80).build());
		apples.add(Apple.builder().color(Color.GREEN).weight(120).build());
		apples.add(Apple.builder().color(Color.GREEN).weight(160).build());
		apples.add(Apple.builder().color(Color.RED).weight(200).build());
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

	@Test
	void filterApples() {
		List<Apple> greenApples = Apple.filterApples(apples, Color.GREEN, 0, true);
		List<Apple> heavyApples = Apple.filterApples(apples, null, 150, false);

		System.out.println(greenApples);
		System.out.println(heavyApples);

		assertThat(greenApples).hasSize(2);
		assertThat(heavyApples).hasSize(2);
	}

	@Test
	void filterApplesPredicate() {
		List<Apple> greenApples = Apple.filterApples(apples, new AppleGreenColorPredicate());
		List<Apple> heavyApples = Apple.filterApples(apples, new AppleHeavyWeightPredicate());

		System.out.println(greenApples);
		System.out.println(heavyApples);

		assertThat(greenApples).hasSize(2);
		assertThat(heavyApples).hasSize(2);
	}

	@Test
	void filterApplesAnonymousClass() {
		List<Apple> redApples = Apple.filterApples(apples, new ApplePredicate() {
			@Override
			public boolean test(final Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});

		List<Apple> heavyApples = Apple.filterApples(apples, new ApplePredicate() {
			@Override
			public boolean test(final Apple apple) {
				return apple.getWeight() > 150;
			}
		});

		System.out.println(redApples);
		System.out.println(heavyApples);

		assertThat(redApples).hasSize(3);
		assertThat(heavyApples).hasSize(2);
	}

	@Test
	void filterApplesLambda() {
		List<Apple> redApples = Apple.filterApples(apples, apple -> apple.getColor().equals(Color.RED));
		List<Apple> heavyApples = Apple.filterApples(apples, apple -> apple.getWeight() > 150);

		System.out.println(redApples);
		System.out.println(heavyApples);

		assertThat(redApples).hasSize(3);
		assertThat(heavyApples).hasSize(2);
	}
}
