package com.nuc.zjy.qa.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @项目名称：QA
 * @类名称：JedisAdapter @类描述：操作redis
 *
 * @author 赵建银
 * @date 2017年11月9日
 * @time 上午10:22:28
 * @version 1.0
 */
@Service
public class JedisAdapter implements InitializingBean {

	private JedisPool pool;

	@Override
	public void afterPropertiesSet() throws Exception {
		pool = new JedisPool("redis://192.168.42.66:6379/10");
	}

	/**
	 * 向集合添加一个或多个成员
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long sadd(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sadd(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	/**
	 * 移除集合中一个或多个成员
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public long srem(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.srem(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	/**
	 * 获取集合的成员数
	 * 
	 * @param key
	 * @return
	 */
	public long scard(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.scard(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	/**
	 * 判断 member 元素是否是集合 key 的成员
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean ssmember(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.sismember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return false;
	}

}
