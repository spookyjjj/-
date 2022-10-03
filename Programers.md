### JadenCase 문자열 만들기
"3people unFollowed me" -> "3people Unfollowed Me"
```java 
public String solution(String s) {
  String answer = "";
  String[] sp = s.toLowerCase().split("");
  boolean flag = true;
  
  for(String ss : sp) {
    answer += flag ? ss.toUpperCase() : ss;
    flag = ss.equals(" ") ? true : false;
  }
  return answer;
}
```
- 숫자/공백도 .toLowerCase(), .toUpperCase()가 먹는다
- split("")와 split(" ")는 다르다 -> 전자는 글자 하나하나 나누기 / 후자는 덩어리로 나누기
- boolean을 스위치로 사용하자
----
### 최솟값 만들기
- int배열 생성
  - int[] a = new int[길이];
  - int[] a = {값, 넣, 기, 변수도ok};
- 배열의 정렬은 Arrays.sort
- 리스트의 정렬은 Collections.sort
----
### 이진 변환 반복하기
- 이진수를 문자열로 나타내기 : Integer.toBinaryString(int값)
----
### 올바른 괄호
- )를 찾아서 바로 앞이 (이면 ()세트로 삭제하는 방법으로 접근
  - 문자열에서 특정형태 삭제하기 : `s.replaceAll("\\(\\)", "")` <- 정규표현식이라 \\가 필요
  - 문자열에서 인덱스 시작점 끝점으로 삭제하기 : StringBuffer의 `.delete(str인덱스, end인덱스)`
- 너무 오래걸려서 stack을 사용
  - for문돌려서 '('이면 push하고, ')'이면 pop해서(후입선출) 짝맞추기!
- 똑같은 논리로 int를 사용하면 다음과 같다
```java
int count = 0;
for (int i = 0; i < s.length(); i++) {
    if(s.charAt(i) == '(') {
      count++;
    }
    if(s.charAt(i) == ')') {
      count--;
    }
    if (count < 0) {
      return false;
    }
}
if (count > 0) {
    return false;
} 
```
- 문자열의 비교
  - String이면 `문자열1.equals("문자열2")`
  - char이면 `글자1=='글자2'` <- ★따라서 **string.charAt(int)=='a'**
----
### 숫자의 표현
```java
for (int i = 1; i <= (n / 2); i++) { //절반까지만 테스트 하면 됨 
    for (int j = (i + 1); j <= ((n + 1) / 2); j++) { //마찬가지로 절반 바로 다음까지만 
        int sum = ((i + j) * (j - i + 1)) / 2; 
        if (sum > n) break;
        if (sum == n) answer++;
    }
}
```
----
### 피보나치수
- 피보나치는 44번째 피보나치 수만 가도 2,971,215,073로 int 범위를 넘어버린다.
- 그래서 문제에서 1234567로 나눈 나머지를 정답으로 내놓으라 한 것! -> int 자료형의 범위 내에서 처리되게
- `(A + B) % C ≡ ( ( A % C ) + ( B % C) ) % C`라는 성질 이용 -> 계산 결과에 1234567으로 나눈 나머지를 대신 넣으면 됨
```java
for (int i = 1; i < n; i++) {
    tem = (a + b) % 1234567;
    a = b;
    b = tem;
}
```
----
### 다음 큰 숫자
- `Integer.bitCount(int)` : int를 이진수로 바꿔서 1의 개수를 카운트 해주는 함수
---- 
### 카펫
- y + b = 가로 * 세로, b = (가로 + 세로 - 2) * 2
- y와 b의 값이 주어졌을 때 가로와 세로를 구하는 연립방정식
- 인수들 찾아내서 진행하는 완전 탐색으로 풀어도 되지만,
- 가로 혹은 세로를 변수로 놓고 이차방정식을 풀어도 됨
----
### 제일 작은 수 제거하기
- ★배열
  - min구하기 `Arrays.stream(arr).min().getAsInt()` : 느림
  - sort `Arrays.sort(arr)`
  - 값으로 특정 인덱스 제거 `Arrays.stream(arr).filter(i -> i != min).toArray()` : 느림
  - 인덱스로 제거 X : 배열은 칸 고정이라서 한칸씩 땡겨서 덮어 씌울 수 밖에..
```java
int[] answer = new int[arr.length-1];
int minIndex=0;

for(int i=0;i<arr.length;i++){
    if(arr[minIndex]>arr[i]){
        minIndex = i;
    }
}
for(int i=minIndex+1;i<arr.length;i++){ //한칸씩 땡겨서 덮어 
    arr[i-1] = arr[i];
}
for(int i=0;i<answer.length;i++){
    answer[i] = arr[i];
}
```
- ★리스트
  - min구하기 `Collections.min(list)` : Integer로 반환
  - sort `Collections.sort(list)`
  - 값으로 특정 인덱스 제거 `list.remove(min)` : Integer(객체)를 넘겨줌
  - 인덱스로 제거 : `list.remove(index)` : int를 넘겨줌
```java
ArrayList<Integer> arrayList = new ArrayList<Integer>();
 for (int a : arr) {
    arrayList.add(a);
}
Integer minimum = Collections.min(arrayList);
arrayList.remove(minimum);
int[] resultArray = new int[arr.length - 1];
for (int i = 0; i < arrayList.size(); ++i) {
    resultArray[i] = arrayList.get(i);
}
````
----
### 탐욕법(Greedy) 체육복
- 풀어라... 정렬의 필요성?
``` JAVA
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
		int answer = n - lost.length;
		boolean step = true;
		for (int i = 0; i < lost.length; i++) {
			for (int j = 0; j < reserve.length; j++) {
				if (lost[i] == reserve[j]) {
					reserve[j] = -1;
					answer++;
					step = false; //더이상 볼 필요없다
					break;
				}
			}
			if (step) {
				for (int j = 0; j < reserve.length; j++) {
					if (lost[i] == (reserve[j] - 1) || lost[i] == (reserve[j] + 1)) {
						reserve[j] = -1;
						answer++;
						step = false; //더이상 볼 필요없다
						break;
					}
				}
			}
			if (step) {
				for (int j = 0; j < reserve.length; j++) {
					if (lost[i] == (reserve[j] + 1)) {
						reserve[j] = -1;
						answer++;
						break;
					}
				}
			}
			step = true;
		}
        return answer;
    }
}
//입력값 〉	5, [4, 2], [3, 5]
//기댓값 〉	5
//실행 결과 〉	실행한 결괏값 4이 기댓값 5과 다릅니다.
``` 
