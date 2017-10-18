package com.jenetics.mathexp.util;

import java.util.Iterator;

import org.apache.commons.math3.complex.Complex;

public class ChainedList<T>{
	
	private T value;
	private ChainedList<T> next;
	private ChainedList<T> initial;
	
	public ChainedList(T value) {
		this(value, true);
		
	}
	
	private ChainedList(T value, boolean associateInitial) {
		super();
		this.value = value;
		if (associateInitial) {
			initial = this;
		}
	}
	
	public ChainedList<T> getNext() {
		return next;
	}

	public T getValue() {
		return value;
	}

	public ChainedList<T> getInitial() {
		return initial;
	}

	public ChainedList<T> addFollowing(T following) {
		next = new ChainedList<T>(following, false);
		next.initial = initial;
		return this;
	}
}
