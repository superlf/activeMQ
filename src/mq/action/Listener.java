package mq.action;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	
	//��������,�н�1000��
	BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
	//����1:���ĵ��߳�;���20��;����ѿ��е��̻߳���
	ExecutorService executor = new ThreadPoolExecutor(
			Runtime.getRuntime().availableProcessors(),
			20, 120L, TimeUnit.SECONDS, queue);
	

	@Override
	public void onMessage(Message message) {

		if(message instanceof MapMessage){
			MapMessage mm = (MapMessage)message;
			executor.execute(new MessageTask(mm));
		}
		
	}


}
