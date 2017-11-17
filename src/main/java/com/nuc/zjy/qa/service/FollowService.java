package com.nuc.zjy.qa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.zjy.qa.utils.RedisKeyUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @项目名称：QA
 * @类名称：FollowService @类描述：
 *
 * @author 赵建银
 * @date 2017年11月15日
 * @time 下午4:52:21
 * @version 1.0
 */
@Service
public class FollowService {

	@Autowired
	JedisAdapter jedisAdapter;

	/**
	 * 
	 * 关注
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public boolean follow(int userId, int entityId, int entityType) {
		String followerkey = RedisKeyUtil.getFollowerKey(entityId, entityType);
		String followeekey = RedisKeyUtil.getFolloweeKey(userId, entityType);
		Date date = new Date();
		Jedis jedis = jedisAdapter.getJedis();
		Transaction tx = jedisAdapter.multi(jedis);
		tx.zadd(followerkey, date.getTime(), String.valueOf(userId));
		tx.zadd(followeekey, date.getTime(), String.valueOf(entityId));
		List<Object> exec = jedisAdapter.exec(tx, jedis);
		return exec.size() == 2 && (Long) exec.get(0) > 0 && (Long) exec.get(1) > 0;
	}

	/**
	 * 
	 * 取消关注
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public boolean unfollow(int userId, int entityId, int entityType) {
		String followerkey = RedisKeyUtil.getFollowerKey(entityId, entityType);
		String followeekey = RedisKeyUtil.getFolloweeKey(userId, entityType);
		Jedis jedis = jedisAdapter.getJedis();
		Transaction tx = jedisAdapter.multi(jedis);
		tx.zrem(followerkey, String.valueOf(userId));
		tx.zrem(followeekey, String.valueOf(entityId));
		List<Object> exec = jedisAdapter.exec(tx, jedis);
		return exec.size() == 2 && (Long) exec.get(0) > 0 && (Long) exec.get(1) > 0;
	}

	private List<Integer> getIdsFromSet(Set<String> set) {
		List<Integer> list = new ArrayList<>();
		for (String ss : set) {
			list.add(Integer.parseInt(ss));
		}
		return list;
	}

	/**
	 * 
	 * 所有关注的人
	 * 
	 * @param entityId
	 * @param entityType
	 * @param count
	 * @return
	 */
	public List<Integer> getFollowers(int entityId, int entityType, int count) {
		String followerKey = RedisKeyUtil.getFollowerKey(entityId, entityType);
		return getIdsFromSet(jedisAdapter.zrevrange(followerKey, 0, count));
	}

	/**
	 * 
	 * 关注列表
	 * 
	 * @param userId
	 * @param entityType
	 * @param count
	 * @return
	 */
	public List<Integer> getFollowees(int userId, int entityType, int count) {
		String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
		return getIdsFromSet(jedisAdapter.zrevrange(followeeKey, 0, count));
	}

	/**
	 * 
	 * 
	 * 关注人数
	 * 
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public Long getFollowerCount(int entityId, int entityType) {
		String FollowerKey = RedisKeyUtil.getFollowerKey(entityId, entityType);
		return jedisAdapter.zcard(FollowerKey);

	}

	/**
	 * 
	 * 关注的数量
	 * 
	 * @param userId
	 * @param entityType
	 * @return
	 */
	public Long getFolloweeCount(int userId, int entityType) {
		String FolloweeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
		return jedisAdapter.zcard(FolloweeKey);
	}

	/**
	 * 
	 * 是否关注某人
	 * 
	 * @param userId
	 * @param entityId
	 * @param entityType
	 * @return
	 */
	public boolean isFollower(int userId, int entityId, int entityType) {
		String followerKey = RedisKeyUtil.getFollowerKey(entityId, entityType);
		return jedisAdapter.zscore(followerKey, String.valueOf(userId)) != null;
	}
}
