

public class pairb extends paixing{
    /*
        pairb是指同花色下形如AAB或ABB的序列
     */
    public static final int f5=2;
    //设置碰的因数//
    int pair;
    //重复的牌//
    public pairb(){
        super();
    }
    //无参构造//
    public pairb(int pair, int max){
        this.pair=pair;
        this.max=max;
    }
    //带参构造//
    @Override
    public void outpaixing(){
        if(smaller(pair,max)==pair)
            System.out.print("---"+"现在牌型是 "+pair+" "+pair+" "+max+"---");
        else
            System.out.print("---"+"现在牌型是 "+max+" "+pair+" "+pair+"---");
    }
    //重写输出手牌方法//
    public int checknow(int pair,int max){
        if(pair+1==max)
            return -1;
        else if(pair-1==max)
            return 1;
        else return 0;
    }
    //重写检查手牌中是否有两个相邻，1为反的右相邻，0为不相邻，-1为右相邻//
    @Override
    public void value(){
        outpaixing();
        int[] value=getvalue();
        System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
    }
    //重写输出价值//
    @Override
    public int[] getvalue(){
        int[] arr=getarr();
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};
        value[pair-1]+=f5;//设置碰的因数//
        value[max-1]+=f7;
        modifychown(arr,value,pair,max);
        //设置卡张value//
        modifyleft(arr,value,pair);
        modifyright(arr,value,pair);
        modifyleft(arr,value,max);
        modifyright(arr,value,max);
        modifychowp(value,pair,max);
        //设置顺张value
        return value;
    }
    //重写value数组方法//
    @Override
    public int[] getarr(){
        int[] arr =new int[9];
        arr[pair-1]=pair;
        arr[max-1]=max;
        return arr;
    }
    //重写arr数组方法//
    @Override
    public void drop(){
        int[]value=getvalue();
        int dropvalue=findminfrom2(value,pair,max);
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }
    //丢弃value最小的牌//
    public int dropvalue()
    {
        int[]value=getvalue();
        int dropvalue=findminfrom2(value,pair,max);
        return value[dropvalue-1];
    }
    //给出最小value//
    public int findminfrom2(int[] value,int pair,int max){
        int tem1=compare2(value[pair-1],value[max-1]);
        int out;
        if(tem1==1)
            out=pair;
        else out=max;
        return out;
    }
    //输出value最小的牌//
    public void modifychowp(int[]val,int pair, int max){
        int check=checknow(pair,max);
        switch (check)
        {
            case -1:
            {
                if(pair==1||max==9)
                {
                    val[pair-1]+=f3;
                    val[pair]+=f3;
                }
                else{
                    val[pair-1]+=f4;
                    val[pair]+=f4;
                }
                break;
            }
            case 0:{
                break;
            }
            case 1:{
                if (max==1||pair==9){
                    val[pair-1]+=f3;//pair的value//
                    val[pair]+=f3;//max的value//
                }
                else {
                    val[pair-1]+=f4;
                    val[pair]+=f4;
                }
                break;
            }
        }
    }
    public void modifychown(int[]arr,int[]val,int pair,int max){
        if(leftneighbor(arr,pair-1)||rightneighbor(arr,pair-1))
        {
            if(arr[pair-1]+2==arr[max-1])
            {
                val[pair-1]+=f6;
                val[max-1]+=f6;
            }
            if(arr[pair-1]-2==arr[max-1])
            {
                val[pair-1]+=f6;
                val[max-1]+=f6;
            }
        }
    }
    public boolean leftneighbor(int[]arr, int index){
        return super.leftneighbor(arr, index);
    }
    //继承left方法//
    public boolean rightneighbor(int[]arr,int index) {
        return super.rightneighbor(arr, index);
    }
    //继承right方法//
    public int smaller(int pair,int max){
        int sm;
        if(compare2(pair,max)==1)
            sm=pair;
        else sm=max;
        return sm;
    }
    public int bigger(int pair,int max)
    {
        int bg;
        if(compare2(pair,max)==1)
            bg=max;
        else bg=pair;
        return bg;
    }
}
