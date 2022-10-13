package com.lastcivilization.usercreatesage.workflow;

import com.lastcivilization.usercreatesage.UserCreateSageApplication;
import com.lastcivilization.usercreatesage.feign.EquipmentClient;
import com.lastcivilization.usercreatesage.feign.FeignClients;
import com.uber.cadence.workflow.Workflow;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserCreateWorkflowImp implements UserCreateWorkflow{

    private long equipment;
    private long stats;
    private long account;
    private boolean error = false;

    @Override
    public void handleUserCreate() {
        Workflow.await(() -> error);
        deleteNotCohesionRecords();
    }

    private void deleteNotCohesionRecords() {
        FeignClients feignClients = (FeignClients) UserCreateSageApplication.applicationContext.getBean("feignClients");
        try{
            feignClients.deleteEquipement(equipment);
        } catch (FeignException exception){
            log.error(exception.getMessage());
        }
        try{
            feignClients.deleteStats(stats);
        } catch (FeignException exception){
            log.error(exception.getMessage());
        }
        try{
            feignClients.deleteAccount(account);
        } catch (FeignException exception){
            log.error(exception.getMessage());
        }
    }

    @Override
    public void setEquipment(long id) {
        this.equipment = id;
    }

    @Override
    public void setStats(long id) {
        this.stats = id;
    }

    @Override
    public void setAccount(long id) {
        this.account = id;
    }

    @Override
    public void handleException() {
        this.error = true;
    }
}
