class ClassA{
    protected ClassA(){
        System.out.println("클래스A 기본생성자");
    }

    public ClassA(int x){
        System.out.println("클래스 A 생성자 매개변수 : " + x);
    }
}

class ClassB extends ClassA{
    int z;
    public ClassB(){
        System.out.println("클래스B기본생성자");
    }

    public ClassB(int y, int z){
        super(y);
        this.z = z;
        System.out.println("클래스 B 생성자 매개변수 : " + y +" "+ z);
    }
}

class ClassC{

}

class pracice{
    public static void main(String[] args){
        ClassA a = new ClassB();
        ClassB b = new ClassB();
        ClassC c = new ClassC();

        System.out.println();
        b = (ClassB)a;

        System.out.println(a instanceof ClassA);
        System.out.println(b instanceof ClassA);
        
    }
}