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
		jf = new JFrame("��¼");
		jf.setLayout(new GridLayout(5, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel("�û�����");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);

		JLabel jl2 = new JLabel("  ��  �룺 ");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);

		JPanel jp3 = new JPanel();
		JButton jb2 = new JButton("��¼");
		jb2.addActionListener(this);
		JButton jb3 = new JButton("ȡ��");
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
		if (comm.equals("��¼")) {
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null) {
				JOptionPane.showConfirmDialog(
						jf, // ���Ϊnull���˿����ʾ�����룬Ϊjf����ʾΪjf������
						"�û��� ���� ���� ����Ϊ�գ�!\n���������룡", "����",
						JOptionPane.DEFAULT_OPTION);
				jtf.setText(null);
				jpf.setText(null);
				jtf.requestFocus();// ������
			} else {
				String name = jtf.getText();
				String pwd = new String(jpf.getPassword());
				System.out.println("name:"+ name + "pwd:"+pwd);
				tLogin.start();
			}
		} else if (comm.equals("ȡ��")) {
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
