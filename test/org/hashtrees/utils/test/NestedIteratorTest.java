/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */package org.hashtrees.utils.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import junit.framework.Assert;

import org.hashtrees.util.NestedIterator;
import org.junit.Test;

public class NestedIteratorTest {

	@Test
	public void testNestedIterators() {
		List<Integer> expected = generateRandomNumbers(10);
		List<Integer> secondExpList = generateRandomNumbers(10);
		List<Iterator<Integer>> itrs = new ArrayList<>();
		itrs.add(expected.iterator());
		itrs.add(secondExpList.iterator());

		NestedIterator<Integer> nestedItr = new NestedIterator<>(itrs);
		List<Integer> actual = new ArrayList<>();
		while (nestedItr.hasNext()) {
			actual.add(nestedItr.next());
		}
		Assert.assertFalse(actual.isEmpty());
		expected.addAll(secondExpList);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testNestedIteratorsWithEmptyList() {
		List<Integer> expected = new ArrayList<>();
		List<Integer> secondExpList = generateRandomNumbers(10);
		List<Iterator<Integer>> list = new ArrayList<>();
		list.add(expected.iterator());
		list.add(secondExpList.iterator());

		NestedIterator<Integer> nestedItr = new NestedIterator<>(list);
		List<Integer> actual = new ArrayList<>();
		while (nestedItr.hasNext()) {
			actual.add(nestedItr.next());
		}
		Assert.assertFalse(actual.isEmpty());
		expected.addAll(secondExpList);
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = NoSuchElementException.class)
	public void testNestedIteratorsWithException() {
		List<Integer> expected = new ArrayList<>();
		List<Iterator<Integer>> itrs = new ArrayList<>();
		itrs.add(expected.iterator());

		NestedIterator<Integer> nestedItr = new NestedIterator<>(itrs);
		nestedItr.next();
	}

	private static List<Integer> generateRandomNumbers(int count) {
		Random random = new Random();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= count; i++) {
			result.add(random.nextInt());
		}
		return result;
	}
}
