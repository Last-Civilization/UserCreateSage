package com.lastcivilization.usercreatesage.config;

import com.lastcivilization.usercreatesage.workflow.UserCreateWorkflow;
import com.lastcivilization.usercreatesage.workflow.UserCreateWorkflowImp;
import com.uber.cadence.DescribeDomainResponse;
import com.uber.cadence.ListDomainsRequest;
import com.uber.cadence.ListDomainsResponse;
import com.uber.cadence.RegisterDomainRequest;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.uber.cadence.worker.WorkerOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

import static com.lastcivilization.usercreatesage.config.CadenceConfiguration.DOMAIN;

@Component
@Slf4j
public class CadenceWorkerStarter {
    private static final Class<?>[] WORKFLOW_IMPLEMENTATION_TYPES = new Class[] { UserCreateWorkflowImp.class };
    private final IWorkflowService workflowService;
    private final WorkerFactory workerFactory;
    private final WorkerOptions workerOptions;

    public CadenceWorkerStarter(IWorkflowService workflowService, WorkerFactory workerFactory, WorkerOptions workerOptions) {
        this.workflowService = workflowService;
        this.workerFactory = workerFactory;
        this.workerOptions = workerOptions;
    }

    @PostConstruct
    public void startWorkerFactory() throws TException {
        if (!domainExists()) {
            registerDomain();
        }
        createWorkers();
        log.info("Starting Cadence Worker Factory");
        workerFactory.start();
    }

    @PreDestroy
    public void shutdownWorkerFactory() {
        log.info("Shutdown Cadence Worker Factory");
        workerFactory.shutdown();
    }

    private void registerDomain() throws TException {
        RegisterDomainRequest request = new RegisterDomainRequest();
        request.setDescription(DOMAIN);
        request.setEmitMetric(false);
        request.setName(DOMAIN);
        request.setWorkflowExecutionRetentionPeriodInDays(2);
        workflowService.RegisterDomain(request);
        log.info("Successfully registered domain \"{}\"", DOMAIN);
    }

    private void createWorkers() {
        Worker worker = workerFactory.newWorker(UserCreateWorkflow.TASK_LIST, workerOptions);
        worker.registerWorkflowImplementationTypes(WORKFLOW_IMPLEMENTATION_TYPES);
    }

    private boolean domainExists() throws TException {
        try {
            ListDomainsRequest listDomainsRequest = new ListDomainsRequest();
            ListDomainsResponse response = workflowService.ListDomains(listDomainsRequest);
            List<DescribeDomainResponse> domains = response.getDomains();
            return domains.stream()
                    .anyMatch(d -> d.domainInfo.name.equals(DOMAIN));
        } catch (UnsupportedOperationException e) {
            log.warn("Listing or registering domains is not supported when using a local embedded test server, " +
                    "these steps will be skipped");
            return true;
        }
    }
}
