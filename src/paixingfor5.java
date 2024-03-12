
public class paixingfor5 extends paixingfor4{
    int inserte;
    public paixingfor5(){
        super();
    }
    public paixingfor5(int least,int mid, int max, int insert,int inserte){
        super.least=least;
        super.mid=mid;
        super.max=max;
        super.insert=insert;
        this.inserte=inserte;
    }
    public void paixu(){
        int[] pos=sort(least,mid,max,insert,inserte,1);
        System.out.print("---"+"现在牌型是");
        for(int j=0;j <pos.length;j++) {
            if (j==pos.length-1)
                System.out.print(pos[j]);
            else
                System.out.print(pos[j] + " ");
        }
        System.out.print("---");
    }
    public void value(){
        paixu();
        int[]value=getvalue();
        int[]pos=sort(least,mid,max,insert,inserte,1);
        for (int i = 0; i < pos.length; i++) {
            if (i==pos.length-1)
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+"---");
            else
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+", ");
        }
    }
    public int[] getarr(){
        return sort(least,mid,max,insert,inserte,0);
    }

    @Override
    public int[] getvalue() {
        int[]arr=getarr();//把数初始化进入数组//
        int[]value=super.getvalue();//获得least,mid,max,insert的value数组和初始化的value数组//
        int[]pos=sort(least,mid,max,insert,inserte,1);
        value[inserte-1]+=f7;
        //设置卡张value//
        for (int i = 0; i <= 2; i++) {
            modifychown(arr,value,pos[i],pos[i+1],pos[i+2]);
        }
        //设置邻近因数//
        modifyleft(arr,value,inserte);
        modifyright(arr,value,inserte);
        //设置顺张value//
        for (int i = 0; i <=2; i++) {
            modifychowp(value,pos[i],pos[i+1],pos[i+2]);
        }
        return value;
    }
    public void drop(){
        int[] value=getvalue();
        int[] pos=sort(least,mid,max,insert,inserte,1);
        int dropvalue=pos[0];
        for (int i = 1; i < pos.length; i++) {
            if(value[pos[i]-1]<value[dropvalue-1]){
                dropvalue=pos[i];
            }
        }
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }

    public boolean check(){
        int[] pos=sort(least,mid,max,insert,inserte,1);
        for (int i = 0; i <=2; i++) {
            if(checknow(pos[i],pos[i+1],pos[i+2])==2)
            {
                return false;
            }
        }
        return true;
    }
    public int[] sort(int least, int mid, int max, int insert, int inserte,int mode){
        int[] tem=new int[9];
        int[] pos=new int[5];
        tem[least-1]=least;
        tem[mid-1]=mid;
        tem[max-1]=max;
        tem[insert-1]=insert;
        tem[inserte-1]=inserte;
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
