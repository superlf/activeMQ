package mq.pb;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 * 单例模式
 * @author a-lin
 */

public class Publish {
	
	/* 连接工厂 */
	private  ConnectionFactory connectionFactory;
	
	/* 连接对象 */
	private Connection connection;
	
	/* Session对象 */
	private Session session;
	
	/* 生产者 */
	private MessageProducer topicProducer;
	
	public Publish(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.topicProducer = this.session.createProducer(null);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public void send1(){
		try {
			/* 消息主题地址 */
			Destination destination = this.session.createTopic("topic1");
			TextMessage tm = session.createTextMessage("我是订阅的消息");
			topicProducer.send(destination,tm);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		Publish p = new Publish();
		
		p.send1();

	}

}
