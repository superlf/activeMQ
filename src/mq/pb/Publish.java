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
 * ��Ϣ������
 * ����ģʽ
 * @author a-lin
 */

public class Publish {
	
	/* ���ӹ��� */
	private  ConnectionFactory connectionFactory;
	
	/* ���Ӷ��� */
	private Connection connection;
	
	/* Session���� */
	private Session session;
	
	/* ������ */
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
			/* ��Ϣ�����ַ */
			Destination destination = this.session.createTopic("topic1");
			TextMessage tm = session.createTextMessage("���Ƕ��ĵ���Ϣ");
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
