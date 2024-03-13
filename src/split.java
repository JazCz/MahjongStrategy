import java.util.ArrayList;

public class split {
    String seq;
    String seqcor;
    StringBuilder sb1;
    StringBuilder sb2;
    ArrayList<String> lsp1;
    ArrayList<String> lsp2;
    ArrayList<Integer> ilsp1;
    ArrayList<Integer> ilsp2;
    public split(){
    }
    public split(String seq, String seqcor)
    {
        this.seq=seq;
        this.seqcor=seqcor;
    }
    public int read(String seqcor,int mode)
    {
        if(mode==-1)
            return seqcor.charAt(0)-'0';
        if(mode==1)
            return seqcor.charAt(seqcor.length()-1)-'0';
        return -1;
    }
}
