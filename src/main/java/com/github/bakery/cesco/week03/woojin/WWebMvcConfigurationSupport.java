package com.github.bakery.cesco.week03.woojin;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WWebMvcConfigurationSupport extends WebMvcConfigurationSupport {

    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.initialize();
        configurer.setTaskExecutor(taskExecutor);

        configurer.registerCallableInterceptors(new LoggingCallableProcessingInterceptor());
    }

}
