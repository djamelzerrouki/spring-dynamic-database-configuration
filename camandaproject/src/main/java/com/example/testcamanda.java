package com.example;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngineServices;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.impl.javax.el.ExpressionFactory;
import org.camunda.bpm.engine.impl.javax.el.ValueExpression;
import org.camunda.bpm.engine.impl.juel.ExpressionFactoryImpl;
import org.camunda.bpm.engine.impl.juel.SimpleContext;
import org.camunda.bpm.engine.impl.juel.SimpleResolver;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;
import org.camunda.bpm.model.xml.type.ModelElementType;

public class testcamanda {
	static BpmnModelInstance modelInstance;
	static void mymethod(){
		File file = new File("../camandaproject\\src\\main\\resources\\loanApproval.bpmn");
		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
		getTaskNode(modelInstance);
		// find element instance by ID
		StartEvent start = (StartEvent) modelInstance.getModelElementById("start");

		// find all elements of the type task
		modelInstance.getModel().getType(Task.class);
		ModelElementType taskType = modelInstance.getModel().getType(Task.class);
		Collection<ModelElementInstance> taskInstances = modelInstance.getModelElementsByType(taskType);
		//taskInstances.forEach(System.out::println);

		SequenceFlow sequenceFlow = (SequenceFlow) modelInstance.getModelElementById("SequenceFlow_0sg4sqa");

		// get the source and target element
		FlowNode source = sequenceFlow.getSource();
		FlowNode target = sequenceFlow.getTarget();

		// get all outgoing sequence flows of the source
		Collection<SequenceFlow> outgoing = source.getOutgoing();
		assert(outgoing.contains(sequenceFlow));

	}
	public static void main(String[] arges){
		mymethod();
	}

	public static  Collection<FlowNode> getFlowingFlowNodes(FlowNode node) {
		Collection<FlowNode> followingFlowNodes = new ArrayList<FlowNode>();
		for (SequenceFlow sequenceFlow : node.getOutgoing()) {
			followingFlowNodes.add(sequenceFlow.getTarget());
		}
		return followingFlowNodes;
	}
	public static void getStertEventNode(BpmnModelInstance modelInstance ){
		StartEvent start = (StartEvent) modelInstance.getModelElementById("start");

		// read attributes by helper methods
		String id = start.getId();
		String name = start.getName();

		// edit attributes by helper methods
		start.setId("new-id");
		start.setName("new name");

		// read attributes by generic XML model API (with optional namespace)
		String custom1 = start.getAttributeValue("custom-attribute");
		String custom2 = start.getAttributeValueNs("custom-attribute-2", "http://camunda.org/custom");

		// edit attributes by generic XML model API (with optional namespace)
		start.setAttributeValue("custom-attribute", "new value");
		start.setAttributeValueNs("custom-attribute", "http://camunda.org/custom", "new value");
	}
	// task method get node
	public static void getTaskNode(BpmnModelInstance modelInstance ){
		Collection<Task> tasks = (Collection<Task>) modelInstance.getModelElementsByType(Task.class);
		tasks.forEach(t->{

			// read attributes bvars.entrySet()y helper methods
			String id = t.getId();
			String name = t.getName();
			String type=t.getElementType().getTypeName();
			List<Task> possibleTasks=	new testcamanda().getNextTasks(t.getId() );

			System .out.println("ID: "+ id +" Name : "+name +" Type : "+type);
			possibleTasks.forEach(tsk->{
				System .out.println("NEXT TASK -> ID: "+ tsk.getId() +" Name : "+tsk.getName() +" Type : "+type);
			});

		});

	}


	public List<Task> getNextTasks(  String taskDefinitionKey ) {
		File file = new File("../camandaproject\\src\\main\\resources\\loanApproval.bpmn");

		BpmnModelInstance modelInstance = Bpmn.readModelFromFile(file);
		ModelElementInstance instance = modelInstance.getModelElementById(taskDefinitionKey);
		FlowNode flowNode = (FlowNode)instance;
		return getOutgoingTask(flowNode );
	}
	private List<Task> getOutgoingTask(FlowNode node ) {
		List<Task> possibleTasks = new ArrayList<>();

		for(SequenceFlow sf: node.getOutgoing()) {
			if (sf.getName() != null) {
				//	LOGGER.info("Entering flow node {}", sf.getName());
			}
			boolean next = true;
			if (sf.getConditionExpression() != null) {
				ExpressionFactory factory = new ExpressionFactoryImpl();
				SimpleContext context = new SimpleContext(new SimpleResolver());

				//	LOGGER.info("Generating map vars {}", vars.toString());

				ValueExpression expr1 = factory.createValueExpression(context, sf.getConditionExpression().getTextContent(), boolean.class);

				next = (Boolean)expr1.getValue(context);
				//	LOGGER.info("Evaluating expression {} to result {}", sf.getConditionExpression().getTextContent(), expr1.getValue(context));

			}

			if (next && sf.getTarget() != null) {

				if (sf.getTarget() instanceof  Task) {
					//LOGGER.info("Found next   task {}", sf.getTarget().getName());
					//System.out.println("Found next   task : "+ sf.getTarget().getName());
					possibleTasks.add((Task) sf.getTarget());
					break;
				}

				possibleTasks.addAll(getOutgoingTask(sf.getTarget()));
			}


		}
		return possibleTasks;
	}
}

