package com.study.java.model;

import com.study.java.interfaces.ApplePredicate;

public class AppleHeavyWeightPredicate implements ApplePredicate {

	@Override
	public boolean test(final Apple apple) {
		return apple.getWeight() > 150;
	}

}
