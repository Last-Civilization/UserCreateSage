package com.lastcivilization.usercreatesage.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "stats-write-service")
public interface StatsClient {

    @DeleteMapping("/stats/{id}")
    void deleteStats(@PathVariable long id);
}
