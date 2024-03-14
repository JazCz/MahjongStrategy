import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class split {
    int pairnum;
    int singlenum;
    int typenum;
    String seq;
    String seqcor;
    String s;
    StringBuilder sb1;
    StringBuilder sb2;
    ArrayList<String> lsp1;
    ArrayList<String> lsp2;
    ArrayList<Integer> ilsp1;
    ArrayList<Integer> ilsp2;
    public split(){
    }
    public split(String seq, String seqcor,String s)
    {
        this.s=s;
        this.seq=seq;
        this.seqcor=seqcor;
        pairnum=read(seqcor,-1);
        singlenum=read(seqcor,1);
        typenum=pairnum+singlenum;
        sb1=new StringBuilder();
        sb2=new StringBuilder();
        lsp1=new ArrayList<>();
        lsp2=new ArrayList<>();
        ilsp1=new ArrayList<>();
        ilsp2=new ArrayList<>();
    }
    public int read(String seqcor,int mode)
    {
        if(mode==-1)
            return seqcor.charAt(0)-'0';
        if(mode==1)
            return seqcor.charAt(seqcor.length()-1)-'0';
        return -1;
    }
    //mode=-1时返回pair的个数//
    //mode=1时返回单张的个数//
    //否则返回异常值-1//
    public void checknmodify(){
        for (int i = 0; i < lsp1.size(); i++) {
            for (int j = 0; j < lsp2.size(); j++) {
                if(Objects.equals(lsp1.get(i), lsp2.get(j))&&i!=j)
                {
                    lsp2.remove(lsp2.get(j));
                    lsp1.remove(lsp1.get(j));
                }
            }
        }
    }
    public void getnum(){
        for (int i = 0; i < lsp1.size(); i++) {
            String s1=lsp1.get(i);
            String s2=lsp2.get(i);
            for (int j = 0; j <s1.length(); j++) {
                ilsp1.add(readnum(getsingle(s1,j)));
                ilsp2.add(readnum(getsingle(s2,j)));
            }
        }
    }
    public int readch(char ch){
        return 2*(ch-'A')+1;
    }//返回字符ch在seqcor的索引
    public int readnum(char ch){
        return getsingle(seqcor,readch(ch)+1)-'0';
    }//返回字符ch在seqcor代表的值//
    public int findmin(int a, int b)
    {
        return Math.min(a, b);
    }
    public char getsingle(String s,int index){
        return s.charAt(index);
    }
    //确保每次只获取单个字符//
    public int getvalsin(String s,int index){return s.charAt(index)-'0';}
    //该方法只对0~9有效//
    public int whichn0(int numx,int numb)
    {
        if(numx!=0)
            return numx;
        else
            return numb;
    }
    public int[] typeint(int[] arr){
        int[] pos=new int[9];
        int[] out=new int[9];
        for (int k : arr) {
            pos[k - 1]++;
        }
        int j=0;
        for (int i = 0; i < pos.length; i++) {
            if (pos[i]!=0)
            {
                out[j]=i+1;
                j++;}
        }
        int[] outn=new int[j];//把多余的0全部清空//
        System.arraycopy(out, 0, outn, 0, outn.length);
        return outn;
    }
    //输出不重复出现的数字数组//
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        Set<T> set = new HashSet<>();
        ArrayList<T> resultList = new ArrayList<>();

        for (T element : list) {
            if (!set.contains(element)) {
                set.add(element);
                resultList.add(element);
            }
        }

        return resultList;
    }
    public static String removeDuplicates(String str, String targetChars) {
        StringBuilder sb = new StringBuilder();
        Set<Character> charsToRemove = new HashSet<>();
        boolean[] removed = new boolean[str.length()];
        boolean[] removet =new boolean[targetChars.length()];
        for (int i = 0; i < targetChars.length(); i++) {
            charsToRemove.add(targetChars.charAt(i));
        }
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);//读入每个字符//
            if (charsToRemove.contains(currentChar)) {
                for (int j = 0; j < targetChars.length(); j++) {
                    char curtargetchar=targetChars.charAt(j);
                    if (!removed[i]&&!removet[j]&&currentChar==curtargetchar) {
                        removed[i] = true;
                        removet[j]=true;
                        break;
                    }
                    else {
                        continue;
                    }
                }
            }
        } for (int j = 0; j < removed.length; j++) {
            if (!removed[j])
                sb.append(str.charAt(j));
        }
        return sb.toString();
    }
}
