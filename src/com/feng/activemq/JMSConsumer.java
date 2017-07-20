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

public class JMSConsumer {
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;//���ӹ���
		Connection connection = null;//����
		Session session;//�Ự���ܻ��߷�����Ϣ���߳�
		Destination destination;//��ϢĿ�ĵ�
		MessageConsumer messageConsumer;
		
		connectionFactory=new ActiveMQConnectionFactory(JMSConsumer.USERNAME, JMSConsumer.PASSWORD, JMSConsumer.BROKEURL);
	 	try {
			connection = connectionFactory.createConnection();//ͨ�����ӹ�����ȡ����
			connection.start();//��������
			session= connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);//����Session
			destination = session.createQueue("FirstQurur1");//������Ϣ����
			messageConsumer = session.createConsumer(destination);//������Ϣ������
			while(true){
				TextMessage textMessage= (TextMessage)messageConsumer.receive(10000);
				if(textMessage!=null){
					System.out.println("�յ���Ϣ"+textMessage.getText());
				}else{
					break;
				}
			
			}
	 	} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//ͨ�����ӹ�����ȡ����

		
	}
	
	
}