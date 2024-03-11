


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
            switch (getposition(insert,least,mid,max)){
                case 1: {
                        System.out.print("---"+"现在牌型是"+insert+" "+least+" "+mid+" "+max+"---");
                    break;
                    }
                case 2:{
                        System.out.print("---"+"现在牌型是"+least+" "+insert+" "+mid+" "+max+"---");
                    break;
                }
                case 3:{
                        System.out.print("---"+"现在牌型是"+least+" "+mid+" "+insert+" "+max+"---");
                    break;
                }
                case 4:{
                        System.out.print("---"+"现在牌型是"+least+" "+mid+" "+max+" "+insert+"---");
                    break;
                }
            }
    }
    //输出插入一张牌后不相连的有序牌//
    @Override
    public void value(){
        paixu();
        int[] value=getvalue();
        switch (getposition(insert,least,mid,max)){
            case 1: {
                System.out.print(insert+"的价值是"+value[insert-1]+", "+least+"的价值是"+value[least-1]+", "+mid+"的价值是"+value[mid-1]+", "+max+"的价值是"+value[max-1]+"---");
                break;
            }
            case 2:{
                System.out.print(least+"的价值是"+value[least-1]+", "+insert+"的价值是"+value[insert-1]+", "+mid+"的价值是"+value[mid-1]+", "+max+"的价值是"+value[max-1]+"---");
                break;
            }
            case 3:{
                System.out.print(least+"的价值是"+value[least-1]+", "+mid+"的价值是"+value[mid-1]+", "+insert+"的价值是"+value[insert-1]+", "+max+"的价值是"+value[max-1]+"---");
                break;
            }
            case 4:{
                System.out.print(least+"的价值是"+value[least-1]+", "+mid+"的价值是"+value[mid-1]+", "+max+"的价值是"+value[max-1]+", "+insert+"的价值是"+value[insert-1]+"---");
                break;
            }
        }
    }
    @Override
    public int[] getarr(){
        int[] arr=new int[9];
        arr[least-1]=least;
        arr[mid-1]=mid;
        arr[max-1]=max;
        arr[insert-1]=insert;
        modifyleft(arr,least-1);
        modifyright(arr,least-1);
        modifyleft(arr,mid-1);
        modifyright(arr,mid-1);
        modifyleft(arr,max-1);
        modifyright(arr,max-1);
        modifyleft(arr,insert-1);
        modifyright(arr,insert-1);
        return arr;
    }
    //重写arr数组方法//
    public int[] getvalue(){
        int[] arr=new int[9];
        arr[least-1]=least;
        arr[mid-1]=mid;
        arr[max-1]=max;
        arr[insert-1]=insert;
        //初始化arr数组//
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};//初始化value数组带防守因数//
        modifychownfor4(arr,value,insert,least,mid,max);
        //设置卡张value//
        /*if(leftneighbor(arr,least-1))
        {arr[least-2]=least-1;
            value[least-1]+=f2;
        }*/
        modifyleft(arr,value,least);
        /*if(rightneighbor(arr,least-1))
        {arr[least]=least+1;
            value[least-1]+=f2;}*/
        modifyright(arr,value,least);
        /*if(leftneighbor(arr,mid-1))
        {arr[mid-2]=mid-1;
            value[mid-1]+=f2;}*/
        modifyleft(arr,value,mid);
        /*if(rightneighbor(arr,mid-1))
        {arr[mid]=mid+1;
            value[mid-1]+=f2;}*/
        modifyright(arr,value,mid);
        /*if(leftneighbor(arr,max-1))
        {
            arr[max-2]=max-1;
            value[max-1]+=f2;}*/
        modifyleft(arr,value,max);
        /*if(rightneighbor(arr,max-1)){
            arr[max]=max+1;
            value[max-1]+=f2;
        }*/
        modifyright(arr,value,max);
        /*if(leftneighbor(arr,insert-1))
            {arr[insert-2]=insert-1;
                value[insert-1]+=f2;}*/
        modifyleft(arr,value,insert);
        /*if(rightneighbor(arr,insert-1)){
                arr[insert]=insert+1;
                value[insert-1]+=f2;
        }*/
        modifyright(arr,value,insert);
        //设置邻近因数//
        modifychowpfor4(value,insert,least,mid,max);
        //设置顺子吃因数，区分边张//
        return value;
        }
    //获得value数组//
    @Override
    public void drop(){
        int[]value=getvalue();
        int dropvalue;
        int temp= compare2(value[findminfrom3(value,least,mid,max)-1],value[findminfrom3(value,mid,max,insert)-1]);
        if(temp==1)
            dropvalue=findminfrom3(value,least,mid,max);
        else dropvalue=findminfrom3(value,mid,max,insert);
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }
    public boolean check(int insert){
        this.insert=insert;
        boolean flag =false;
        switch (getposition(insert,least,mid,max)){
            case 1: {
                if(checknow(insert,least,mid)!=2)
                    flag=true;
                break;
            }
            case 2:{
                if(checknow(least,insert,mid)!=2)
                    flag=true;
                break;
            }
            case 3:{
                if(checknow(mid,insert,max)!=2)
                    flag=true;
                break;
            }
            case 4:{
                if(checknow(mid,max,insert)!=2)
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
    public int getposition(int insert,int least,int mid, int max){
        int position;
        if(insert<=least)
        {
            position=1;
        }
        else if(insert<=mid)
        {
            position=2;
        }
        else if (insert<=max)
        {
            position=3;
        }
        else {
            position=4;
        }
        return position;
    }
    //获得insert的相对位置//
    public void modifychowpfor4(int[] val, int insert,int least, int mid, int max){
        switch (getposition(insert,least,mid,max))
        {
            case 1:
            {
                modifychowp(val,insert,least,mid);
                modifychowp(val,least,mid,max);
                break;
            }
            case 2:
            {
                modifychowp(val,least,insert,mid);
                modifychowp(val,insert,mid,max);
                break;
            }
            case 3:
            {
                modifychowp(val,least,mid,insert);
                modifychowp(val,mid,insert,max);
                break;
            }
            case 4:
            {
                modifychowp(val,least,mid,max);
                modifychowp(val,mid,max,insert);
                break;
            }
        }
    }
    public void modifychownfor4(int[]array,int[]val, int insert, int least, int mid, int max)
    {
        switch (getposition(insert,least,mid,max))
        {
            case 1:
            {
                modifychown(array,val,insert,least,mid);
                modifychown(array,val,least,mid,max);
                break;
            }
            case 2:
            {
                modifychown(array,val,least,insert,mid);
                modifychown(array,val,insert,mid,max);
                break;
            }
            case 3:
            {
                modifychown(array,val,least,mid,insert);
                modifychown(array,val,mid,insert,max);
                break;
            }
            case 4:
            {
                modifychown(array,val,least,mid,max);
                modifychown(array,val,mid,max,insert);
                break;
            }
        }
    }

}
