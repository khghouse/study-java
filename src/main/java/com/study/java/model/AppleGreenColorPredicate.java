package com.study.java.model;

import com.study.java.enums.Color;
import com.study.java.interfaces.ApplePredicate;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override
	public boolean test(final Apple apple) {
		return Color.GREEN.equals(apple.getColor());
	}

}
