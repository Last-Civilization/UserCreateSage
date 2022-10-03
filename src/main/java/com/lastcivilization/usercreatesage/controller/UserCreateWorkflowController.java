package com.lastcivilization.usercreatesage.controller;

import com.lastcivilization.usercreatesage.service.UserCreateWorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/workflows")
@RequiredArgsConstructor
class UserCreateWorkflowController {

    private final UserCreateWorkflowService userCreateWorkflowService;

    @GetMapping
    String startWorkflow(){
        return userCreateWorkflowService.startWorkflow();
    }

    @PutMapping("/{workflowId}/equipments/{id}")
    String setEquipmentId(@PathVariable String workflowId, @PathVariable long id){
        return userCreateWorkflowService.setEquipmentId(workflowId, id);
    }

    @PutMapping("/{workflowId}/stats/{id}")
    String setStatsId(@PathVariable String workflowId, @PathVariable long id){
        return userCreateWorkflowService.setStatsId(workflowId, id);
    }

    @PutMapping("/{workflowId}/accounts/{id}")
    String setAccountId(@PathVariable String workflowId, @PathVariable long id){
        return userCreateWorkflowService.setAccountId(workflowId, id);
    }

    @PutMapping("/{workflowId}/exceptions")
    String setEquipmentId(@PathVariable String workflowId){
        return userCreateWorkflowService.handleException(workflowId);
    }
}
