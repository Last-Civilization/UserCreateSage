package com.lastcivilization.usercreatesage.workflow;

import com.uber.cadence.workflow.Workflow;

public class UserCreateWorkflowImp implements UserCreateWorkflow{

    private long equipment;
    private long stats;
    private long account;
    private boolean error = false;

    @Override
    public void handleUserCreate() {
        while (!error){
            System.out.println("CURRENT STATE: ");
            System.out.println("EQ: "+equipment);
            System.out.println("STATS: "+stats);
            System.out.println("ACCOUNT: "+account);
            Workflow.await(() -> error);
        }
        System.out.println("CURRENT STATE: ");
        System.out.println("EQ: "+equipment);
        System.out.println("STATS: "+stats);
        System.out.println("ACCOUNT: "+account);
        deleteNotCohesionRecords();
    }

    private void deleteNotCohesionRecords() {
        System.out.println("Deleted records!");
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
