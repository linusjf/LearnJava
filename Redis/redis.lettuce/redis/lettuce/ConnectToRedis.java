package redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;

public final class ConnectToRedis {

  private static final String FOO = "foo";

  private ConnectToRedis() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    RedisClient redisClient = RedisClient.create("redis://localhost");

    try (StatefulRedisConnection<String, String> connection =
             redisClient.connect()) {
      System.out.println("Connected to Redis");
      RedisStringCommands<String, String> sync = connection.sync();
      sync.set(FOO, "bar");
      String value = sync.get(FOO);
      System.out.println(value);
    }
    redisClient.shutdown();
  }
}
