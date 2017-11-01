package com.robert.redis.client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.robert.redis.client.services.IRedisService;
import com.robert.redis.client.services.RedisService;

public class RedisClient extends JFrame{

	private static final String RC_TITLE = "RedisClient-java";
	private static final int RC_WIDTH = 900;
	private static final int RC_HEIGHT = 600;
	private IRedisService redisService ;
	
	public RedisClient(){
		redisService = new RedisService();
		this.setTitle(RC_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(RC_WIDTH, RC_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setContentPane(RCJPannel());
	}
	public RedisClient(String host){
		
		redisService = new RedisService(host);
		this.setTitle(RC_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(RC_WIDTH, RC_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setContentPane(RCJPannel());
	}
	public RedisClient(String host, int port){
		
		redisService = new RedisService(host, port);
		this.setTitle(RC_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(RC_WIDTH, RC_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setContentPane(RCJPannel());
	}
	
	
	public static void main(String[] args) {
		
		RedisClient rc = new RedisClient("10.0.2.54");
		rc.setVisible(true);
	}

	
	private JPanel RCJPannel(){
		JPanel rcjp = new JPanel();
		RCJMenuBar();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints sbc = new GridBagConstraints();
		rcjp.setLayout(layout);
		
		JList treeJList = treeJList();
		JScrollPane jsp = new JScrollPane(treeJList);
		jsp.setBounds(0, 0, 300, 600);
		JPanel leftBorderJPanel = new JPanel();
		JPanel rightJPanel = new JPanel();
		JPanel rightBorderJPanel = new JPanel();
		rightJPanel.setBackground(Color.PINK);
		rcjp.add(leftBorderJPanel);
		rcjp.add(jsp);
		rcjp.add(rightJPanel);
		rcjp.add(rightBorderJPanel);
		sbc.fill = GridBagConstraints.BOTH;
		sbc.gridwidth = 1;
		sbc.weightx = 0;
		sbc.weighty = 1;
		layout.setConstraints(leftBorderJPanel, sbc);
		sbc.gridwidth = 1;
		sbc.weightx = 0;
		sbc.weighty = 1;
		layout.setConstraints(jsp, sbc);
		sbc.gridwidth = 5;
		sbc.weightx = 1;
		sbc.weighty = 1;
		layout.setConstraints(rightJPanel, sbc);
		sbc.gridwidth = 0;
		sbc.weightx = 0;
		sbc.weighty = 1;
		layout.setConstraints(rightBorderJPanel, sbc);
		return rcjp;
	}
	
	//菜单
	private JMenuBar RCJMenuBar(){
		JMenuBar rcjmb = new JMenuBar();
		this.setJMenuBar(rcjmb);
		JMenu file, search, help;
		file = new JMenu("文件");
		search = new JMenu("搜索");
		search.addSeparator();
		help = new JMenu("帮助");
		
		rcjmb.add(file);
		rcjmb.add(search);
		rcjmb.add(help);
		
		JMenuItem jmi1, jmi2;
		jmi1 = new JMenuItem("新建连接");
		jmi2 = new JMenuItem("导出");
		
		jmi1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RedisLogin rl = new RedisLogin();
				rl.setVisible(true);
				
			}
		});
		file.add(jmi1);
		file.addSeparator();
		file.add(jmi2);
		return rcjmb;
	}
	
	
	
	//左边框--以后单独抽出来
	public JList treeJList(){
		
		Set<String> keys = redisService.keys("*");
		int row = keys.size();
		String[] array = new String[row];
		int i=0;
		for(String key : keys){
			if(redisService.typeKey(key).equals("hash")){
				
			}
			
			array[i] = key;
			i++;	
			
		}
		JList jlist = new JList(array);
		
		return jlist;
	}
	
	
}
