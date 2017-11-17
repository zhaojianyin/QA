package com.nuc.zjy.qa.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

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

	public Jedis getJedis() {
		return pool.getResource();
	}

	/**
	 * 开启事务
	 * 
	 * @param jedis
	 * @return
	 */
	public Transaction multi(Jedis jedis) {
		try {
			return jedis.multi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object> exec(Transaction tx, Jedis jedis) {
		try {
			return tx.exec();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (tx != null) {
				try {
					tx.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public long zadd(String key, double score, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.zadd(key, score, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	public Set<String> zrevrange(String key, int start, int end) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public long zcard(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return 0;
	}

	public Double zscore(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

}
