


public class paixingfor4 extends paixing{
    /*
        paixingfor4是指同花色下从小到大互不相邻且互不相同的序列ABCD
     */
    int insert;
    public paixingfor4(){
        super();
    }//无参构造//
    public paixingfor4(int least,int mid, int max, int insert){
        super.least=least;
        super.mid=mid;
        super.max=max;
        this.insert=insert;
    }//带参构造//
    public void paixu(){
        int[] pos=sort(least,mid,max,insert,1);
        System.out.print("---"+"现在牌型是");
        for(int j=0;j <pos.length;j++) {
            if (j==pos.length-1)
                System.out.print(pos[j]);
            else
                System.out.print(pos[j] + " ");
        }
        System.out.print("---");
    }
    //输出插入一张牌后不相连的有序牌//
    @Override
    public void value(){
        paixu();
        int[] value=getvalue();
        int[]pos=sort(least,mid,max,insert,1);
        for (int i = 0; i < pos.length; i++) {
            if (i==pos.length-1)
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+"---");
            else
                System.out.print(pos[i]+"的价值是"+value[pos[i]-1]+", ");
        }
    }
    @Override
    public int[] getarr(){
        return sort(least,mid,max,insert,0);
    }
    //重写arr数组方法//
    public int[] getvalue(){
        int[] arr=getarr();
        //初始化arr数组//
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};//初始化value数组带防守因数//
        value[least-1]+=f7;
        value[mid-1]+=f7;
        value[max-1]+=f7;
        value[insert-1]+=f7;
        modifychownfor4(arr,value,insert,least,mid,max);
        //设置卡张value//
        modifyleft(arr,value,least);
        modifyright(arr,value,least);
        modifyleft(arr,value,mid);
        modifyright(arr,value,mid);
        modifyleft(arr,value,max);
        modifyright(arr,value,max);
        modifyleft(arr,value,insert);
        modifyright(arr,value,insert);
        //设置邻近因数//
        modifychowpfor4(value,insert,least,mid,max);
        //设置顺子吃因数，区分边张//
        return value;
        }
    //获得value数组//
    @Override
    public void drop(){
        int[] value=getvalue();
        int[] pos=sort(least,mid,max,insert,1);
        int dropvalue=pos[0];
        for (int i = 1; i < pos.length; i++) {
            if(value[pos[i]-1]<value[dropvalue-1]){
                dropvalue=pos[i];
            }
        }
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }
    public boolean check(){
        int[] pos=sort(least,mid,max,insert,1);
        for (int i = 0; i <=1; i++) {
            if(checknow(pos[i],pos[i+1],pos[i+2])==2)
            {
                return false;
            }
        }
        return true;
    }
    //检查insert是否合法//
    public int[] sort(int least, int mid, int max, int insert, int mode){
        int[] tem=new int[9];
        int[] pos=new int[4];
        tem[least-1]=least;
        tem[mid-1]=mid;
        tem[max-1]=max;
        tem[insert-1]=insert;
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
    public void modifychowpfor4(int[] val, int insert,int least, int mid, int max){
        int[] pos=sort(least,mid,max,insert,1);
        for (int i = 0; i <=1; i++) {
            modifychowp(val,pos[i],pos[i+1],pos[i+2]);
        }
    }
    public void modifychownfor4(int[]array,int[]val, int insert, int least, int mid, int max)
    {
        int[] pos=sort(least,mid,max,insert,1);
        for (int i = 0; i <=1; i++) {
            modifychown(array,val,pos[i],pos[i+1],pos[i+2]);
        }
    }

}
