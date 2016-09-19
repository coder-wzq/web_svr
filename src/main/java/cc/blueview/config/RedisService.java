package cc.blueview.config;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
	private Logger logger = LoggerFactory.getLogger(RedisService.class);
	@Autowired
	private RedisTemplate<String, Object> template;

	@Autowired
	RedisConnectionFactory connectionFactory;

	public Object getValue(String key) {
		return template.opsForValue().get(key);
	}

	public void put(String key, String value) {
		template.opsForValue().set(key, value);
		template.expire(key, 3600, TimeUnit.SECONDS);
	}

	public void putMap(String key, Map<String, Object> map) {
		Gson gson = new Gson();
		template.opsForValue().set(key, gson.toJson(map).toString());
	}

	public Map<String, Object> getMap(String key) {
		Gson gson = new Gson();
		Object obj = getValue(key);
		if (obj != null)
			return gson.fromJson(obj.toString(), new HashMap<String, Object>().getClass());
		else
			return null;

	}

	public void put(String key, String value, int expireSecond) {
		template.opsForValue().set(key, value);
		template.expire(key, expireSecond, TimeUnit.SECONDS);
	}

	public void delete(String key) {
		template.delete(key);
	}

	public void deleteKeys(String prefix) {
		Set<String> keys = template.keys(String.format("%s*", prefix));
		template.delete(keys);
	}

	public void flushAll() {
		Set<String> keys = template.keys("cache_*");
		template.delete(keys);
	}

}
