### 내부클래스
- 외부에서 안쪽클래스를 사용하려면, 바깥클래스의 객체를 사용하여 안쪽클래스를 객체화한 후에야 가능.
- 안쪽클래스에서는 바깥클래스의 변수와 메소드에 직접 접근 가능. but 바깥클래스에서 안쪽클래스에 접근하려면 반드시 바깥클래스에서 안쪽클래스를 객체화한 후에 가능.
```java  
public class Outter {
     int i;
     public Outter() {
          System.out.println("바깥클래스 생성자");
     }
     public void outterMethod() {
          System.out.println("바깥클래스의 메서드");
     }
     //=========Inner Class의 시작==========
     public class Inner {
          int j;
          public Inner() {
               System.out.println("안쪽클래스의 생성자");
          }
          public void innerMethod() {
               System.out.println("안쪽클래스의 메서드");
               System.out.println("안쪽메서드 바깥변수 i=" + i);
          }
     }

     public static void main(String[] args) {
          Outter out = new Outter();
          out.i = 10;
          out.outterMethod();
          //out.j = 10; //(X) 안쪽 클래스의 변수는 직접 사용 불가능
          //out.innerMethod(); //(X) 안쪽 클래스의 메서드는 직접 사용 불가능

          //즉, 바깥클래스에서 안쪽클래스에 접근하려면 안쪽클래스를 객체화 해서 써야함
          Outter.Inner in = out.new Inner(); //-> 안쪽 클래스의 자원 사용
          in.j = 100;
          in.innerMethod();
     }
}
```  
### 중첩클래스
- 안쪽클래스를 Static으로 둔 경우
- 안쪽클래스를 객체화 하지 않고 사용
```java  
public class Outter {
     int i;
     static int j;
     public Outter(){
          System.out.println("바깥클래스의 생성자");         
     }
     public void outterMethod(){
          System.out.println("바깥글래스 인스턴스메서드");         
     }    
     public static void outterStaticMethod(){
          System.out.println("바깥클래스 스태틱메서드");
     }

     //=========중첩 Class의 시작==========
     static class Nested{
        static int k;
        public Nested(){
           System.out.println("안쪽클래스생성자");
        }  
        public static void innerMethod(){
           //i=10; //(X) static영역에서는, 있을 수도 있고 없을 수도 있는 instance영역꺼 가지고 뭘 못함.
           Outter.j = 10; //(O) (j = 10;) 하지만 같은 static한 필드나 메소드에는 접근 가능.
           //outterMethod(); //(X) 
           Outter.outterStaticMethod(); //(O) (outterStaticMethod();)
        }  
     }

     public static void main(String[] args) {
          //안쪽클래스 사용은 걍 클래스 이름불러 쓰면됨 <-static클래스니까
          Nested.k=10;
          Nested.inMethod();
     }
}
```  
### 지역클래스  
- 메소드 안에서 쓰려고 만든 클래스  
- 지역클래스는 지역변수, parameter중에서 final이 붙은 것만 사용할 수 있다.
```java  
public class Outter {
     int i;
     public Outter(){
          System.out.println("바깥 클래스의 생성자");
     } 
     public void method(int a, final int b){
          int j = 0;
          final int k = 0;         
          //==========지역클래스 시작=============
          class Local{
             int local_i;
             public Local(){
                  System.out.println("지역클래스의 생성자");                 
             }
             public void localMethod(){
                  System.out.println("바깥클래스의 인스턴스변수" + i);
                  //메소드에서 파라미터로 받은걸 여기다가 씀
                  //System.out.println("파라메터a = " + a); //(X) final이 붙어있지 않은 파라메터는 지역클래스에서 사용할 수 없다.
                  System.out.println("파라메터b = " + b); 
                  //System.out.println("지역변수j = " + j); //(X) final이 붙어있지 않은 필드는 지역클래스에서 사용할 수 없다.
                  System.out.println("지역변수k = " + k);
                  System.out.println("지역클래스의 메소드");                 
             }
        }      
        Local l = new Local(); //메소드안에서의 로컬클래스는 메소드안에서 객체생성해 써먹어야함
        l.local_i = 10;
        l.localMethod();                
     }//여기까지가 Outter클래스의 메소드였음~~
  
  public static void main(String[] args) {         
          Outter o = new Outter();
          o.method(100,200);              
     }
}  
```  
### 익명클래스
- java 수업시간에 다루었음
