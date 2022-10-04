package com.lastcivilization.usercreatesage.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment-write-service")
public interface PaymentClient {

    @DeleteMapping("/payments/{id}")
    void deleteAccount(@PathVariable long id);
}
