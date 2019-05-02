package mq.action;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;


public class Producer {
	
	private ConnectionFactory connectionFactory;
	
	private Connection connection;
	
	private Session session;
	
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
			Destination destination = this.session.createQueue("ationmq");
			
			for(int i=0;i<50;i++ ){
				MapMessage msg = this.session.createMapMessage();
				int id = i;
				msg.setInt("id", id);
				msg.setString("name", "张"+i);
				msg.setString("age", ""+i);
				/* 取模算法,设置一个属性 */
				String receiver = id%2 ==0?"A":"B";
				msg.setStringProperty("receiver", receiver);
				this.messageProducer.send(destination,msg,DeliveryMode.PERSISTENT, 4, 1000*60);
				System.out.println("message send id:"+id);
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Producer p = new Producer();
		
		p.send1();

	}

}
