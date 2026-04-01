class chap4_1 {
    //this is for array class

    public chap4_1(int index){
        System.out.println("this is class chap4_1 file - index: " + index);
    }

    public static void main(String[] args){
        chap4_1[] c = new chap4_1[5];
        for (int i = 0; i < 5; i++){
            c[i] = new chap4_1();
        }
    }
}
