package com.ruichen.restful;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisPipelined() {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            for (int i = 0; i < 10; i++) {
                String redisKey = "key_"  + i;
                String redisValue = "value_"  + i;
                connection.set(redisKey.getBytes(), redisValue.getBytes());
            }
            return null;
        });
    }

}
