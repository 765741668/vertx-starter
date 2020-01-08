package com.orochi.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class VertxServer extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);
    // 将"/"绑定到我们的hello消息 - 从而保持兼容性
    router.get("/restapp/testVm/:id").handler(this::queryNormal);
    vertx.createHttpServer().requestHandler(router).listen(8899, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8899");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void queryNormal(RoutingContext routingContext) {
    routingContext.response().end("11111111111111111111");
  }

}
