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
 * 消费者接受监听器
 * @author a-lin
 *
 */

public class Listener implements MessageListener{


	
	public static void main(String[] args) {

	}
	
	//阻塞队列,有界1000个
	BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
	//参数1:核心的线程;最大20个;两秒把空闲的线程回收
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
