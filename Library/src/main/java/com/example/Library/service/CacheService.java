package com.example.Library.service;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheService {
    private final Cache tokenCache;

    public CacheService(CacheManager cacheManager) {
        this.tokenCache = cacheManager.getCache("tokenCache");
    }

    public String generateAndCacheToken(String email) {
        String token = generateToken();
        tokenCache.put(token, email);
        return token;
    }

    public String getEmailToken(String token) {
        return (String) tokenCache.get(token).get();
    }

    public void removeToken(String token) {
        tokenCache.evict(token);
    }

    private String generateToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
