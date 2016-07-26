package cn.miao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.miao.redis.RedisClientTemplate;
import redis.clients.jedis.Client;

public class RedisTest extends BaseFunctionalTestCase {

	@Autowired
	RedisClientTemplate redisClient;

	@Test
	public void redisClient() {
		redisClient.set("cnblog", "cnblog");
		redisClient.set("redis", "redis");
		redisClient.set("test", "test");
		redisClient.set("123456", "1234567");
		
		Client client1 = redisClient.getShard("cnblog").getClient();
		Client client2 = redisClient.getShard("redis").getClient();
		Client client3 = redisClient.getShard("test").getClient();
		Client client4 = redisClient.getShard("123456").getClient();
		//// 打印key在哪个server中
		System.out.println("cnblog in server:" + client1.getHost() + " and port is:" + client1.getPort());
		System.out.println("redis  in server:" + client2.getHost() + " and port is:" + client2.getPort());
		System.out.println("test   in server:" + client3.getHost() + " and port is:" + client3.getPort());
		System.out.println("123456 in server:" + client4.getHost() + " and port is:" + client4.getPort());
	}
	
	@Test
	public void saveUserl() {
		redisClient.set("1002", 5, "user1001");
	}
	
	@Test
	public void saveUser() {
		redisClient.set("1001", "user1001");
	}

	@Test
	public void getUser() {
		System.err.println(redisClient.get("1002"));
	}

}
