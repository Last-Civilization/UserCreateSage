package com.lastcivilization.usercreatesage.workflow;

import com.lastcivilization.usercreatesage.UserCreateSageApplication;
import com.uber.cadence.workflow.Workflow;

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
        System.out.println("Deleted records! E: "+equipment+" S: "+stats+" A: "+account);
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
