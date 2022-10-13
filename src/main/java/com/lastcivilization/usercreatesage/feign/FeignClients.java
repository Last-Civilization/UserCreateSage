package com.lastcivilization.usercreatesage.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignClients {

    private final EquipmentClient equipmentClient;
    private final PaymentClient paymentClient;
    private final StatsClient statsClient;

    public void deleteEquipement(long id){
        equipmentClient.deleteEquipment(id);
    }

    public void deleteAccount(long id){
        paymentClient.deleteAccount(id);
    }

    public void deleteStats(long id){
        statsClient.deleteStats(id);
    }
}
