package mq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息生产者
 * 单例模式
 * @author a-lin
 */

public class Producer {
	
	/* 连接工厂 */
	private  ConnectionFactory connectionFactory;
	
	/* 连接对象 */
	private Connection connection;
	
	/* Session对象 */
	private Session session;
	
	/* 生产者 */
	private MessageProducer messageProducer;
	
	public Producer(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.messageProducer = this.session.createProducer(null);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public void send1(){
		try {
			/* 消息队列地址 */
			Destination destination = this.session.createQueue("testmq");
			
			MapMessage msg1 = this.session.createMapMessage();
			/* 两种字符串set方法的区别:第二种可以参与条件过滤 */
			msg1.setString("name", "wukong");
			msg1.setString("age", "500");
			msg1.setStringProperty("color", "red");
			msg1.setIntProperty("sal", 2000);
			
			MapMessage msg2 = this.session.createMapMessage();
			msg2.setString("name", "bajie");
			msg2.setString("age", "330");
			msg2.setStringProperty("color", "balck");
			msg2.setIntProperty("sal", 200);
			
			MapMessage msg3 = this.session.createMapMessage();
			msg3.setString("name", "liubei");
			msg3.setString("age", "50");
			msg3.setStringProperty("color", "blue");
			msg3.setIntProperty("sal", 600);
			
			messageProducer.send(destination, msg1, DeliveryMode.PERSISTENT, 1, 1000*60);
			messageProducer.send(destination, msg2, DeliveryMode.PERSISTENT, 2, 1000*60);
			messageProducer.send(destination, msg3, DeliveryMode.PERSISTENT, 3, 1000*60);
			messageProducer.send(destination, msg1, DeliveryMode.PERSISTENT, 4, 1000*60);
			
			System.out.println("is OJBK");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		Producer p = new Producer();
		
		p.send1();

	}

}
