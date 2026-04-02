class chap4_1 {
    //this is for array class

    public chap4_1(int num){
        System.out.println("this is class chap4_1 " + num);
        
        

        
    }

    //기본 타입이 전달되는 메서드 
    public static void increase1(int m){
        m = m+1;
        System.out.println(m);
    }

    //객체가 전달되는 메서드(ㅅㅂ)
    public static void increase2(Circle n){
        n.radius++;
        System.out.println(n.radius);
    }

    //배열이 전달되는 메서드(롯같다
    //이것도 call by reference와 같은 논리

    public static void increase3(int[] array){
        for (int i = 0; i < array.length; i++){
            array[i] ++;
            System.out.print(array[i]);
        }
        
    }

    public static class Circle {
        int radius;     

        public Circle(int radius) {
            this.radius = radius;
        }
    }

    //오버로딩
    // 한 클래스 내에서 이름이 같은 메소드 -- 오버라이드랑 다르다 !!
    public static int getsum(int a, int b){
        return a+b;
    }
    public static int getsum(int a, int b, int c){
        return a+b+c;
    }
    public static int getsum(int a, int b, int c, int d){
        return a+b+c+d;
    }
    
    // 이렇게 매개변수의 개수와 타입이 같은 놈은 오버로딩 실패됨
    // public static int getsum(int a, int b){
    //     return a+b;
    // }
    //

    

    

    //객체 배열
    public static void main(String[] args){
        chap4_1[] c = new chap4_1[5];
        for (int i = 0; i < 5; i++){
            c[i] = new chap4_1(i);
            
        }

        // / 인자 전달 –기본 타입의 값이 전달되는 경우
        //  call by value
        //  값만 복사 → 원본은 그대로.
        int shi = 200;
        increase1(shi);

        
        // 인자 전달 –객체가 전달되는 경우
        // call by refernce
        // 원본 주소를 전달 → 함수 안에서 바꾸면 원본도 바뀜
        Circle pizza = new Circle(10);   // radius = 10으로 객체 생성
        increase2(pizza);                
        System.out.println(pizza.radius); // 11

        //배열전달 - 배열 통으로 전달 x 레퍼런스만ㅇㅇ
        int shit[] = {1,2,3,4};
        increase3(shit);

        System.out.println();
        //오버로딩
        System.out.println(getsum(1,2));
        System.out.println(getsum(1,2,3));
        System.out.println(getsum(1,2,3,4));



        
    }


}
