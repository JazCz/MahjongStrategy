import java.util.ArrayList;
import java.util.Scanner;
public class inrec {
    public static String input(){
        System.out.println("请输入当前手牌:");
        Scanner sc=new Scanner(System.in);
        return sc.next();
    }
    public static boolean check(ArrayList<Integer> list){
        int[]pos=new int[9];
        for (Integer integer : list) {
            pos[integer-1]++;
        }
        for (int i = 0; i <=6; i++) {
            if(pos[i]*pos[i+1]*pos[i+2]!=0||pos[i]==3)
                return false;
        }
        return true;
    }
    public static ArrayList<Integer> trans(String input){
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<input.length();i++)
        {
            char ch=input.charAt(i);
            if(ch>='0'&&ch<='9')
            {
                list.add(ch-'0');
            }
        }
        return list;
    }//集合存入纯数字//
    public static String recognize(String input,int mode) {
        ArrayList<Integer> in=inrec.trans(input);
        if (check(in))
        {
        int[] pos=new int[9];
        int num1=0;
        int num2=0;
        char s='A';
        StringBuilder sb=new StringBuilder();
            for (Integer integer : in) {
                pos[integer - 1]++;
            }
        for (int po : pos) {
            if (po == 1)
                num1++;
            if (po == 2)
                num2++;
        }
        for (int i = 1; i <= num2 ; i++) {
            sb.append(s).append(s);
            s=(char)(s+1);
        }
        for (int i = 1; i <=num1; i++) {
            sb.append(s);
            s=(char)(s+1);
        }
        if(mode==1)
            return sb.toString();
        if(mode==0)
            return input;
        }
        return "当前手牌存在刻子或顺子";
    }
    public static String corres(String input)
    {
        String rec=recognize(input,1);
        String in=recognize(input,0);
        ArrayList<Integer> list= trans(in);
        int[] pos=new int[9];
        int j=0;
        int num1=0;
        int num2=0;
        for (Integer integer : list) {
            pos[integer - 1]++;
        }
        StringBuilder result= new StringBuilder();
        for (int i = 1; i <=9; i++) {
            if(pos[i-1]==2)
            {
                char get=rec.charAt(j);
                result.append(get).append(i);
                j+=2;
                num2++;
            }
        }
        for (int i = 1; i <=9 ; i++) {
            if(pos[i-1]==1)
            {
                char get=rec.charAt(j);
                result.append(get).append(i);
                j+=1;
                num1++;
            }
        }
        result.insert(0,num2);
        result.append(num1);
        return result.toString();
    }
    //获得result序列,输出格式如下:xAaBbCcDdEey,其中形如Aa表示字母A和数字a的对应关系//
    //x表示该序列中有几个对子,y表示该序列中有几个单张//
}
