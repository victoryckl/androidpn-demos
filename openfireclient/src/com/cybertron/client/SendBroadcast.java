package com.cybertron.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jivesoftware.smack.packet.IQ;

import com.cybertron.client.broadcast.Broadcast;
import com.cybertron.client.util.XmppUtil;

public class SendBroadcast implements ActionListener {
	/**
	 * @author bigBird {@link http://laycher.cn}
	 */
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf, jpf2;

	public SendBroadcast() {
		jf = new JFrame("发送广播");
		jf.setLayout(new GridLayout(6, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel(" 标  题：");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);

		JLabel jl2 = new JLabel(" 信  息：");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);

		JLabel jl3 = new JLabel(" 地  址：");
		jpf2 = new JPasswordField(12);
		JPanel jp3 = new JPanel();
		jp3.add(jl3);
		jp3.add(jpf2);
		jf.add(jp3);

		JPanel jp4 = new JPanel();
		JButton jb1 = new JButton("发送消息");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("取消");
		jb2.addActionListener(this);
		jp4.add(jb1);
		jp4.add(jb2);
		jf.add(jp4);

		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 240);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new SendBroadcast();
//	}

	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("发送消息")) {
			// jf.dispose();
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null
					|| "".equals(new String(jpf2.getPassword()))
					|| jpf2.getPassword() == null) {
				final JFrame jf = new JFrame("错误");
				JLabel jl = new JLabel("用户名 或者 密码 不能为空！");
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				jp1.add(jl);
				jf.add(jp1);
				JButton jb = new JButton("确定");
				jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						jf.dispose();
					}

				});
				jp2.add(jb);
				jf.add(jp2);
				jf.setLayout(new GridLayout(2, 1));
				jf.setResizable(false);
				jf.setVisible(true);
				jf.pack();
				jf.setLocation(400, 300);
			} else {
				String title = jtf.getText();
				String message = new String(jpf.getPassword());
				String uri = new String(jpf2.getPassword());
				System.out.println("title: "+title+ " message: "+ message+ " uri: "+ uri);
				
				Broadcast broadcast = new Broadcast();
				broadcast.setType(IQ.Type.GET);
				broadcast.setTitle(jtf.getText());
				broadcast.setMessage(new String(jpf.getPassword()));
				broadcast.setUri(new String(jpf2.getPassword()));
				XmppUtil.sendPacket(broadcast);
				
			}
		} else if (comm.equals("取消")) {
			System.exit(0);
		}

	}
}
