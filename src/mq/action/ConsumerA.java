package mq.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerA {
	
	public final String SELECTOR ="receiver='A'";
	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private MessageConsumer messageConsumer;
	private Destination destination;
	
	public ConsumerA(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.destination = session.createQueue("ationmq");
			this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR);
			System.out.println("Consumer A start...");

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void receiver(){
		
		try {
			this.messageConsumer.setMessageListener(new Listener());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		ConsumerA c = new ConsumerA();
		c.receiver();
	}

 }









