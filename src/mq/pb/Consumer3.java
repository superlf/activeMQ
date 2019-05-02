package mq.pb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer3 {
	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private MessageConsumer messageConsumer;
	private Destination destination;
	
	public Consumer3(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.destination = session.createTopic("topic1");
			this.messageConsumer = this.session.createConsumer(this.destination);
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
		Consumer3 c = new Consumer3();
		c.receiver();
	
	}

 }









