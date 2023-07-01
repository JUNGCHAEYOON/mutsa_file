package com.test.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 2byte stream
		// 자바는 글자 하나를 2 byte를 사용하기 때문에
		// 문자열 입출력 전용으로 사용하는 스트림들을 2byte stream이라고 부른다.
		// 라인단위 입출력이 가능하고 문자열 인코딩 설정이 가능하다.
		
		//saveData();
		//printData();
		//loadData();
		keyboardInput();
	}

	public static void saveData() {
		try {
			// 기본 스트림 생성
			FileOutputStream fos = new FileOutputStream("test4.txt");
			// OutputStreamWriter
			// 문자열 인코딩을 설정하는 필터 스트림
			OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			// 출력 버퍼를 사용하는 필터 스트림
			BufferedWriter bw = new BufferedWriter(writer);
			// println, print, printf 를 사용할 수 있는 필터 스트림
			PrintWriter pw = new PrintWriter(bw);
			
			// 파일에 쓴다.
			pw.print("안녕하세요");
			pw.println("반갑습니다");
			pw.printf("이름은 %s이고, 나이는 %d살 입니다\n", "홍길동", 30);
			
			pw.flush();
			pw.close();
			bw.close();
			writer.close();
			fos.close();
			
			System.out.println("쓰기 완료");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printData() {
		try {
			// 기본 출력(터미널)에 인코딩을 설정할 수 있도록 작업한다.
			OutputStreamWriter writer = new OutputStreamWriter(System.out, "UTF-8");
			BufferedWriter bw = new BufferedWriter(writer);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.print("안녕하세요");
			pw.println("반갑습니다");
			pw.printf("이름은 %s이고, 나이는 %d살 입니다", "홍길동", 30);
			
			pw.flush();
			pw.close();
			bw.close();
			writer.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadData() {
		try {
			FileInputStream fis = new FileInputStream("test4.txt");
			// 인코딩 설정
			InputStreamReader irs = new InputStreamReader(fis, "UTF-8");
			// 입력 버퍼 사용
			BufferedReader br = new BufferedReader(irs);
			
			// 읽어온다.
			String str1 = null;
			StringBuffer buffer = new StringBuffer();
			
			do {
				// 한 줄을 읽어온다.
				str1 = br.readLine();
				if(str1 != null) {
					buffer.append(str1 + "\n");
				}
			} while(str1 != null);
			
			br.close();
			irs.close();
			fis.close();
			
			// StringBuffer의 문자열로 String을 만든다.
			String result = buffer.toString();
			System.out.println(result);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void keyboardInput() {
		try {
			InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			System.out.print("입력1 : ");
			String str1 = br.readLine();
			System.out.print("입력 2: ");
			String str2 = br.readLine();
			
			br.close();
			isr.close();
			
			System.out.println(str1);
			System.out.println(str2);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
















