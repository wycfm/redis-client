package com.robert.redis.client.config;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisPool {
	
	// 非切片客户端链接对象
	private Jedis jedis;
	// 非切片链接池对象
	private JedisPool jedisPool;
	// 切片客户端链接对象
	private ShardedJedis shardedJedis;
	// 切片链接池
	private ShardedJedisPool shardedJedisPool;
	
	private String ip = "127.0.0.1";
	private int port = 6379;

	public RedisPool(){
		initializePool();
		initializeShardedPool();
		setJedis(jedisPool.getResource());
		setShardedJedis(shardedJedisPool.getResource());
	}
	
	public RedisPool(String ip){
		this.ip = ip;
		initializePool();
		initializeShardedPool();
		setJedis(jedisPool.getResource());
		setShardedJedis(shardedJedisPool.getResource());
	}
	
	public RedisPool(String ip, int port){
		this.ip = ip;
		this.port = port;
		initializePool();
		initializeShardedPool();
		setJedis(jedisPool.getResource());
		setShardedJedis(shardedJedisPool.getResource());
		
	}
	
	// 初始化非切片池 
	public void initializePool(){
		//池的配置
		JedisPoolConfig jpc = new JedisPoolConfig();
		//最大空闲连接数
		jpc.setMaxIdle(20);
		jpc.setMaxIdle(5);
		//获取连接时的最大等待毫秒数  
		jpc.setMaxWaitMillis(1000);
		//在空闲时检查有效性, 默认false  
		jpc.setTestOnBorrow(false);
		jedisPool = new JedisPool(jpc, ip, port);
	}
	
	// 初始化切片池
	public void initializeShardedPool(){
		//池的配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(20);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(false);
		// slave链接 
		//可以实现集群的功能，配置多个redis服务实现请求的分配进行负载均衡  
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(ip, port, "master"));
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}
	
	public void closeJedisPool(){
		jedisPool.close();
	}
	public void closeShardedJedisPool(){
		shardedJedisPool.close();
	}

	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public void setShardedJedis(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}
}
