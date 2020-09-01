package com.github.bakery.cesco.week03;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
public class CustomAsyncManagerIntegrationFilter implements Filter, CallableProcessingInterceptor {

    private final AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        final WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
//        asyncManager.setTaskExecutor(asyncTaskExecutor);
//        asyncManager.
    }

    public <T> void beforeConcurrentHandling(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("beforeConcurrentHandling");
    }

    public <T> void preProcess(NativeWebRequest request, Callable<T> task) throws Exception {
        log.info("preProcess");
    }

    public <T> void postProcess(NativeWebRequest request, Callable<T> task,
            Object concurrentResult) throws Exception {
        log.info("postProcess");
    }

    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        log.error("handleTimeout");
        return RESULT_NONE;
    }

    public <T> Object handleError(NativeWebRequest request, Callable<T> task, Throwable t) throws Exception {
        log.error("handleError");
        return RESULT_NONE;
    }

    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task) throws Exception {
        log.error("afterCompletion");
    }

}
