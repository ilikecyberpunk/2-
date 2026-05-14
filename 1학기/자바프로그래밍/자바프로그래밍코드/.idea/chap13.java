import java.io.*;

public class chap13 {
    
    public static void main(String[] args) throws IOException {
        //입출력 스트림

        // 입력스트림: 입력 장치 -> 자바 프로그램으로 데이터를 전달하는 객체
        // 출력스트림: 자바 프로그램 -> 출력장치로 데이터를 보내는 객체

        // 입출력 스트림 기본 단위 : 바이트

        //종류 : 문자, 바이트 스트림
        // InputStreamReader rd = new InputStreamReader(System.in);
        // while(true){
        //     int c = rd.read();
        //     if(c == -1){
        //         break;
        //     }
        // }



        //문자 스트림으로 택스트 파일 읽기
        FileReader fin = new FileReader("C:/Users/gtaeh/Desktop/dd.txt");
        int c1;
        while((c1 = fin.read()) != -1){
            System.out.print((char)c1);
        }
        fin.close();

        FileReader f = new FileReader("C:/Users/gtaeh/Desktop/me.txt");
        int c2;
        while((c2 = f.read()) != -1){
            System.out.print((char)c2);
        }
        f.close();

        //파일 입출력 동안 FileNotFoundException일어날 수 있음
        try{
            FileReader f1 = new FileReader("C:/Users/gtaeh/Desktop/me.txt");
            int c3;
            while((c3 = f1.read())!= -1){
                System.out.print((char)c3);
            }
            f1.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }

        
        //문자 스트림으로 텍스트 파일 쓰기
        FileWriter fout = new FileWriter("C:/Users/gtaeh/Desktop/me.txt");
        fout.write('A');

        char[] words = new char[2];
        words[0] = 'y';
        words[1] = 't';
        fout.write(words,0,words.length);
        
        fout.close();

    
        System.out.println();



        //텍스트 파일 쓰기
        System.out.print("입력하세요 : ");
        InputStreamReader in = new InputStreamReader(System.in);
        FileWriter fout1 = null;
        int c3;

        try{
            fout1 = new FileWriter("me.txt");
            if((c3 = in.read()) != -1){
                fout1.write(c3);
            }
            in.close();
            fout1.close();
        }
        catch(Exception e){
            System.out.println(e);
        }


    }
}
