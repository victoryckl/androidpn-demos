package com.cybertron.client.util;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

public class XmppUtil {

	private static XMPPConnection xmppConnect = null;
	final static int right = 1;
	final static int error = 0;

	public XMPPConnection openConnection() {
		if (xmppConnect == null) {
			try {
				ConnectionConfiguration connConfig = new ConnectionConfiguration(
						"192.168.1.112", 5222);
				xmppConnect = new XMPPConnection(connConfig);
				xmppConnect.connect();
			} catch (XMPPException xe) {
				xe.printStackTrace();
			}
		}
		return xmppConnect;
	}
	public int login(String user, String pwd) {
		int val = error;
		if (xmppConnect != null) {
			try {
				xmppConnect.login(user, pwd);
				val = right;
			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return val;
	}
	
	public int sendStatus(int val) {
		if (xmppConnect != null) {
			Presence presence = null;
			if(val == 1)
			{
				//available
				presence = new Presence(Presence.Type.available);
			}
			else if(val == 2)
			{
				//unavailable
				presence = new Presence(Presence.Type.unavailable);
			}
			xmppConnect.sendPacket(presence);
		}
		return right;
	}
	
	public static int sendPacket(Packet packet){
		xmppConnect.sendPacket(packet);
		return right;
	}
	
	public void closeConnection() {
		if (xmppConnect != null) {
			xmppConnect.disconnect();
			xmppConnect = null;
		}
	}
}
