

public class pairbfor4 extends pairb {//未优化getposition方法//
    /*
        pairbfor4是指形如AABC或ABBC或ABCC的序列
     */
    int insert;
    public pairbfor4(){
        super();
    }
    public pairbfor4(int pair, int max,int insert){
        super.pair=pair;
        super.max=max;
        this.insert=insert;
    }
    public void paixu(){
        switch (getposition(insert,pair,max)){
            case 1: {
                if(smaller(pair,max)==pair)
                    System.out.print("---"+"现在牌型是"+insert+" "+pair+" "+pair+" "+max+"---");
                else
                    System.out.print("---"+"现在牌型是"+insert+" "+max+" "+pair+" "+pair+"---");
                break;
            }
            case 2:{
                if(smaller(pair,max)==pair)
                    System.out.print("---"+"现在牌型是"+pair+" "+pair+" "+insert+" "+max+"---");
                else
                    System.out.print("---"+"现在牌型是"+max+" "+ insert+" "+pair+" "+pair+"---");
                break;
            }
            case 3:{
                if(smaller(pair,max)==pair)
                    System.out.print("---"+"现在牌型是"+pair+" "+pair+" "+max+" "+insert+"---");
                else
                    System.out.print("---"+"现在牌型是"+max+" "+pair+" "+pair+" "+insert+"---");
                break;
            }
            }
        }
    //输出插入一张牌后不相连的有序牌//
    public void value(){
        paixu();
        int[] value=getvalue();
        switch (getposition(insert,pair,max)){
            case 1: {
                if(doublepair())
                    System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
                else
                    System.out.print(insert+"的价值是"+value[insert-1]+", "+smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
                break;
            }
            case 2:{
                if(doublepair())
                    System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
                else
                    System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+insert+"的价值是"+value[insert-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
                break;
            }
            case 3:{
                if (doublepair())
                    System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+"---");
                else
                    System.out.print(smaller(pair,max)+"的价值是"+value[smaller(pair,max)-1]+", "+bigger(pair,max)+"的价值是"+value[bigger(pair,max)-1]+", "+insert+"的价值是"+value[insert-1]+"---");
                break;
            }
        }
    }
    public int[] getarr(){
        int[] arr =new int[9];
        arr[pair-1]=pair;
        arr[max-1]=max;
        arr[insert-1]=insert;
        return arr;
    }
    //重写arr数组方法//
    public int[] getvalue(){
        int[] arr=getarr();
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};
        modifyleft(arr,value,insert);
        modifyright(arr,value,insert);
        modifyleft(arr,value,pair);
        modifyright(arr,value,pair);
        if (doublepair())//insert=max,eg1133,pair=1,insert=3,max=3//
        {//value算法和pairb相同//
            value[pair-1]+=f5;
            value[insert-1]+=f5;
            modifychowp(value,pair,max);
            modifychown(arr,value,pair,max);
        }
        else {//insert!=max,eg1134,pair=1,insert=3,max=4//
            //value算法和paixing相同//
            value[pair-1]+=f5;
            value[insert-1]+=f7;
            value[max-1]+=f7;
            modifyleft(arr,value,max);
            modifyright(arr,value,max);
            modifychowpfor4(value,insert,pair,max);
            modifychownfor4(arr,value,insert,pair,max);
        }
        return value;
    }
    public void drop(){
        int[]value=getvalue();
        int dropvalue;
        if(doublepair())
        {
            dropvalue=findminfrom2(value,pair,max);
            System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
        }
        else {
            dropvalue=findminfrom3(value,insert,pair,max);
            System.out.println("应该丢弃"+dropvalue+"这张牌"+"---");
        }
    }
    public boolean check(int insert){
        boolean flag =false;
        switch (getposition(insert,pair,max)){
            case 1: {
                if(checknow(insert,smaller(pair,max),bigger(pair,max))!=2)
                    flag=true;
                break;
            }
            case 2:{
                if(checknow(smaller(pair,max),insert,bigger(pair,max))!=2)
                    flag=true;
                break;
            }
            case 3:{
                if(checknow(smaller(pair,max),bigger(pair,max),insert)!=2)
                    flag=true;
                break;
            }
            default:{
                break;
            }
        }
        return flag;
    }
    //检查insert是否合法//
    public boolean doublepair(){
        return insert == max;
    }
    public int getposition(int insert,int pair, int max){
        int position=0;
        if(insert<=smaller(pair,max))
        {
            position=1;
        }
        else if(insert>smaller(pair,max)&&insert<=bigger(pair,max))
        {
            position=2;
        }
        else if (insert>bigger(pair,max)){
            position=3;
        }
        return position;
    }
    //获得insert的相对位置//
    public void modifychowpfor4(int[] val, int insert,int pair, int max){
        switch (getposition(insert,pair,max))
        {
            case 1:
            {
                modifychowp(val,insert,smaller(pair,max),bigger(pair,max));
                break;
            }
            case 2:
            {
                modifychowp(val,smaller(pair,max),insert,bigger(pair,max));
                break;
            }
            case 3:
            {
                modifychowp(val,smaller(pair,max),bigger(pair,max),insert);
                break;
            }
        }
    }
    public void modifychownfor4(int[]array,int[]val, int insert, int pair, int max)
    {
        switch (getposition(insert,pair,max))
        {
            case 1:
            {
                modifychown(array,val,insert,smaller(pair,max),bigger(pair,max));
                break;
            }
            case 2:
            {
                modifychown(array,val,smaller(pair,max),insert,bigger(pair,max));
                break;
            }
            case 3:
            {
                modifychown(array,val,smaller(pair,max),bigger(pair,max),insert);
                break;
            }
        }
    }
}
