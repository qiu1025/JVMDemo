package com.feng.activemq2;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("订阅者收到的消息："+(((TextMessage) message).getText()));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
