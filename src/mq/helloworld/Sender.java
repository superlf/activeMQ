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
		 * 第一步：建立ConnectionFactory工厂对象
		 * 	需要填入用户名/密码，以及要连接的地址(默认即可)
		 * 	默认地址"tcp://localhost:61616"
		 */
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//				ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,"tcp://localhost:61616");
		"lfx","lfx","tcp://localhost:61616");
		/** 第二步：通过ConnectionFactory工厂对象创建Connection连接,并调用start方法开启(默认是关闭的) */
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		/**
		 * 第三步：通过Connection对象创建Session会话，用于接收消息
		 * 	参数配置1：是否启用事务
		 * 	参数配置2：签收模式(一般设置为自动签收)
		 */
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		/**
		 * 第四步:通过Session对象创建Destination对象
		 * 	Destination对象 指的是一个客户端用来指定生产消息目标和消费消息来源的对象
		 * 	PTP模式中:Destination被称作Queue即队列
		 *  Pub/Sub模式:Destination被称作Topic即主题
		 */
		Destination destination =  session.createQueue("first");
		
		/** 第五步:通过Session对象创建消息的生产者OR消费者 MessageProducer/MessageConsumer */
		MessageProducer producer = session.createProducer(null);
		
		/** 第六步:使用MessageProducer的setDeliveryMode方法为其设置持久化特性和非持久化特性(DeliveryMode) */
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		/**
		 * 第七步:使用JMS规范的TextMessage形式创建数据(通过Session对象)
		 * 	生产者:使用MessageProducer 的send方法发送数据。
		 * 	消费者:使用MessageProducer 的receive方法进行接收
		 */

		for (int i = 0; i < 20; i++) {
			TextMessage msg = session.createTextMessage("消息内容:"+i);
			//第一个参数：目标地址
			//第二个参数：具体的数据信息
			//第三个参数：传送数据的模式
			//第四个参数：优先级
			//第五个参数：消息过期时间
			producer.send(destination,msg);
			TimeUnit.SECONDS.sleep(1);
			
		}
		 
		
		if(connection!=null){
			connection.close();
		}
		System.out.println("is OK !!");
		

	}

}
