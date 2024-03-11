
public class paixing {
    /*
        paixing是指同花色下从小到大各不相同的序列ABC
     */
    public static final int f1=1;
    //设置防守牌的因数f1，1~9防守重要性被设置为123454321，f1的改变只影响与f2的比较//
    public static final int f2=1;
    //设置相邻牌的因数f2//
    public static final int f3=1;
    //设置最左和最右顺子（吃）的因数f3//
    public static final int f4=2;
    //设置一般顺子（吃）的因数f4//
    public static final int f6=1;
    //设置卡张吃的因数f6//
    int least;//最小牌//
    int mid;//中间牌//
    int max;//最大牌//
    public paixing (){
    }
    //无参构造//
    public paixing(int least,int mid,int max){
        this.least=least;
        this.mid=mid;
        this.max=max;
    }
    //带参构造三张牌//
    public void outpaixing(){
        System.out.print("---"+"现在牌型是 "+least+" "+mid+" "+max+"---");
    }
    //输出当前手牌//
    public int checknow(int least,int mid,int max){
        if(least+1==mid&&mid+1==max)
            return 2;
        if(least+1==mid)
            return 1;
        else if(mid+1==max)
            return -1;
        else return 0;
    }
    //检查手牌中是否有两个相邻，1为左相邻，0为不相邻，-1为右相邻，2为顺子//
    public int getneighbor()
    {
        int cnt=0;
        int[] arr=getarr();
        for(int i=0;i<=8;i++)
        {
            if(i!=least-1&&i!=mid-1&&i!=max-1&&arr[i]!=0)
                cnt=cnt+1;
        }
        return cnt;
    }
    //返回相邻牌的总数//
    public void outneighbor(){
        int[] arr =getarr();
        System.out.print("相邻的牌有");
        for(int i=0;i<=8;i++)
        {
            if(i!=least-1&&i!=mid-1&&i!=max-1&&arr[i]!=0)
                System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    //输出相邻牌//
    public void value(){
        outpaixing();
        int[] value=getvalue();
        System.out.print(least+"的价值是"+value[least-1]+", "+mid+"的价值是"+value[mid-1]+", "+max+"的价值是"+value[max-1]+"---");
    }
    //输出价值//
    public int[] getarr(){
        int[] arr=new int[9];
        arr[least-1]=least;
        arr[mid-1]=mid;
        arr[max-1]=max;
        modifyleft(arr,least);
        modifyright(arr,least);
        modifyleft(arr,mid);
        modifyright(arr,mid);
        modifyleft(arr,max);
        modifyright(arr,max);
        return arr;
    }
    //数组与临近数组构造方法//
    public int[] getvalue(){
        int[] arr=new int[9];
        arr[least-1]=least;
        arr[mid-1]=mid;
        arr[max-1]=max;
        //初始化arr数组//
        int[] value={f1,f1*2,f1*3,f1*4,f1*5,f1*4,f1*3,f1*2, f1};//初始化value数组带防守因数//
        /*if(leftneighbor(arr,mid-1)||rightneighbor(arr,mid-1))
        {
            if(arr[mid-1]+2==arr[max-1])
            {
                value[mid-1]+=f6;
                value[max-1]+=f6;
            }
            if(arr[mid-1]-2==arr[least-1])
            {
                value[mid-1]+=f6;
                value[least-1]+=f6;
            }
        }*/
        modifychown(arr,value,least,mid,max);
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
        //设置邻近因数//
        /*int check=checknow(least,mid,max);
        switch (check) {
            case 1: {
                if(least==1)
                {
                    value[least-1]+=f3;
                    value[least]+=f3;
                }
                else {
                    value[least-1]+=f4;
                    value[least]+=f4;
                }
                break;
            }
            case 0:{
                break;
            }
            case -1:{
                if(max==9){
                    value[max-1]+=f3;
                    value[max-2]+=f3;
                }
                else {
                    value[max-1]+=f4;
                    value[max-2]+=f4;
                }
                break;
            }
        }*/
        modifychowp(value,least,mid,max);
        //设置顺子吃因数，区分边张//
        return value;
    }
    //获得value数组//
    public void drop(){
        int[]value=getvalue();
        int dropvalue=findminfrom3(value,least,mid,max);
        System.out.println("应当丢弃"+dropvalue+"这张牌"+"---");
    }
    //丢弃value最小的牌//
    public int findminfrom3(int[] value,int least,int mid,int max){
        int tem1=compare2(value[mid-1],value[max-1]);
        int tem2 = compare2(value[least - 1], value[mid - 1]);
        int tem3=compare2(value[least-1],value[max-1]);
        int out=0;
        if(tem2==1&&tem3==1)
            out= least;
        else if(tem1==-1&&tem3==-1)
            out= max;
        else if(tem2==-1&&tem1==1)
            out= mid;
        return out;
    }
    //输出value最小的牌//
    public int compare2(int a,int b){
        if (a<b)
            return 1;
        else return -1;
    }
    //比较a与b，给出序关系 //
    public boolean leftneighbor(int[]arr,int index){
        return (index - 1) >= 0 && arr[index - 1] == 0;
    }
    //检查index左边是否有位置//
    public boolean rightneighbor(int[]arr,int index) {
        return (index + 1) <= 8 && arr[index + 1] == 0;
    }
    //检查index右边是否有位置//
    public void modifyleft(int[] array, int[] val,int num){
        if(leftneighbor(array,num-1))
        {array[num-2]=num-1;
            val[num-1]+=f2;
        }
    }
    public void modifyleft(int[] array,int num){
        if(leftneighbor(array,num-1))
        {array[num-2]=num-1;
        }
    }
    public void modifyright(int[] array, int[] val,int num){
        if(rightneighbor(array,num-1))
        {array[num]=num+1;
            val[num-1]+=f2;
        }
    }
    public void modifyright(int[] array, int num){
        if(rightneighbor(array,num-1))
        {array[num]=num+1;
        }
    }
    public void modifychowp(int[]val,int left, int mid, int right){
        int check=checknow(left,mid,right);
        switch (check) {
            case 1: {
                if(left==1)
                {
                    val[left-1]+=f3;
                    val[left]+=f3;
                }
                else {
                    val[left-1]+=f4;
                    val[left]+=f4;
                }
                break;
            }
            case 0:{
                break;
            }
            case -1:{
                if(right==9){
                    val[right-1]+=f3;
                    val[right-2]+=f3;
                }
                else {
                    val[right-1]+=f4;
                    val[right-2]+=f4;
                }
                break;
            }
        }
    }
    public void modifychown(int[] array,int[]val,int left,int mid, int right){
        if(leftneighbor(array,mid-1)||rightneighbor(array,mid-1))
        {
            if(array[mid-1]+2==array[right-1])
            {
                val[mid-1]+=f6;
                val[right-1]+=f6;
            }
            if(array[mid-1]-2==array[left-1])
            {
                val[mid-1]+=f6;
                val[left-1]+=f6;
            }
        }
    }
}
