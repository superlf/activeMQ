package mq.action;

import javax.jms.JMSException;
import javax.jms.MapMessage;

public class MessageTask implements Runnable{
	
	private MapMessage message;
	
	public MessageTask(MapMessage message){
		this.message = message;
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(500);
			/* ����ҵ���� */
			System.out.println("��ǰ�߳�:"+ Thread.currentThread().getName()+"��������:" + this.message.getString("id"));
			
		} catch (InterruptedException | JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
