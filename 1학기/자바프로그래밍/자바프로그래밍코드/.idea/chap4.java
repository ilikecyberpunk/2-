public class chap4 {  
    //public = 클래스 접근 권한
    // class  = 클래스 선언
    // 그 다음 클래스 이름

    //필드
    int radius;
    String name;

    //메소드
    public double getArea(){
        return 3.14*radius*radius;
    }

    //생성자
    public chap4(){
        radius = 1;  //초기값 설정
        name = "circle1";
        System.out.println("radius = " + radius + "\n" + "name = " + name);

    }

    //매개 변수를 가진 생성자 
    public chap4(int i, String j){
        radius = i;
        name = j;
        System.out.println("radius = " + radius + "\n" + "name = " + name);
    }

    //생성자가 없어도 선언하면 생성자를 자동으로 만들어 주지만, 만약 매개 변수를 가진 생성자가 존재한다면 자동으로 생성자가 생성되지 않는다

    //만들어봐야겠다1
    public class monster{
        int stamina;
        int attackdamage;

        public monster(int i, int j){
            stamina = i;
            attackdamage = j;
        }

    }

    public class for_this{

        //클래스의 맴버변수랑 생성자 매개변수랑 이름이 같으면?
        //객체 자신에 대한 레퍼런스인 this활용

        int a;

        public for_this(){
            this.a = 0;
        }

        public for_this(int a){
            this.a = a;   //이러면 맴버변수 a가 매개변수 a를 대입
        }
    }

    public static void main(String[] args){
        chap4 a = new chap4(132, "ㅅㅂ");

        monster p = a.new monster(32,34);

        
        
    }   

}