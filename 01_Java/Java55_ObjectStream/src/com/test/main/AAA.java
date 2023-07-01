package com.test.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class AAA{
	public static void main(String [] args) {
		
		SchoolClass schoolClass = new SchoolClass();
		
		while (true) {
			int menuNumber = schoolClass.inputMenu();
			
			if (menuNumber == 1) {
				schoolClass.inputStudentInfo();
			}
			else if(menuNumber == 2) {
				schoolClass.printStudentInfo();
			}
			else if(menuNumber == 3) {
				break;
			}
			
		}
	}
}

class SchoolClass{
	
	Scanner scanner;
	
	FileInputStream fis = null;
	FileOutputStream fos = null;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	
	public SchoolClass() {
		scanner = new Scanner(System.in);
	}
	
	public int inputMenu() {
		System.out.println("1. 학생정보 입력");
		System.out.println("2. 학생정보 출력");
		System.out.println("3. 종료");
		System.out.print("메뉴 번호 : ");
		int menuNumber = scanner.nextInt();
		return menuNumber;
	}
	
	public void inputStudentInfo() {
		try {
			if(fos == null) {
				fos = new FileOutputStream("test1000.dat", true);
				oos = new ObjectOutputStream(fos);
			}
			System.out.print("이름 : ");
			String name = scanner.next();
			System.out.print("학년 : ");
			int grade = scanner.nextInt();
			
			TestClass100 t1 = new TestClass100(name, grade);
			
			oos.writeObject(t1);;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printStudentInfo() {
		try {
			if(fis == null) {
				fis = new FileInputStream("test1000.dat");
				ois = new ObjectInputStream(fis);
			}
			
			while (true) {
				TestClass100 t200 = (TestClass100) ois.readObject();
				t200.printStudentInfo();
			}
		}catch(EOFException e) {
			// e.printStackTrace();
			System.out.println("종료");
		}catch(Exception e) {
			// e.printStackTrace();
			
		}
	}
}

class TestClass100 implements Serializable{
	String name;
	int grade;
	
	public TestClass100(String name, int grade){
		this.name = name;
		this.grade = grade;
	}
	
	public void printStudentInfo() {
		System.out.printf("이름 : %s\n", name);
		System.out.printf("학년 : %d\n", grade);
	}
}