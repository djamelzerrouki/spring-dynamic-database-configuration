package com.example.configuration;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import de.hsansbach.ecommerce.engine.LoggingExecutionParseListener;
import de.hsansbach.ecommerce.engine.LoggingStartEndEventExecutionParseListener;
import de.hsansbach.ecommerce.engine.LoggingTransitionParseListener;
import de.hsansbach.ecommerce.engine.LoggingUserTaskExecutionParseListener;

@Component
@Order(Ordering.DEFAULT_ORDER + 1)
public class ProcessEngineConfiguration implements ProcessEnginePlugin {

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		List<BpmnParseListener> bpmnParseListener = new ArrayList<>();
		bpmnParseListener.add(new LoggingExecutionParseListener());
		bpmnParseListener.add(new LoggingStartEndEventExecutionParseListener());
		bpmnParseListener.add(new LoggingTransitionParseListener());
		bpmnParseListener.add(new LoggingUserTaskExecutionParseListener());
		processEngineConfiguration.setCustomPostBPMNParseListeners(bpmnParseListener);
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {
	}

}