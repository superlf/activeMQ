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
			/* 进行业务处理 */
			System.out.println("当前线程:"+ Thread.currentThread().getName()+"处理任务:" + this.message.getString("id"));
			
		} catch (InterruptedException | JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
