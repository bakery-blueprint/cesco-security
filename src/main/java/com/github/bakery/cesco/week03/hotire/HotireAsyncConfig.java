package com.github.bakery.cesco.week03.hotire;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class HotireAsyncConfig {

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(15);
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(5);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationMillis(3000L);
        return executor;
    }

    @Bean
    public FilterRegistrationBean<HotireCustomAsyncManagerIntegrationFilter> filterRegistrationBean() {
        final FilterRegistrationBean<HotireCustomAsyncManagerIntegrationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new HotireCustomAsyncManagerIntegrationFilter(asyncTaskExecutor()));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}
