package com.cybertron.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.cybertron.client.util.XmppUtil;

public class Logon implements ActionListener {
	/**
	 * @author bigBird {@link http://laycher.cn}
	 */
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf;
	XmppUtil mXmppUtil = new XmppUtil();
	static int flag = 0;

	public Logon() {
		jf = new JFrame("登录");
		jf.setLayout(new GridLayout(5, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel("用户名：");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);

		JLabel jl2 = new JLabel("  密  码： ");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);

		JPanel jp3 = new JPanel();
		JButton jb2 = new JButton("登录");
		jb2.addActionListener(this);
		JButton jb3 = new JButton("取消");
		jb3.addActionListener(this);
		jp3.add(jb2);
		jp3.add(jb3);
		jf.add(jp3);

		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 200);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Logon();
	}

	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("登录")) {
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null) {
				JOptionPane.showConfirmDialog(
						jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
						"用户名 或者 密码 不能为空！!\n请重新输入！", "错误",
						JOptionPane.DEFAULT_OPTION);
				jtf.setText(null);
				jpf.setText(null);
				jtf.requestFocus();// 光标回来
			} else {
				String name = jtf.getText();
				String pwd = new String(jpf.getPassword());
				System.out.println("name:"+ name + "pwd:"+pwd);
				tLogin.start();
			}
		} else if (comm.equals("取消")) {
			System.exit(0);
		}

	}
	
	Thread tLogin = new Thread(new Runnable() {
		public void run() {

			mXmppUtil.openConnection();
			flag = mXmppUtil.login("123", "123");
			if (0 == flag) {
				mXmppUtil.closeConnection();
			} else {
				jf.dispose();
				mXmppUtil.sendStatus(1);
				
				new SendBroadcast();
			}
		}
	});
}
