package com.lastcivilization.usercreatesage.service;

import com.lastcivilization.usercreatesage.workflow.UserCreateWorkflow;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateWorkflowService {

    private final WorkflowClient workflowClient;

    public String startWorkflow(){
        UserCreateWorkflow userCreateWorkflow = workflowClient.newWorkflowStub(UserCreateWorkflow.class);
        WorkflowExecution workflowExecution = WorkflowClient.start(userCreateWorkflow::handleUserCreate);
        return workflowExecution.getWorkflowId();
    }

    public String setEquipmentId(String workflowId, long equipmentId){
        UserCreateWorkflow userCreateWorkflow = workflowClient.newWorkflowStub(UserCreateWorkflow.class, workflowId);
        userCreateWorkflow.setEquipment(equipmentId);
        return workflowId;
    }

    public String setStatsId(String workflowId, long statsId){
        UserCreateWorkflow userCreateWorkflow = workflowClient.newWorkflowStub(UserCreateWorkflow.class, workflowId);
        userCreateWorkflow.setStats(statsId);
        return workflowId;
    }

    public String setAccountId(String workflowId, long accountId){
        UserCreateWorkflow userCreateWorkflow = workflowClient.newWorkflowStub(UserCreateWorkflow.class, workflowId);
        userCreateWorkflow.setAccount(accountId);
        return workflowId;
    }

    public String handleException(String workflowId){
        UserCreateWorkflow userCreateWorkflow = workflowClient.newWorkflowStub(UserCreateWorkflow.class, workflowId);
        userCreateWorkflow.handleException();
        return workflowId;
    }
}
