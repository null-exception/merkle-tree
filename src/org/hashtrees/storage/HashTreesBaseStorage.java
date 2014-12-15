package org.hashtrees.storage;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.hashtrees.util.AtomicBitSet;

public abstract class HashTreesBaseStorage implements HashTreesStorage {

	private final ConcurrentMap<Long, AtomicBitSet> treeIdAndDirtySegmentMap = new ConcurrentHashMap<Long, AtomicBitSet>();
	private final int noOfSegDataBlocks;

	public HashTreesBaseStorage(int noOfSegDataBlocks) {
		this.noOfSegDataBlocks = noOfSegDataBlocks;
	}

	private AtomicBitSet getDirtySegmentsHolder(long treeId) {
		if (!treeIdAndDirtySegmentMap.containsKey(treeId))
			treeIdAndDirtySegmentMap.putIfAbsent(treeId, new AtomicBitSet(
					noOfSegDataBlocks));
		return treeIdAndDirtySegmentMap.get(treeId);
	}

	@Override
	public void setDirtySegment(long treeId, int segId) {
		getDirtySegmentsHolder(treeId).set(segId);
	}

	@Override
	public List<Integer> clearAndGetDirtySegments(long treeId) {
		return getDirtySegmentsHolder(treeId).clearAndGetAllSetBits();
	}

	@Override
	public void clearAllSegments(long treeId) {
		getDirtySegmentsHolder(treeId).clear();
	}
}