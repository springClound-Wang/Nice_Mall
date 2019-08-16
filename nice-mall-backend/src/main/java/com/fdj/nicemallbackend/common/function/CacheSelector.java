package com.fdj.nicemallbackend.common.function;

@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}
