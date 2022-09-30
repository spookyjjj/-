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
