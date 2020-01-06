package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;

public class MainVerticle extends AbstractVerticle {

  private RedisClient redisClient;
  private MongoClient mongoClient;

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
//    DeploymentOptions options = new DeploymentOptions().setInstances(4);
//    vertx.deployVerticle(MainVerticle.class.getName(), options);
    vertx.deployVerticle(MainVerticle.class.getName());
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    String host = Vertx.currentContext().config().getString("host");
    if (host == null) {
      host = "120.77.146.135";
    }
    JsonObject config = Vertx.currentContext().config();

    String uri = config.getString("mongo_uri");
    if (uri == null) {
      uri = "mongodb://39.108.174.173:27017";
    }
    String db = config.getString("mongo_db");
    if (db == null) {
      db = "dbpick_resource";
    }
    JsonObject mongoconfig = new JsonObject()
      .put("connection_string", uri)
      .put("db_name", db);
    redisClient = RedisClient.create(vertx, new RedisOptions().setHost(host).setPort(6380).setAuth("baobao"));
    mongoClient = MongoClient.createShared(vertx, mongoconfig);

    Router router = Router.router(vertx);
    // 将"/"绑定到我们的hello消息 - 从而保持兼容性
    router.get("/testRedis").handler(this::queryRedis);
    router.get("/testNormal").handler(this::queryNormal);
    router.get("/testMongo").handler(this::queryMongo);
    vertx.createHttpServer().requestHandler(router).listen(8899, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8899");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void queryRedis(RoutingContext routingContext) {
    redisClient.get("app:version:1", res -> {
      if (res.succeeded()) {
        routingContext.response().end(res.result());
      }else {
        System.out.println(res.cause().getMessage());
        res.cause().printStackTrace();
        routingContext.response().setStatusCode(500).end();
      }
    });
  }

  private void queryNormal(RoutingContext routingContext) {
        routingContext.response().end("11111111111111111111");
  }

  private void queryMongo(RoutingContext routingContext) {
    mongoClient.find("dictionaries", new JsonObject().put("code", "sms"), res -> {
      if (res.succeeded()) {
        routingContext.response().end(res.result().toString());
      }else {
        System.out.println(res.cause().getMessage());
        res.cause().printStackTrace();
        routingContext.response().setStatusCode(500).end();
      }
    });
  }
}
