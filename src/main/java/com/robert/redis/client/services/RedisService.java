package com.robert.redis.client.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.robert.redis.client.config.RedisPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

public class RedisService implements IRedisService{

	private RedisPool redisPool;
	
	public RedisService(){
		redisPool = new RedisPool();
	}
	
	public RedisService(String host){
		redisPool = new RedisPool(host);
	}
	
	public RedisService(String host, int port){
		redisPool = new RedisPool(host, port);
	}
	
	private Jedis getJResource(){
		Jedis jResource = null;
		jResource = redisPool.getJedis();
		return jResource;
	}
	private ShardedJedis getShardResource(){
		ShardedJedis sResource = null;
		sResource = redisPool.getShardedJedis();
		return sResource;
	}
	
	public Boolean setString(String key, String value) {
		boolean result = false;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				resource.set(key, value);
				result = true;
			}
			
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public String getString(String key) {
		String result = null;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				result = resource.get(key);
			}
			
		}catch(Exception e){
			result = null;
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public Boolean existsKey(String key) {
		Boolean result = false;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				result = resource.exists(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		
		return result;
	}

	public Long delKey(String key) {
		Long result = null;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				result = resource.del(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public String typeKey(String key) {
		String result = null;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				result = resource.type(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public Set<String> keys(String key) {
		Set<String> result = null;
		Jedis resource = null;
		try{
			resource = getJResource();
			if(resource != null){
				result = resource.keys(key);
			}
			
		}catch(Exception e){
			result = null;
			e.printStackTrace();
		}finally{
			redisPool.closeJedisPool();
		}
		return result;
	}

	public Map<String, String> getMap(String key) {
		Map<String, String> map = null;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null && resource.exists(key)){
				map = resource.hgetAll(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return map;
	}

	public Boolean setMap(String key, Map<String, String> map) {
		Boolean result = false;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				resource.hmset(key, map);
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public String getMapFieldValue(String key, String fieldKey) {
		String result = null;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null && resource.hexists(key, fieldKey)){
				result = resource.hget(key, fieldKey);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

	public Map<String, String> getMapFieldValues(String key, String[] fieldKeys) {
		Map<String, String> map = new HashMap<String, String>();
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null){
				for(String fieldKey : fieldKeys){
					map.put(fieldKey, resource.hget(key, fieldKey));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return map;
	}

	public Boolean setMapFieldValue(String key, String fieldKey, String fieldValue) {
		Boolean result = false;
		ShardedJedis resource = null;
		try{
			resource = getShardResource();
			if(resource != null && resource.exists(key)){
				resource.hset(key, fieldKey, fieldValue);
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			redisPool.closeShardedJedisPool();
		}
		return result;
	}

}
