package org.hashtrees.test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hashtrees.store.HashTreesMemStore;
import org.hashtrees.store.HashTreesPersistentStore;
import org.hashtrees.store.HashTreesStore;
import org.hashtrees.test.utils.HashTreesImplTestUtils;
import org.hashtrees.thrift.generated.SegmentData;
import org.hashtrees.thrift.generated.SegmentHash;
import org.hashtrees.util.ByteUtils;
import org.junit.Assert;
import org.junit.Test;

public class HashTreesStoreTest {

	private static final int DEF_TREE_ID = 1;
	private static final int DEF_SEG_ID = 0;

	private static interface HTStoreHelper {

		HashTreesStore getInstance() throws Exception;

		HashTreesStore restartInstance(HashTreesStore htStore) throws Exception;

		void cleanup(HashTreesStore htStore);
	}

	private static class HTPersistentStoreHelper implements HTStoreHelper {

		@Override
		public HashTreesStore getInstance() throws Exception {
			return new HashTreesPersistentStore(
					HashTreesImplTestUtils.randomDirName());
		}

		@Override
		public void cleanup(HashTreesStore htStore) {
			((HashTreesPersistentStore) htStore).delete();
		}

		@Override
		public HashTreesStore restartInstance(HashTreesStore htStore)
				throws Exception {
			((HashTreesPersistentStore) htStore).stop();
			return new HashTreesPersistentStore(
					((HashTreesPersistentStore) htStore).getDbDir());
		}
	}

	private static class HTMemStoreHelper implements HTStoreHelper {

		@Override
		public HashTreesStore getInstance() throws Exception {
			return new HashTreesMemStore();
		}

		@Override
		public void cleanup(HashTreesStore htStore) {
			// nothing to do.
		}

		@Override
		public HashTreesStore restartInstance(HashTreesStore htStore)
				throws Exception {
			return htStore;
		}

	}

	private static final Set<HTStoreHelper> helpers = new HashSet<>();

	static {
		helpers.add(new HTPersistentStoreHelper());
		helpers.add(new HTMemStoreHelper());
	}

	@Test
	public void testSegmentData() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				ByteBuffer key = ByteBuffer.wrap("key1".getBytes());
				ByteBuffer digest = ByteBuffer.wrap(ByteUtils.sha1("digest1"
						.getBytes()));

				htStore.putSegmentData(DEF_TREE_ID, DEF_SEG_ID, key, digest);

				SegmentData sd = htStore.getSegmentData(DEF_TREE_ID,
						DEF_SEG_ID, key);
				Assert.assertNotNull(sd);
				Assert.assertEquals(digest, sd.digest);

				htStore.deleteSegmentData(DEF_TREE_ID, DEF_SEG_ID, key);
				sd = htStore.getSegmentData(DEF_TREE_ID, DEF_SEG_ID, key);
				Assert.assertNull(sd);
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testSegment() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				List<SegmentData> list = new ArrayList<SegmentData>();
				SegmentData sd;
				for (int i = 0; i < 10; i++) {
					sd = new SegmentData(ByteBuffer.wrap(("test" + i)
							.getBytes()), ByteBuffer.wrap(("value" + i)
							.getBytes()));
					list.add(sd);
					htStore.putSegmentData(DEF_TREE_ID, DEF_SEG_ID, sd.key,
							sd.digest);
				}

				List<SegmentData> actualResult = htStore.getSegment(
						DEF_TREE_ID, DEF_SEG_ID);
				Assert.assertNotNull(actualResult);
				Assert.assertTrue(actualResult.size() != 0);
				Assert.assertEquals(list, actualResult);
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testPutSegmentHash() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				ByteBuffer digest = ByteBuffer.wrap("digest1".getBytes());
				htStore.putSegmentHash(DEF_TREE_ID, DEF_SEG_ID, digest);

				SegmentHash sh = htStore
						.getSegmentHash(DEF_TREE_ID, DEF_SEG_ID);
				Assert.assertNotNull(sh);
				Assert.assertEquals(digest, sh.hash);

				List<SegmentHash> expected = new ArrayList<SegmentHash>();
				expected.add(sh);

				List<Integer> nodeIds = new ArrayList<Integer>();
				nodeIds.add(DEF_SEG_ID);

				List<SegmentHash> actual = htStore.getSegmentHashes(
						DEF_TREE_ID, nodeIds);
				Assert.assertNotNull(actual);
				Assert.assertEquals(expected, actual);
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testDeleteTree() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				ByteBuffer key = ByteBuffer.wrap("key1".getBytes());
				ByteBuffer digest = ByteBuffer.wrap("digest1".getBytes());

				htStore.putSegmentData(DEF_TREE_ID, DEF_SEG_ID, key, digest);
				htStore.deleteTree(DEF_TREE_ID);

				SegmentData sd = htStore.getSegmentData(DEF_TREE_ID,
						DEF_SEG_ID, key);
				Assert.assertNull(sd);
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testSetLastFullyTreeBuiltTimestamp() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				long exTs = System.currentTimeMillis();
				htStore.setCompleteRebuiltTimestamp(DEF_TREE_ID, exTs);
				long dbTs = htStore.getCompleteRebuiltTimestamp(DEF_TREE_ID);
				Assert.assertEquals(exTs, dbTs);
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testSegmentRebuildMarkers() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();
			try {
				List<Integer> expectedSegs = new ArrayList<>();
				for (int i = 0; i < 10; i++)
					expectedSegs.add(i);
				htStore.markSegments(DEF_TREE_ID, expectedSegs);
				List<Integer> actualMarkedSegs = htStore
						.getMarkedSegments(DEF_TREE_ID);
				Assert.assertNotNull(actualMarkedSegs);
				Assert.assertEquals(10, actualMarkedSegs.size());
				Collections.sort(actualMarkedSegs);
				Assert.assertEquals(expectedSegs, actualMarkedSegs);

				htStore.unmarkSegments(DEF_TREE_ID, expectedSegs);
				actualMarkedSegs = htStore.getMarkedSegments(DEF_TREE_ID);
				Assert.assertNotNull(actualMarkedSegs);
				Assert.assertEquals(0, actualMarkedSegs.size());
			} finally {
				helper.cleanup(htStore);
			}
		}
	}

	@Test
	public void testDirtySegmentsPersistenceBetweenRestarts() throws Exception {
		for (HTStoreHelper helper : helpers) {
			HashTreesStore htStore = helper.getInstance();

			try {
				htStore.setDirtySegment(DEF_TREE_ID, DEF_SEG_ID);
				htStore.stop();
				htStore = helper.restartInstance(htStore);
				List<Integer> dirtySegments = htStore
						.getDirtySegments(DEF_TREE_ID);
				Assert.assertNotNull(dirtySegments);
				Assert.assertEquals(1, dirtySegments.size());
				Assert.assertEquals(DEF_SEG_ID, dirtySegments.get(0).intValue());
			} finally {
				helper.cleanup(htStore);
			}
		}
	}
}