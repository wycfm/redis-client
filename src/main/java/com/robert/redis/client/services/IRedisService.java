package com.robert.redis.client.services;

import java.util.Map;
import java.util.Set;

public interface IRedisService {

	
	public Boolean setString(String key, String value);
	public String getString(String key);
	public Boolean existsKey(String key);
	public Long delKey(String key);
	public String typeKey(String key);
	public Set<String> keys(String key);
	
	/**
	 * 获得map数据集
	 * @param key
	 * @return
	 */
	public Map<String, String> getMap(String key);
	
	/**
	 * 设置map数据集
	 * @param key
	 * @param map
	 * @return
	 */
	public Boolean setMap(String key, Map<String, String> map);
	
	/**
	 * 获得map字段中的值
	 * @param key
	 * @param fieldKey
	 * @return
	 */
	public String getMapFieldValue(String key, String fieldKey);
	
	/**
	 * 获得map中多个字段值
	 * @param key
	 * @param fieldKeys
	 * @return
	 */
	public Map<String, String> getMapFieldValues(String key, String[] fieldKeys);
	
	/**
	 * 设置map中具体的字段值
	 * 参考存储格式：{key,map{fieldKey, fieldValue}}
	 * @param key
	 * @param fieldKey
	 * @param fieldValue
	 * @return
	 */
	public Boolean setMapFieldValue(String key, String fieldKey, String fieldValue);
	
	
}
