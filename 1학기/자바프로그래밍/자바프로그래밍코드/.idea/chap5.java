public class chap5{

    //상속 -> extends로 선언
    // 부모 클래스 = super class
    //자식 클래스 = sub class
    static class Point{   // static 추가
        int x = 0;
        int y = 0;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        protected void setdata(){
            this.x = 2; 
            this.y = 3;
            System.out.println(x + y);
        }

        private void changedata(int x, int y){  //얘는 서브라도 안됨
            this.x = x;
            this.y = y;
        }
    }

    //서브 클래스 객체는 슈퍼 클래스 객체의 맴버 포함함
    static class ColorPoint extends Point{   
        public ColorPoint(){
            super(0, 0);   // 부모 생성자 호출 필수
            setdata();
        }
    }

    //자바 상속의 특징
    // 다중상속 불가능 (클래스 기준)
    // 근데 서브 클래스라도 슈퍼 클래스의 private는 상속 못함
    // 디폴트나 public이나 protected는 같은 패키지면 다 되고, protected는 패키지랑 상관없이 서브면 다 됨

    //생성자 순서
    // 슈퍼가 가장 먼저 실행되고, 그다음 그 서브들이 실행됨 -> 그 클래스의 슈퍼가 가장 우선

    //super() 로 슈퍼 클래스의 생성자 명시적 선택하기
    static class Point2 extends Point{   // static + 대문자 수정
        Point2(){
            super(23,53);
        }
    }

    public static void main(String[] args){   // static 추가
        Point x = new Point(1, 2);   // 생성자 맞게 수정
        ColorPoint y = new ColorPoint();
    }
}