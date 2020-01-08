package com.orochi.starter;

import io.vertx.core.Vertx;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VertxListener {

  @EventListener
  public void deployVerticles(ApplicationReadyEvent event) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(VertxServer.class.getName());
  }
}
