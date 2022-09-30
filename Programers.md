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
