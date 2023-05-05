package redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

public final class ConnectToRedis {

  private ConnectToRedis() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    RedisClient redisClient = RedisClient.create("redis://localhost");

    try (StatefulRedisConnection<String, String> connection =
             redisClient.connect()) {
      System.out.println("Connected to Redis");
      RedisStringCommands<String, String> sync = connection.sync();
      String value = sync.get("key");
      System.out.println(value);
    }
    redisClient.shutdown();
  }
}
