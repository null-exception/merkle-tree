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
 */package org.hashtrees.util;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NestedIterator<T> implements Iterator<T> {

	private final Queue<Iterator<T>> itrQueue;

	public NestedIterator(List<Iterator<T>> iterators) {
		itrQueue = new ArrayDeque<>(iterators);
	}

	@Override
	public boolean hasNext() {
		while (!itrQueue.isEmpty() && !itrQueue.peek().hasNext())
			itrQueue.remove();
		return !itrQueue.isEmpty() && itrQueue.peek().hasNext();
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException("No more elements exist.");
		return itrQueue.peek().next();
	}
}
