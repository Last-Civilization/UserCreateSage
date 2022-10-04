package com.lastcivilization.usercreatesage.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "equipment-write-service")
public interface EquipmentClient {

    @DeleteMapping("/equipments/{id}")
    void deleteEquipment(@PathVariable long id);
}
