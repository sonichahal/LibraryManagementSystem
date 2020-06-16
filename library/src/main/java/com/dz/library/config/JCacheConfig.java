package com.dz.library.config;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

@Configuration
@EnableCaching
public class JCacheConfig {

    JCacheManagerCustomizer jCacheManagerCustomizer() {
        return new JCacheManagerCustomizer() {
            @Override
            public void customize(CacheManager cacheManager) {
                MutableConfiguration<Object, Object> config = new MutableConfiguration<>();
                config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
                config.setStatisticsEnabled(true);
                cacheManager.createCache("getBooks", config);
                cacheManager.createCache("deleteBook", config);
            }
        };
    }
}
