package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
	String name;
	int score;
	
	Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

public class JavaTest_박미진 {
	public static void main(String[] args) {
		// 1. 서술형
		// - 기본 데이터 타입: 기본 데이터 타입은 선언하는 값 그대로 내용이 저장되며,
		//					종류로는 bool, char, int, double 등이 있습니다.
		//					예시로는 int num = 3; 변수 선언이 가능합니다.
		
		// - 참조 데이터 타입: 참조 데이터 타입은 데이터가 저장된 주소가 저장됩니다.
		//					종류로는 String, Integer, Double 등이 있고,
		//					예시로는 String str = "안녕하세요 :)" 변수 선언이 가능합니다.
		
		// 2. 코드 구현 문제
		int num1 = 10;
		double num2 = 3.5;
		
		double result = num1 + num2;
		
		System.out.println(result);
		
		// 3. 코드 구현 문제
		Scanner sc = new Scanner(System.in);
		
		int number = sc.nextInt();
		
		if (number % 2 == 0) {
			System.out.println("입력하신 숫자는 짝수입니다.");
		} else {
			System.out.println("입력하신 숫자는 홀수입니다.");
		}
		
		sc.close();
		
		// 4. 코드 구현 문제
		int[] numbers = {1, 2, 3, 4, 5};
		
		for (int num: numbers) {
			System.out.print(num + " ");
		}
	
		System.out.println();
		
		// 5. 코드 구현 문제
		/*
		 * 1. `Student`라는 클래스를 작성합니다.
    		- `name` (이름, 문자열 타입)과 `score` (점수, 정수 타입) 필드를 가집니다.
    		- `Student` 객체를 생성할 때 이름과 점수를 초기화할 수 있는 생성자를 만듭니다.
    	2. `Student` 객체 배열을 생성하여 학생 5명의 이름과 점수를 초기화합니다.
    		- 예: 학생 이름: "John", "Jane", "Tom", "Emily", "Alex" / 점수: 85, 92, 78, 88, 95
		3. 반복문을 사용하여 점수가 90점 이상인 학생의 이름을 출력합니다.
		 */
		
		List<Student> students = new ArrayList<>();
		students.add(new Student("John", 85));
		students.add(new Student("Jane", 92));
		students.add(new Student("Tom", 78));
		students.add(new Student("Emily", 88));
		students.add(new Student("Alex", 95));

		for (Student student: students) {
			if (student.score >= 90) {
				System.out.println(student.name);
			}
		}
		
		// 6. 단답형
		// 정답: Object
		
		// 7. 단답형
		// 정답: length
		
		// 8. 단답형
		//정답: 삼항연산자 ( [조건]? [참일 경우]: [거짓일 경우] )
		
		// 9. 3
		
		// 10. 2
		
		// 11. Child
		
		// 12. 1
		
		// 13. 2
		
		// 14. Animal 클래스가 Dog 클래스의 부모 클래스여야 한다. 
		// 		class Dog extends Animal
		
		// 15. 2
		
		// 16. 3
		
		// 17. public
		
		// 18. 3
		
		// [추가 질문] 람다 표현식 파트부터 이해하기가 어려워요ㅠㅜ 만약 이후에 추가(보완) 수업이 있다면 참석하고 싶습니다 :)
	}
}
