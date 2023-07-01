package com.test.main;

import com.test.zoo.ZooClass;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 동물원 객체를 생성한다.
		ZooClass zooClass = new ZooClass();
		
		// 입력받은 동물의 타입 번호
		int typeNumber = 0;
		
		// 입력 받는 동물 번호가 0이 아닌 동안 반복한다.
		do {
			typeNumber = zooClass.inputAnimalType();
			
			// 동물 타입 번호가 0이 아니라면...
			if(typeNumber != 0) {
				// 동물 객체를 담아준다.
				zooClass.addAnimal(typeNumber);	
			}
			
		} while(typeNumber != 0);
		
		// 입력한 동물들의 정보를 출력한다.
		zooClass.printAnimalInfo();
	}
}
