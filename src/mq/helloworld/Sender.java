package mq.helloworld;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	public static void main(String[] args) throws JMSException, InterruptedException {
		
		/**
		 * ��һ��������ConnectionFactory��������
		 * 	��Ҫ�����û���/���룬�Լ�Ҫ���ӵĵ�ַ(Ĭ�ϼ���)
		 * 	Ĭ�ϵ�ַ"tcp://localhost:61616"
		 */
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//				ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://localhost:61616");
		"lfx","lfx","tcp://localhost:61616");
		/** �ڶ�����ͨ��ConnectionFactory�������󴴽�Connection����,������start��������(Ĭ���ǹرյ�) */
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		/**
		 * ��������ͨ��Connection���󴴽�Session�Ự�����ڽ�����Ϣ
		 * 	��������1���Ƿ���������
		 * 	��������2��ǩ��ģʽ(һ������Ϊ�Զ�ǩ��)
		 */
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		/**
		 * ���Ĳ�:ͨ��Session���󴴽�Destination����
		 * 	Destination���� ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ���
		 * 	PTPģʽ��:Destination������Queue������
		 *  Pub/Subģʽ:Destination������Topic������
		 */
		Destination destination =  session.createQueue("first");
		
		/** ���岽:ͨ��Session���󴴽���Ϣ��������OR������ MessageProducer/MessageConsumer */
		MessageProducer producer = session.createProducer(null);
		
		/** ������:ʹ��MessageProducer��setDeliveryMode����Ϊ�����ó־û����Ժͷǳ־û�����(DeliveryMode) */
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		/**
		 * ���߲�:ʹ��JMS�淶��TextMessage��ʽ��������(ͨ��Session����)
		 * 	������:ʹ��MessageProducer ��send�����������ݡ�
		 * 	������:ʹ��MessageProducer ��receive�������н���
		 */

		for (int i = 0; i < 20; i++) {
			TextMessage msg = session.createTextMessage("��Ϣ����:"+i);
			//��һ��������Ŀ���ַ
			//�ڶ��������������������Ϣ
			//�������������������ݵ�ģʽ
			//���ĸ����������ȼ�
			//�������������Ϣ����ʱ��
			producer.send(destination,msg);
			TimeUnit.SECONDS.sleep(1);
			
		}
		 
		
		if(connection!=null){
			connection.close();
		}
		System.out.println("is OK !!");
		

	}

}
