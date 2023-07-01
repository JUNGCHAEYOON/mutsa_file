package com.test.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 서버에 접속한다.
			// 이때, IP 주소와 포트 번호를 설정해줘야 한다.
			// 127.0.0.1 : 자기 자신 컴퓨터를 가르키는 IP주소
			// 서버와 클라이언트를 같은 컴퓨터에서 개발할 때 사용하는
			// 테스트용 IP 주소
			Socket socket = new Socket("127.0.0.1", 30000);
			System.out.println(socket);
			
			// 데이터를 받는다.
			// 스트림 추출
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			int data1 = dis.readInt();
			double data2 = dis.readDouble();
			boolean data3 = dis.readBoolean();
			String data4 = dis.readUTF();
			
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
			System.out.println(data4);
			
			// 서버로 데이터를 보낸다.
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			dos.writeInt(200);
			dos.writeDouble(22.22);
			dos.writeBoolean(false);
			dos.writeUTF("클라이언트가 보낸 문자열");
			
			// 접속해제
			socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}







