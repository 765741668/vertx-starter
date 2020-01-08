package com.orochi.starter.core.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_
 * //                         o8888888o
 * //                         88" . "88
 * //                         (| ^_^ |)
 * //                         O\  =  /O
 * //                      ____/`---'\____
 * //                    .'  \\|     |//  `.
 * //                   /  \\|||  :  |||//  \
 * //                  /  _||||| -:- |||||-  \
 * //                  |   | \\\  -  /// |   |
 * //                  | \_|  ''\---/''  |   |
 * //                  \  .-\__  `-`  ___/-. /
 * //                ___`. .'  /--.--\  `. . ___
 * //              ."" '<  `.___\_<|>_/___.'  >'"".
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //      ========`-.____`-.___\_____/___.-`____.-'========
 * //                           `=---='
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //         佛祖保佑       永无BUG     永不修改
 * ////////////////////////////////////////////////////////////////////
 *@Description: 路由发布
 *
 *@author  Orochi-Yzh
 *@dateTime  1/4/2020 3:35 PM
*/
public class RouterRegistryVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouterRegistryVerticle.class);

    private static final int MAX_WEBSOCKET_FRAME_SIZE = 1000000;

    private static final int HTTP_PORT = 8080;

    private int port = HTTP_PORT;

    private HttpServer server;

    private Router router;

    public RouterRegistryVerticle(Router router) {
        this.router = router;
    }

    public RouterRegistryVerticle(Router router, int port) {
        this.router = router;
        if (port > 0) {
            this.port = port;
        }
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        LOGGER.debug("To start listening to port {} ......", port);
        super.start();
        HttpServerOptions options = new HttpServerOptions().setMaxWebsocketFrameSize(MAX_WEBSOCKET_FRAME_SIZE).setPort(port);
        server = vertx.createHttpServer(options);
        server.requestHandler(router);
        server.listen(result -> {
            if (result.succeeded()) {
                startPromise.complete();
                LOGGER.debug("RouterRegistry started on port {}",port);
            } else {
                startPromise.fail(result.cause());
            }
        });
    }

    @Override
    public void stop(Promise<Void> startPromise) {
        if (server == null) {
            startPromise.complete();
            return;
        }
        server.close(result -> {
            if (result.failed()) {
                startPromise.fail(result.cause());
            } else {
                startPromise.complete();
            }
        });
    }
}
