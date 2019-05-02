package mq.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerB {
	
	public final String SELECTOR ="receiver='B'";
	
	
	/* 连接工厂*/
	private  ConnectionFactory connectionFactory;
	/* 连接对象*/
	private Connection connection;
	/* Session对象 */
	private Session session;
	/* 消费者*/
	private MessageConsumer messageConsumer;
	/* 消息地址*/
	private Destination destination;
	
	public ConsumerB(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.destination = session.createQueue("ationmq");
			this.messageConsumer = this.session.createConsumer(this.destination,SELECTOR);
			System.out.println("Consumer B start...");
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
		ConsumerB c = new ConsumerB();
		c.receiver();
	}

 }









