package com.github.bakery.cesco.week03.woojin;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingCallableProcessingInterceptor implements CallableProcessingInterceptor {

    @Override
    public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("===== beforeConcurrentHandling =====");
        log.info("request: {}, task: {}", request, task);
    }

    @Override
    public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("===== preProcess =====");
        log.info("request: {}, task: {}", request, task);
    }

    @Override
    public <T> void postProcess(NativeWebRequest request, Callable<T> task, Object concurrentResult) throws Exception {
        log.info("===== postProcess =====");
        log.info("request: {}, task: {}, concurrentResult: {}", request, task, concurrentResult);
    }

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("===== handleTimeout =====");
        log.info("request: {}, task: {}", request, task);
        return RESULT_NONE;
    }

    @Override
    public <T> Object handleError(NativeWebRequest request, Callable<T> task, Throwable t) throws Exception {
        log.info("===== handleError =====");
        log.info("request: {}, task: {}", request, task);
        return RESULT_NONE;
    }

    @Override
    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("===== afterCompletion =====");
        log.info("request: {}, task: {}", request, task);
    }
}
