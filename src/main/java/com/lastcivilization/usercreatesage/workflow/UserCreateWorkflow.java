package com.lastcivilization.usercreatesage.workflow;

import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;

public interface UserCreateWorkflow {

    String TASK_LIST = "Create-User";
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 600, taskList = TASK_LIST)
    void handleUserCreate();

    @SignalMethod
    void setEquipment(long id);

    @SignalMethod
    void setStats(long id);

    @SignalMethod
    void setAccount(long id);

    @SignalMethod
    void handleException();
}
