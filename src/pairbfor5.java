public class pairbfor5 extends pairbfor4{
    /*
        pairbfor5是指形如AABBC或AACBB或CAABB的序列
     */
    int insertc;
    public pairbfor5(){
        super();
    }
    public pairbfor5(int pair, int max, int insertc){//pair必须是对子,不区分其他的序//
        super.pair=pair;
        super.max=max;
        this.insertc=insertc;
    }
    public void paixu(){
        int[]pos=sort(pair,max,insertc,1);
        System.out.print("---"+"现在牌型是");
        for(int j=0;j <pos.length;j++) {
            if (pos[j]==insertc)
                if (j==pos.length-1)
                    System.out.print(pos[j]);
                else
                    System.out.print(pos[j] + " ");
            else
            {if (j==pos.length-1)
                System.out.print(pos[j]+" "+pos[j]);
            else
                System.out.print(pos[j] + " "+pos[j]+" ");}
        }
        System.out.print("---");
    }
    public void value(){
        paixu();
        int[]value=getvalue();
        int[]pos=sort(pair,max,insertc,1);
        for (int i = 0; i < pos.length; i++) {
            if (i==pos.length-1)
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+"---");
            else
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+", ");
        }
    }
    public int[] getarr(){
        return sort(pair,max,insertc,0);
    }
    public int[] getvalue(){
        int[]arr=getarr();
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};
        int[]pos=sort(pair,max,insertc,1);
        value[pair-1]+=f5;
        value[max-1]+=f5;
        value[insertc-1]+=f7;
        modifyleft(arr,value,pair);
        modifyright(arr,value,pair);
        modifyleft(arr,value,max);
        modifyright(arr,value,max);
        modifyleft(arr,value,insertc);
        modifyright(arr,value,insertc);
        modifychown(arr,value,pos[0],pos[1],pos[2]);
        modifychowp(value,pos[0],pos[1],pos[2]);
        return value;
    }
    public void drop(){
        int[]value=getvalue();
        int[]pos=sort(pair,max,insertc,1);
        int dropvalue=pos[0];
        for (int i = 1; i < pos.length; i++) {
            if(value[pos[i]-1]<value[dropvalue-1]){
                dropvalue=pos[i];
            }
        }
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }
    public boolean check(){
        int[] pos=sort(pair, max,insertc,1);
        return checknow(pos[0], pos[1], pos[2]) != 2;
    }
    public int[] sort(int pair, int max, int insertc,int mode){
        int[] tem=new int[9];
        int[] pos=new int[3];
        tem[pair-1]=pair;
        tem[max-1]=max;
        tem[insertc-1]=insertc;
        int j=0;
        for (int k : tem) {
            if (k != 0) {
                pos[j] = k;
                j++;
            }
        }
        if(mode==1)
            return pos;
        else
            return tem;
    }
}
