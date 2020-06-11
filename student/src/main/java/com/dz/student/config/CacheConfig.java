package com.dz.student.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager(){
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("studentsCache");
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        cacheConfiguration.setMaxEntriesLocalHeap(1000);
        cacheConfiguration.setTimeToLiveSeconds(60);

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return null;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }


//    @Bean
//    public EhCacheManagerFactoryBean cacheManager() {
//        return new EhCacheManagerFactoryBean();
//    }
//
//    @Bean
//    public EhCacheCacheManager testEhCacheManager() {
//        // testEhCache Configuration - create configuration of cache that previous required XML
//        CacheConfiguration testEhCacheConfig = new CacheConfiguration()
//                .eternal(false)                     // if true, timeouts are ignored
//                .timeToIdleSeconds(3)               // time since last accessed before item is marked for removal
//                .timeToLiveSeconds(60)               // time since inserted before item is marked for removal
//                .maxEntriesLocalHeap(1000)            // total items that can be stored in cache
//                .memoryStoreEvictionPolicy("LRU")   // eviction policy for when items exceed cache. LRU = Least Recently Used
//                .name("studentsCache");
//
//        Cache testCache = new Cache(testEhCacheConfig);
//
//        Objects.requireNonNull(cacheManager().getObject()).addCache(testCache);
//        return new EhCacheCacheManager(Objects.requireNonNull(cacheManager().getObject()));
//    }




//    @Bean
//    public EhCacheCacheManager ehCacheCacheManager() {
//
//        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
//    }
//
//
//    @Bean
//    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
//
//        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//
//        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
//        cacheManagerFactoryBean.setShared(true);
//
//        return cacheManagerFactoryBean;
//    }

}
