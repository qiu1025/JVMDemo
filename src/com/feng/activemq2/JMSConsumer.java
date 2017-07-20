package com.feng.activemq2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection = null;//连接
		Session session;//会话接受或者发送消息的线程
		Destination destination;//消息目的地
		MessageConsumer messageConsumer;
		
		connectionFactory=new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
	 	try {
			connection = connectionFactory.createConnection();//通过连接工厂获取连接
			connection.start();//启动连接
			session= connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);//创建Session
			//destination = session.createQueue("FirstQurur1");//创建消息队列
			destination = session.createTopic("FirstTopic1");
			
			messageConsumer = session.createConsumer(destination);//创建消息消费者
			
			messageConsumer.setMessageListener(new Listener());
			
	 	} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//通过连接工厂获取连接

		
	}
	
	
}
