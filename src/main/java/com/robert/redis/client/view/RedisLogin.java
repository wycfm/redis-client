package com.robert.redis.client.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RedisLogin extends JFrame{
	
	private static final String RL_TITLE = "Redis-Login";
	private static final int RL_WIDTH = 400;
	private static final int RL_HEIGTH = 200;
	
	private String host;
	private String password;
	private String port;
	
	private JLabel jlHost, jlPort, jlPassword;
	
	private JTextField jtfHost, jtfPort, jtfPassword;
	
	private JButton jLogin, jCannel;
	
	public RedisLogin(){
		jlHost = new JLabel("host");
		jlPort = new JLabel("port");
		jlPassword = new JLabel("password");
		jtfHost = new JTextField(20);
		jtfPort = new JTextField(20);
		jtfPassword = new JTextField(20);
		jLogin = new JButton("Login");
		jCannel = new JButton("Cannel");
		this.setTitle(RL_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(RL_WIDTH, RL_HEIGTH);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(4, 1, 10, 10));
		//this.setContentPane(RLJPanel());
		this.add(commonJPanel(jlHost, jtfHost));
		this.add(commonJPanel(jlPort, jtfPort));
		this.add(commonJPanel(jlPassword, jtfPassword));
		this.add(commonJPanel(jLogin, jCannel));
		
		initEvent();
	}
	
	
	
	private JPanel commonJPanel(Container jlabel, Container jtextField){
		JPanel jpanel = new JPanel();
		jpanel.add(jlabel);
		jpanel.add(jtextField);
		return jpanel;
	}
	
	private void initEvent(){
		
		//登录按钮监听
		/*jLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				host = jtfHost.getText();
				password = jtfPassword.getText();
				port = jtfPort.getText();
				RedisClient rc = new RedisClient(host, password, port);
				rc.setVisible(true);
				
			}
		});*/
		jLogin.addActionListener(new LoginButtonListener(this, jtfHost.getText(), jtfPort.getText(), jtfPassword.getText()));
		//关闭按钮
		jCannel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}
	
	public static void main(String[] args) {
		RedisLogin rl = new RedisLogin();
		rl.setVisible(true);
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPort() {
		return port;
	}



	public void setPort(String port) {
		this.port = port;
	}
}
class LoginButtonListener implements ActionListener{

	private RedisLogin el;
	private String h;
	private String port;
	private String pass;
	public LoginButtonListener(RedisLogin el, String h, String port, String pass){
		this.el = el;
		this.h= h;
		this.port = port;
		this.pass = pass;
	};
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		el.dispose();
		RedisClient rc = new RedisClient(h, Integer.parseInt(port));
		rc.setVisible(true);
	}
	
}
