package com.study.java.model;

import java.util.ArrayList;
import java.util.List;

import com.study.java.enums.Color;
import com.study.java.interfaces.ApplePredicate;
import com.study.java.interfaces.Predicate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

	private Color color;
	private Integer weight;

	public static List<Apple> filterGreenApples(final List<Apple> inventory) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(final List<Apple> inventory, final Color color) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApples(final List<Apple> inventory, final Color color, final int weight,
			final boolean flag) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApples(final List<Apple> inventory, final ApplePredicate p) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<T>();
		for (T t : list) {
			if (p.test(t)) {
				result.add(t);
			}
		}
		return result;
	}
}
