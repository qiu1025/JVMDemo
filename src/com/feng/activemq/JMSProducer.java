package com.feng.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {

	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	
	public static void main(String[] args) {
		
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection = null;//连接
		Session session;//会话接受或者发送消息的线程
		Destination destination;//消息目的地
		MessageProducer messageProducer;//消息生产者
		
		
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BROKEURL);
	
		try {
		 	connection = connectionFactory.createConnection();//通过连接工厂获取连接
			connection.start();//启动连接
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);//创建Session
			destination = session.createQueue("FirstQurur1");//创建消息队列
			messageProducer = session.createProducer(destination);//创建消息生产者
			sendMessage(session, messageProducer); //发送消息
			session.commit();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	
	private static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException{
		for(int i = 0;i<JMSProducer.SENDNUM;i++){
			TextMessage message= session.createTextMessage("ActiveMQ发送的消息"+ i);
			System.out.println("发送消息："+"ActiveMQ发送的消息"+i);
			messageProducer.send(message);
		}
	}
}
