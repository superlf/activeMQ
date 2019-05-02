package mq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import mq.pb.Listener;

public class Consumer {
	
	public final String SELECT_01 ="age>300";//该写法需要在生产时候需要固定的set属性方法
	public final String SELECT_02 ="color='blue'";
	public final String SELECT_03 ="sal>500";
	public final String SELECT_04 ="";
	public final String SELECT_05 ="";
	
	
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
	
	public Consumer(){
		try {
			this.connectionFactory = new ActiveMQConnectionFactory("lfx","lfx","tcp://localhost:61616");
			this.connection= this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			this.destination = session.createQueue("testmq");
			this.messageConsumer = this.session.createConsumer(this.destination,SELECT_03);
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
		Consumer c = new Consumer();
		System.out.println("——————————————");
		c.receiver();
		System.out.println("IS OK!!");
	
	
	}

 }









