package com.study.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.java.enums.Color;
import com.study.java.model.Apple;

@ExtendWith(MockitoExtension.class)
public class Chapter01Test {

	@Test
	void filterGreenApples() {
		List<Apple> apples = new ArrayList<Apple>();

		apples.add(Apple.builder().color(Color.RED).build());
		apples.add(Apple.builder().color(Color.RED).build());
		apples.add(Apple.builder().color(Color.GREEN).build());
		apples.add(Apple.builder().color(Color.GREEN).build());
		apples.add(Apple.builder().color(Color.RED).build());
		System.out.println(apples.size());

		System.out.println(Apple.filterGreenApples(apples));
	}
}
