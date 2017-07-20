package com.feng.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer2 {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//���ӹ���
		Connection connection = null;//����
		Session session;//�Ự���ܻ��߷�����Ϣ���߳�
		Destination destination;//��ϢĿ�ĵ�
		MessageConsumer messageConsumer;
		
		connectionFactory=new ActiveMQConnectionFactory(JMSConsumer2.USERNAME, JMSConsumer2.PASSWORD, JMSConsumer2.BROKEURL);
	 	try {
			connection = connectionFactory.createConnection();//ͨ�����ӹ�����ȡ����
			connection.start();//��������
			session= connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);//����Session
			destination = session.createQueue("FirstQurur1");//������Ϣ����
			messageConsumer = session.createConsumer(destination);//������Ϣ������
			
			messageConsumer.setMessageListener(new Listener());
			
	 	} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ͨ�����ӹ�����ȡ����

		
	}
	
	
}