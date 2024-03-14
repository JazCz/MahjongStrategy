import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class split7 extends split{
    public split7(){
        super();
    }
    public split7(String seq, String seqcor,String s) {
        super.seq=seq;
        super.seqcor=seqcor;
        super.s=s;
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
    public void split(){
        int[]arr= new int[typenum];//单次出现的数字数组从小到大//
        int[]pos=new int[typenum];
        int[] pos1=new int[typenum];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=getsingle(seqcor,2*i+2)-'0';
        }
        for (int i = 0; i < arr.length; i++) {
            if(i<pairnum)
            {
                for (int j = 0; j < arr.length; j++) {
                    if(j>=pairnum||j==i)
                        pos[j]=1;
                    for (int k = 0; k < arr.length; k++) {
                        if(pos[k]!=1)
                        {
                            if(arr[j]>=arr[i]&&arr[k]>=arr[j])
                            {
                                sb1.append(arr[i]);
                                sb1.append(arr[j]);
                                sb1.append(arr[k]);
                                pos[k]=1;
                                lsp1.add(sb1.toString());
                                lsp2.add(removeDuplicates(s,sb1.toString()));
                                sb1.setLength(0);
                            }
                        }
                    }
                    Arrays.fill(pos,j+1,arr.length,0);
                }
            }
            else {
                for (int j = i; j < arr.length; j++) {
                    pos[j]=1;
                    for (int k = j+1; k < arr.length; k++) {
                        if(pos[k]!=1)
                        {
                            sb1.append(arr[i]);
                            sb1.append(arr[j]);
                            sb1.append(arr[k]);
                            pos[k]=1;
                            pos1[i]=1;
                            pos1[j]=1;
                            pos1[k]=1;
                            lsp1.add(sb1.toString());
                            sb1.setLength(0);
                            for (int l = 0; l < pos.length; l++) {
                                if (pos1[l]==0)
                                {
                                    if(l<pairnum)
                                        sb2.append(arr[l]).append(arr[l]);
                                    else sb2.append(arr[l]);
                                }
                            }
                            lsp2.add(sb2.toString());
                            sb2.setLength(0);
                            Arrays.fill(pos1, 0);
                        }
                    }
                    Arrays.fill(pos,j+1,arr.length,0);
                }
            }
        }
    }
    //lsp1存放第一组数//
    //lsp2存放第二组数//
    //得到lsp1后直接lsp2.add(removeDuplicates(s,sb1.toString()));即可//
    public void getvalue(){
        split();
        int[] t1=new int[3];
        int[] t2=new int[4];
        int[] t=new int[lsp1.size()];
        int[] val=new int[4];
        int j=0;
        int k=0;
        for (int i = 0; i < lsp1.size(); i++) {
            while (j<=2){
                t1[j]=getvalsin(lsp1.get(i),j);
                j++;
            }//把i组字符串存入数组//
            while (k<=3){
                t2[k]=getvalsin(lsp2.get(i),k);
                k++;
            }//把i组字符串存入数组//
            j=0;
            k=0;
            int[] t1t=typeint(t1);
            int[] t2t=typeint(t2);
            paixing px;
            pairb pb;
            paixingfor4 pxf4;
            pairbfor4 pbf4;
            int p1 = 0,s1 = 0;
            int p2=0,s2=0,s21=0;
            //第一组的情况
            if (t1t.length==2)//pairb//
            {
                for (int l = 0; l < t1t.length; l++) {
                    if (ispair(t1,t1t[l])!=-1)
                        p1=ispair(t1,t1t[l]);
                    else s1=t1t[l];
                }
                pb=new pairb(p1,s1);
                pb.outpaixing();
                val[1]=pb.dropvalue();
            }
            if(t1t.length==3)//paixing//
            {
                px=new paixing(t1[0],t1[1],t1[2]);
                px.outpaixing();
                val[0]=px.dropvalue();
            }
            //第二组的情况
            if(t2t.length==4)
            {
                pxf4=new paixingfor4(t2[0],t2[1],t2[2],t2[3]);
                pxf4.paixu();
                val[2]=pxf4.dropvalue();
            }
            if(t2t.length==3||t2t.length==2)
            {
                for (int l = 0; l < t2t.length; l++) {
                    if (ispair(t2,t2t[l])!=-1)
                        p2=ispair(t2,t2t[l]);
                    else
                        s21=t2t[l];
                }
                for (int l = 0; l < t2t.length; l++) {
                    if(t2t[l]!=p2&&t2t[l]!=s21)
                        s2=t2t[l];
                }
                if(s21!=0)
                    pbf4=new pairbfor4(p2,s2,s21);
                else
                    pbf4=new pairbfor4(p2,s2,s2);
                pbf4.paixu();
                val[3]=pbf4.dropvalue();
            }
            t[i]=findmin(whichn0(val[0],val[1]),whichn0(val[2],val[3]));
            Arrays.fill(val,0);
            System.out.println();
        }
        System.out.println("价值最小的牌型是:");
        int min=0;
        for (int i = 1; i <t.length ; i++) {
            if(t[i]<t[min])
                min=i;
        }
        while (j<=2){
            t1[j]=getvalsin(lsp1.get(min),j);
            j++;
        }//把value最小组字符串存入数组//
        while (k<=3){
            t2[k]=getvalsin(lsp2.get(min),k);
            k++;
        }//把value最小组字符串存入数组//
        //照抄前面的代码//
        //Arrays.fill(val,0);
        int[] t1t=typeint(t1);
        int[] t2t=typeint(t2);
        paixing px;
        pairb pb;
        paixingfor4 pxf4;
        pairbfor4 pbf4;
        int p1 = 0,s1 = 0;
        int p2=0,s2=0,s21=0;
        //第一组的情况
        if (t1t.length==2)//pairb//
        {
            for (int l = 0; l < t1t.length; l++) {
                if (ispair(t1,t1t[l])!=-1)
                    p1=ispair(t1,t1t[l]);
                else s1=t1t[l];
            }
            pb=new pairb(p1,s1);
            pb.outpaixing();
            if(pb.dropvalue()==t[min])
            {
                pb.drop();
            }
        }
        if(t1t.length==3)//paixing//
        {
            px=new paixing(t1[0],t1[1],t1[2]);
            px.outpaixing();
            if(px.dropvalue()==t[min])
            {
                px.drop();
            }
        }
        //第二组的情况
        if(t2t.length==4)
        {
            pxf4=new paixingfor4(t2[0],t2[1],t2[2],t2[3]);
            pxf4.paixu();
            if(pxf4.dropvalue()==t[min])
            {
                pxf4.drop();
            }
        }
        if(t2t.length==3||t2t.length==2)
        {
            for (int l = 0; l < t2t.length; l++) {
                if (ispair(t2,t2t[l])!=-1)
                    p2=ispair(t2,t2t[l]);
                else
                    s21=t2t[l];
            }
            for (int l = 0; l < t2t.length; l++) {
                if(t2t[l]!=p2&&t2t[l]!=s21)
                    s2=t2t[l];
            }
            if(s21!=0)
                pbf4=new pairbfor4(p2,s2,s21);
            else
                pbf4=new pairbfor4(p2,s2,s2);
            pbf4.paixu();
            if(pbf4.dropvalue()==t[min])
            {
                pbf4.drop();
            }
        }
    }
    //在本类中lsp1.get(i)得到的就是第1组的第i情况的字符串//
    public void print() {
        split();
        System.out.println("组1：");
        for (String s : lsp1)
        {
            System.out.print(s+" ");
        }
        System.out.println("组2：");
        for (String s : lsp2) {
            System.out.print(s + " ");
        }
    }
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
}
