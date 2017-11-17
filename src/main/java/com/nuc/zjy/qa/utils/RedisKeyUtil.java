package com.nuc.zjy.qa.utils;

/**
 * @项目名称：QA
 * @类名称：RedisKeyUtil @类描述：key值生成
 *
 * @author 赵建银
 * @date 2017年11月9日
 * @time 上午10:45:08
 * @version 1.0
 */
public class RedisKeyUtil {
	private static String SPLIT = ":";
	private static String BIZ_LIKE = "LIKE";
	private static String BIZ_DISLIKE = "DISLIKE";

	// 粉丝
	private static String BIZ_FOLLOWER = "FOLLOWER";
	// 关注对象
	private static String BIZ_FOLLOWEE = "FOLLOWEE";

	/**
	 * 
	 * 通过实体id和实体type生成喜欢的key like:1:1
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public static String getLikeKey(int entityId, int entityType) {
		return BIZ_LIKE + SPLIT + String.valueOf(entityId) + SPLIT + String.valueOf(entityType);
	}

	/**
	 * 通过实体id和实体type生成不喜欢的key dislike:1:1
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public static String getDisLikeKey(int entityId, int entityType) {
		return BIZ_DISLIKE + SPLIT + String.valueOf(entityId) + SPLIT + String.valueOf(entityType);
	}

	public static String getFollowerKey(int entityId, int entityType) {
		return BIZ_FOLLOWER + SPLIT + String.valueOf(entityId) + SPLIT + String.valueOf(entityType);
	}

	public static String getFolloweeKey(int userId, int entityType) {
		return BIZ_FOLLOWEE + SPLIT + String.valueOf(userId) + SPLIT + String.valueOf(entityType);
	}
}
