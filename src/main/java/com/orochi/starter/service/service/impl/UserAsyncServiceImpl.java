//package com.orochi.starter.service.service.impl;
//
//import com.orochi.starter.core.anno.AsyncServiceHandler;
//import com.orochi.starter.core.model.BaseAsyncService;
//import com.orochi.starter.service.entity.User;
//import com.orochi.starter.service.service.UserAsyncService;
//import com.orochi.starter.service.service.UserService;
//import io.vertx.core.AsyncResult;
//import io.vertx.core.Future;
//import io.vertx.core.Handler;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * ////////////////////////////////////////////////////////////////////
// * //                          _ooOoo_
// * //                         o8888888o
// * //                         88" . "88
// * //                         (| ^_^ |)
// * //                         O\  =  /O
// * //                      ____/`---'\____
// * //                    .'  \\|     |//  `.
// * //                   /  \\|||  :  |||//  \
// * //                  /  _||||| -:- |||||-  \
// * //                  |   | \\\  -  /// |   |
// * //                  | \_|  ''\---/''  |   |
// * //                  \  .-\__  `-`  ___/-. /
// * //                ___`. .'  /--.--\  `. . ___
// * //              ."" '<  `.___\_<|>_/___.'  >'"".
// * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |
// * //            \  \ `-.   \_ __\ /__ _/   .-` /  /
// * //      ========`-.____`-.___\_____/___.-`____.-'========
// * //                           `=---='
// * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// * //         佛祖保佑       永无BUG     永不修改
// * ////////////////////////////////////////////////////////////////////
// *@Description:
// *
// *@author  Orochi-Yzh
// *@dateTime  1/4/2020 4:22 PM
//*/
//@Component
//@AsyncServiceHandler
//public class UserAsyncServiceImpl implements UserAsyncService, BaseAsyncService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void findByAge(int age, Handler<AsyncResult<List<User>>> resultHandler) {
//        try {
//            List<User> UserList = userService.findByAge(age);
//            Future.succeededFuture(UserList).setHandler(resultHandler);
//        } catch (Exception e) {
//            resultHandler.handle(Future.failedFuture(e));
//        }
//    }
//}
