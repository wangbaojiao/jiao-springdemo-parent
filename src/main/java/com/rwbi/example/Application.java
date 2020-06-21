package com.rwbi.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangbaojiao
 * @date ：Created in 6/16/2020 1:41 PM
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication
@Slf4j
@Configuration
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//单位为ms
        factory.setConnectTimeout(5000);//单位为ms
        return factory;
    }

//    @Bean
//    public CacheManager cacheManager() throws URISyntaxException, FileNotFoundException {
//	*//*创建springCacheManager接口的具体实现类，参数是javax下面的CacheManager实现类*//*
//        return new JCacheCacheManager(jCacheManager());
//    }
//
//    @Bean
//    public javax.cache.CacheManager jCacheManager() throws URISyntaxException, FileNotFoundException {
//
//        //ehcache实现了javax的CachingProvider接口的具体实现
//        EhcacheCachingProvider ehcacheCachingProvider = new EhcacheCachingProvider();
//
//        //根据配置文件获取cachemanager
//        URI uri = ResourceUtils.getURL("classpath:ehcache.xml").toURI();
//
//        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(uri,this.getClass().getClassLoader());
//
//        Cache<Long, OtcOrderDetail> orderDetailCache = cacheManager.getCache("orderDetailCache");
//
//
//        return cacheManager;
//
//    }


}
