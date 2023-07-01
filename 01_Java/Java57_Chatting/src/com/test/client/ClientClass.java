package com.test.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 기능1
		// - 서버에 접속한다.
		// - 서버에 접속을 성공하면 서버로부터 메시지가 수신되는 것을 대기한다.
		// - 서버로 부터 메시지가 수신되면 수신된 메시지를 출력한다.
		
		// 기능2
		// - 키보드 입력 대기를 한다.
		// - 키보드로 부터 입력이 발생하면 입력된 메시지를 서버로 전달한다.
		
		new ChattingClient();
	}

}

class ChattingClient{
	
	final String SERVER_IP = "127.0.0.1";
	final int SERVER_PORT = 40000;
	private Socket socket;
	private Scanner scanner;
	
	public ChattingClient() {
		try {
			scanner = new Scanner(System.in);
			
			// 서버에 접속한다.
			socket = new Socket(SERVER_IP, SERVER_PORT);
			// System.out.println(socket);
			
			// 서버에 닉네임을 전달한다.
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			System.out.print("닉네임 : ");
			String nickName = scanner.next();
			dos.writeUTF(nickName);
			
			// 메시지 수신 쓰래드 가동
			MessageInputClass messageInputClass = new MessageInputClass(socket);
			messageInputClass.start();
			
			// 키보드 입력 쓰래드 가동
			KeyboardInputClass keyboardInputClass = new KeyboardInputClass(socket);
			keyboardInputClass.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메시지 수신
	class MessageInputClass extends Thread{
		private Socket socket;
		private InputStream is;
		private DataInputStream dos;
		
		public MessageInputClass(Socket socket) {
			try {
				this.socket = socket;
				is = socket.getInputStream();
				dos = new DataInputStream(is);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				while(true) {
					// 서버로 부터 데이터 수신 대기
					String msg = dos.readUTF();
					System.out.println(msg);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	// 키보드 입력 대기
	class KeyboardInputClass extends Thread{
		private Socket socket;
		private OutputStream os;
		private DataOutputStream dos;
		
		public KeyboardInputClass(Socket socket) {
			try {
				this.socket = socket;
				os = socket.getOutputStream();
				dos = new DataOutputStream(os);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			try {
				while(true) {
					// 키보드로 부터 입력을 받는다.
					String msg = scanner.next();
					// 서버로 보낸다.
					dos.writeUTF(msg);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}








