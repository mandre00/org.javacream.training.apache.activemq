package org.javacream.training.jms.transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.javacream.training.util.jms.JmsBase;
import org.javacream.training.util.jms.JmsUtil;

public class SimpleConsumer extends JmsBase{

	public SimpleConsumer(){
		super(false, Session.AUTO_ACKNOWLEDGE);
		JmsUtil.setListener(getSession(), JmsUtil.createQueue(getSession(), TransactionalConstants.DESTINATION_CONSUMER), new SimpleMessageListener());
		try {
			getConnection().start();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}

	}
	public static void main(String[] args) {
		new SimpleConsumer();
	}
	
	private class SimpleMessageListener implements MessageListener{

		@Override
		public void onMessage(Message message) {
			System.out.println("received message: " + message);
		}
		
	}
}
