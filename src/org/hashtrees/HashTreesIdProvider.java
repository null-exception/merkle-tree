package org.hashtrees;

import java.nio.ByteBuffer;
import java.util.Iterator;

/**
 * There can be multiple hash trees. Given a key we need to know which hash tree
 * that the key belongs to. This interface defines that signature
 * {@link #getTreeId(ByteBuffer)}.
 * 
 * Also some nodes may act as replica nodes for some of the hash trees. In that
 * case replica nodes should not synch primary nodes for those hash trees. To
 * avoid this problem, {@link HashTrees} should know which hash trees are
 * managed by the local node. {@link #getAllPrimaryTreeIds()} serves this
 * purpose.
 * 
 * This interface defines methods which will be used by {@link HashTreesImpl}
 * class. The implementation has to be thread safe.
 * 
 */
public interface HashTreesIdProvider {

	/**
	 * Returned treeId should be >= 0.
	 * 
	 * @param key
	 * @return
	 */
	long getTreeId(ByteBuffer key);

	/**
	 * Returns treeIds for which the current node is responsible for.
	 * 
	 * @return
	 */
	Iterator<Long> getAllPrimaryTreeIds();
}
