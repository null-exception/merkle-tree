namespace java org.hashtrees.thrift.generated

/**
* Contains nodeId and segment hash.
* 
**/
struct SegmentHash
{
	1: required i32 nodeId;
	2: required binary hash;
}

/**
* Contains key, digest of the value
*
**/
struct SegmentData
{
	1: required binary key;
	2: required binary digest;
}

/**
* Contains hostname, and port no.
*
**/
struct ServerName
{
	1: required string hostName;
	2: required i32 portNo;
}

struct RemoteTreeInfo
{
	1: required ServerName sn;
	2: required i64 treeId;
}

/**
* Rebuild hashtree request object.
*
* @param requester, server name which requests for the rebuild 
* @param treeId
* @param tokenNo, a unique tokenNo to differentiate similar requests.
* @param expFullRebuildTimeInt, if the remote tree is not fully rebuilt
*        within this interval, then remote tree is expected to do a full
*        rebuild, otherwise just dirty segments rebuild.
*/
struct RebuildHashTreeRequest
{
	1: required ServerName requester;
	2: required i64 treeId;
	3: required i64 tokenNo;
	4: required i64 expFullRebuildTimeInt;
}

/**
* Response after a hashtree has been rebuilt.

* @param responder, server which has executed the rebuild of the tree. 
* @param treeId, which treeId was rebuilt.
* @param tokenNo, the tokenNo from previous hashtree rebuild request.
**/
struct RebuildHashTreeResponse
{
	1: required ServerName responder;
	2: required i64 treeId;
	3: required i64 tokenNo;
}

service HashTreesSyncInterface
{
	
	/**
     * Adds the (key,value) pair to the store. Intended to be used
     * while synch operation.
     * 
     * @param keyValuePairs
     */
	void sPut(1:map<binary,binary> keyValuePairs);
	
	/**
     * Deletes the keys from the store. While synching this function is used.
     * 
     * @param keys
     */
	void sRemove(1:list<binary> keys);
	
	/**
     * Hash tree internal nodes store the hash of their children nodes. Given a
     * set of internal node ids, this returns the hashes that are stored on the
     * internal node.
     * 
     * @param treeId
     * @param nodeIds, internal tree node ids.
     * @return
     */
	list<SegmentHash> getSegmentHashes(1:i64 treeId, 2:list<i32> nodeIds);
	
	/**
     * Returns the segment hash that is stored on the tree.
     * 
     * @param treeId, hash tree id.
     * @param nodeId, node id
     * @return
     */
	SegmentHash getSegmentHash(1:i64 treeId, 2:i32 nodeId);
	
	/**
     * Hash tree data is stored on the leaf blocks. Given a segment id this
     * method is supposed to return (key,hash) pairs.
     * 
     * @param treeId
     * @param segId, id of the segment block.
     * @return
     */
	list<SegmentData> getSegment(1:i64 treeId, 2:i32 segId);
	
	/**
     * Returns the (key,digest) for the given key in the given segment.
     * 
     */
	SegmentData getSegmentData(1:i64 treeId, 2:i32 segId, 3:binary key);
	
	/**
     * Deletes tree nodes from the hash tree, and the corresponding segments.
     * 
     */
	void deleteTreeNodes(1:i64 treeId, 2:list<i32> nodeIds);
	
	/**
	 * Requests a rebuild of the hashtree.
	 *
	 */
    void submitRebuildRequest(1:RebuildHashTreeRequest request);

    /**
     * Submits the response to the server.
     * 
     */
    void submitRebuildResponse(1:RebuildHashTreeResponse response);
    
    /**
	 * Adds a remote tree to sync list. Hashtrees on the local server will be synched
	 * against the remote tree.
	 *
	 */
	void addToSyncList(1:RemoteTreeInfo rTree);

	/**
	 * Removes a remote tree from sync list. From the next iteration, the remote
	 * tree will not be synched by the local server.
	 * 
	 */
	void removeFromSyncList(1:RemoteTreeInfo rTree);
	
	/**
	* Returns which remote trees are synced by this instance.
	*
	*/
	list<RemoteTreeInfo> getSyncList();
}