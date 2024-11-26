package com.senac.victor.av2.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MQConfig {

	@Autowired 
	private AmqpAdmin amqpAdmin;
	private Queue queue;

	private Queue queue (String queueName){
		return new Queue(queueName, true, false, false);
	}

	private DirectExchange createDirectExchange(){
		return new DirectExchange("av2mq");
	}
	
	@PostConstruct
	private void Create (){
		this.queue = new Queue("fila-av2");

        // Create the direct exchange
        DirectExchange directExchange = createDirectExchange();
        
        // Create the binding
        Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
        						
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareExchange(directExchange);
		amqpAdmin.declareBinding(binding);
	}
}
