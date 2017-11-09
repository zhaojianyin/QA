package com.nuc.zjy.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.zjy.qa.utils.RedisKeyUtil;

/**
 * @项目名称：QA
 * @类名称：LikeService @类描述：
 *
 * @author 赵建银
 * @date 2017年11月9日
 * @time 上午10:39:40
 * @version 1.0
 */
@Service
public class LikeService {

	@Autowired
	JedisAdapter jedisAdapter;

	/**
	 * 赞
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public long like(int userId, int entityId, int entityType) {
		String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
		jedisAdapter.sadd(likeKey, String.valueOf(userId));

		String dislikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
		jedisAdapter.srem(dislikeKey, String.valueOf(userId));
		return jedisAdapter.scard(likeKey);
	}

	/**
	 * 
	 * 踩
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public long dislike(int userId, int entityId, int entityType) {
		// 踩，将赞删除
		String dislikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
		jedisAdapter.sadd(dislikeKey, String.valueOf(userId));

		String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
		jedisAdapter.srem(likeKey, String.valueOf(userId));
		return jedisAdapter.scard(dislikeKey);
	}

	/**
	 * 
	 * 喜欢的状态
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public int getLikeStatus(int userId, int entityId, int entityType) {
		String likekey = RedisKeyUtil.getLikeKey(entityId, entityType);
		if (jedisAdapter.ssmember(likekey, String.valueOf(userId))) {
			return 1;
		}
		String dislikekey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
		if (jedisAdapter.ssmember(dislikekey, String.valueOf(userId))) {
			return -1;
		}
		return 0;
	}

	/**
	 * 获取赞总数
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public long getLikeCount(int userId, int entityId, int entityType) {
		String likekey = RedisKeyUtil.getLikeKey(entityId, entityType);
		return jedisAdapter.scard(likekey);
	}
}
