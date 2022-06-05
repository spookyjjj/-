### 입출력과 사칙연산
- 25083 새싹  
  \뒷부분과 닫는" 혹은 닫는' 은 문자로 인식되는것 보다 명령이라고 인식되는게 먼저.  
  따라서 문자로서 넣은거라고 앞에\ 붙여줘서 알려줘야함  
  ```java  
  System.out.println(" \. ". L_r'"); //이러면 지혼자 괄호 열고닫고, \뒤의.는 뭔가요 물어봐삿고 난리
  System.out.println(" \\. \". L_r'");  
  ```  
  
### 반복문  
- 10871 X보다 작은 수  
  지금은 콘솔창에서 입력과 출력을 같이 하고 있지만, 실제론 입력 스트림과 출력 스트림이 별개라는것!  
  
  
  문제의 예제 출력(1 4 2 3)처럼 되기 위해서 작은수들 저장해놨다 한 번에 출력하지 않고,
  ```java  
  String l = ""; //문자열 박스 만들어서  
  l = l + num + " "; //x보다 작은 num이면 결합해서 박스에 넣고  
  System.out.println(l); //마지막에 한번에 그 문자열 출력하는 흐름  
  ```  
  x보다 작은 num 나올때마다 출력하더라도 ok란 소리   
  ```java  
  System.out.print(num + " "); //다만 콘솔창에서는 입출력이 같이 일어나서 보이기에는 예제출력과 안 같음  
  ```
- 10952 A+B - 5  
    ```java  
  while (a != 0 || b != 0) {
			a = scan.nextInt();
			b = scan.nextInt();
			if (a + b == 0) {
				continue;
			}
			System.out.println(a + b);
		} //a와 b가 0이 아닌한 계속한다!! 이것보다는
  while (true) {      
			int a = scan.nextInt();
			int b = scan.nextInt();
			if (a == 0 && b == 0) {
				break;
			}
			System.out.println(a + b);
		}  // a와 b가 0이면 끝난다(break)!! 이게 더 깔끔하니 ★이런식으로 생각하기~!!
    ```
- 10951 A+B - 4  
  End Of File(EOF): 몇개의 입력이 들어오는지 모르는 문제에서 사용  
  
  Scanner에서는 .hasNextInt()로 처리함. 입력이 있으면 true이므로 반복문 실행O, 없으면 false이므로 반복문 실행X
    
    
### 문자열
- 11720 숫자의 합  
  long으로도 double로도 표현이 힘든 숫자는 아예 문자열로 취급해서 해결
  ```java
  System.out.println((int) '0'); //48  
  System.out.println((int) '1'); //49  
  ...
  System.out.println((int) '9'); //57  
  ```  
- 10809 알파벳 찾기  
  ```java  
  System.out.println((int) 'a'); //97  
  ...
  System.out.println((int) 'z'); //122  
  ```
- 자릿수에 있는 정수 합산하는 방법  
  - charAt사용해서 문자로 불러온 후, 그걸 또 정수로 바꿔서 더하고 하는게 아니라  
  - for문 사용  
  ```java  
  int num = 정수;
  int sum = 0;
  for =(int i = 0; i < 자리수; i++) {
  sum += num % 10;
  num = num / 10;
  }
  ```  
  
