package mq.pb;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * �����߽��ܼ�����
 * @author a-lin
 *
 */

public class Listener implements MessageListener{

	public static void main(String[] args) {

	}

	@Override
	public void onMessage(Message message) {

		if(message instanceof TextMessage){
			System.out.println("�յ���Ϣ����:");
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println(tm.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
		if(message instanceof MapMessage){
			MapMessage mm = (MapMessage)message;
			try {
				System.out.println(mm.toString());
				System.out.println(mm.getString("name"));
				System.out.println(mm.getString("age"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
