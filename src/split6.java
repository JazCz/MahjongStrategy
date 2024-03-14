
import java.util.*;

public class split6 extends split{
    public split6(){
        super();
    }
    public split6(String seq, String seqcor,String s) {
        super.seq=seq;
        super.seqcor=seqcor;
        super.s=s;
        sb1=new StringBuilder();
        sb2=new StringBuilder();
        lsp1=new ArrayList<>();
        lsp2=new ArrayList<>();
        ilsp1=new ArrayList<>();
        ilsp2=new ArrayList<>();
    }
    public void split(){
        int[]pos=new int[6];
        ArrayList<String> rawlsp1=new ArrayList<>();
        ArrayList<String> rawlsp2=new ArrayList<>();
        for (int i = 0; i < seq.length(); i++) {
            for (int j = i+1; j <seq.length(); j++) {
                for (int k = j + 1; k < seq.length(); k++) {
                    sb1.append(getsingle(seq,i));
                    pos[i]=1;
                    sb1.append(getsingle(seq,j));
                    pos[j]=1;
                    sb1.append(getsingle(seq,k));
                    pos[k]=1;
                    rawlsp1.add(sb1.toString());
                    sb1.setLength(0);
                    for (int l = 0; l < pos.length; l++) {
                        if (pos[l]==0)
                        {
                            sb2.append(getsingle(seq,l));
                        }
                    }
                    rawlsp2.add(sb2.toString());
                    sb2.setLength(0);
                    Arrays.fill(pos, 0);
                }
            }
        }
        lsp1=removeDuplicates(rawlsp1);
        lsp2=removeDuplicates(rawlsp2);
    }
    public void print(){
        split();
        checknmodify();
        for (String s : lsp1) {
            System.out.print(s + " ");
        }
    }
    //only for test//
    public void printnum(){
        split();
        checknmodify();
        getnum();
        System.out.println("组1：");
        for (int i = 0; i < ilsp1.size(); i++) {
            if((i+1)%3==0)
                System.out.print(ilsp1.get(i)+", ");
            else
                System.out.print(ilsp1.get(i)+" ");
        }
        System.out.println("组2：");
        for (int i = 0; i < ilsp1.size(); i++) {
            if((i+1)%3==0)
                System.out.print(ilsp2.get(i)+", ");
            else
                System.out.print(ilsp2.get(i)+" ");
        }
    }
    //only for test//
    public void getvalue(){
        split();
        checknmodify();
        getnum();
        int[] t1=new int[3];
        int[] t2=new int[3];
        int j=0;
        int[] t=new int[lsp1.size()];
        int[] val=new int[4];
        for (int i = 0; i < ilsp1.size(); i++) {
            if (j<=2)
            {
                t1[j]=ilsp1.get(i);
                t2[j]=ilsp2.get(i);
                j++;
                //将ilsp分组j读入t1//
            }
            if((i+1)%3==0){
                int[]t1t=typeint(t1);
                int[]t2t=typeint(t2);
                paixing px1;
                pairb pb1;
                paixing px2;
                pairb pb2;
                j=0;
                int p1=-1;
                int p2=-1;
                for (int k1 = 0; k1 < t1t.length; k1++)
                    {
                        p1=ispair(t1,t1t[k1]);
                    }//t==t1t?//
                    if(p1!=-1){
                        for (int l = 0; l < t1t.length; l++) {
                            if(t1t[l]!=p1)
                            {
                                pb1 =new pairb(p1,t1t[l]);
                                pb1.outpaixing();
                                val[1]=pb1.dropvalue();
                            }
                        }
                    }
                    else {
                            px1= new paixing(t1[0],t1[1],t1[2]);
                            px1.outpaixing();
                            val[0]=px1.dropvalue();
                    }
                for (int k2 = 0; k2 < t2t.length; k2++)
                    {
                        p2=ispair(t2,t2t[k2]);
                    }
                    //记录pair//
                    if(p2!=-1){
                        for (int l = 0; l < t2t.length; l++) {
                            if(t2t[l]!=p2)
                            {
                                pb2 =new pairb(p2,t2t[l]);
                                pb2.outpaixing();
                                val[3]= pb2.dropvalue();
                            }
                        }
                    }
                    else {
                            px2=new paixing(t2[0],t2[1],t2[2]);
                            px2.outpaixing();
                            val[2]= px2.dropvalue();
                    }
                t[(i+1)/3-1]=findmin(whichn0(val[0],val[1]),whichn0(val[2],val[3]));
                Arrays.fill(val,0);
                System.out.println();
            }
        }
        System.out.println("价值最小的牌型是:");
        int min=0;
        for (int i = 1; i <t.length ; i++) {
            if(t[i]<t[min])
                min=i;
        }
        int start=(min+1)*3-1-2;
        ArrayList<paixing> listx=new ArrayList<>();
        ArrayList<pairb> listb=new ArrayList<>();
        for (int i = start; i <=start+2; i++) {
            if (j <= 2) {
                t1[j] = ilsp1.get(i);
                t2[j] = ilsp2.get(i);
                j++;
                //将ilsp分组j读入t1//
            }
            if ((i+1)%3==0) {
                int[] t1t = typeint(t1);
                int[] t2t = typeint(t2);
                paixing px1;
                pairb pb1;
                paixing px2;
                pairb pb2;
                int p1=-1;
                int p2=-1;
                for (int k1 = 0; k1 < t1t.length; k1++)
                    {
                        p1 = ispair(t1, t1t[k1]);
                    }
                    if (p1 != -1) {
                        for (int l = 0; l < t1t.length; l++) {
                            if (t1t[l] != p1) {
                                pb1 = new pairb(p1, t1t[l]);
                                listb.add(pb1);
                                pb1.outpaixing();
                                val[1] = pb1.dropvalue();
                            }
                        }
                    }
                    else {
                            px1 = new paixing(t1[0], t1[ 1], t1[2]);
                            listx.add(px1);
                            px1.outpaixing();
                            val[0] = px1.dropvalue();
                    }
                for (int k2 = 0; k2 < t2t.length; k2++)
                    {
                        p2 = ispair(t2, t2t[k2]);
                    }//记录pair//
                    if (p2 != -1) {
                        for (int l = 0; l < t2t.length; l++) {
                            if (t2t[l] != p2) {
                                pb2 = new pairb(p2, t2t[l]);
                                listb.add(pb2);
                                pb2.outpaixing();
                                val[3] = pb2.dropvalue();
                            }
                        }
                    } else {
                            px2 = new paixing(t2[0], t2[1], t2[2]);
                            listx.add(px2);
                            px2.outpaixing();
                            val[2] = px2.dropvalue();
                    }
            }
            int index=0;
            for (int k = 0; k < val.length; k++) {
                if(val[k]==t[min])
                    index=k;
            }
            if(index%2==1&& !listb.isEmpty())
                listb.get((index-1)/2).drop();
            else if(index%2==0&&!listx.isEmpty())
                listx.get(index/2).drop();
        }
    }
    //print和本方法不能同时调用,因为都改变了类的变量
    public int ispair(int[]arr,int pair)
    {
        int[] pos=new int[9];
        for (int j : arr) {
            pos[j - 1]++;
        }
        if (pos[pair-1]==2)
            return pair;
        return -1;
    }
    //ispair只考虑了一个pair的情况,返回这个pair//


}
