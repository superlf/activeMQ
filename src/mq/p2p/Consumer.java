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
	
	public final String SELECT_01 ="age>300";//��д����Ҫ������ʱ����Ҫ�̶���set���Է���
	public final String SELECT_02 ="color='blue'";
	public final String SELECT_03 ="sal>500";
	public final String SELECT_04 ="";
	public final String SELECT_05 ="";
	
	
	/* ���ӹ���*/
	private  ConnectionFactory connectionFactory;
	/* ���Ӷ���*/
	private Connection connection;
	/* Session���� */
	private Session session;
	/* ������*/
	private MessageConsumer messageConsumer;
	/* ��Ϣ��ַ*/
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
		System.out.println("����������������������������");
		c.receiver();
		System.out.println("IS OK!!");
	
	
	}

 }









