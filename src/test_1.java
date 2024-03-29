import java.util.ArrayList;
public class test_1 {
        public static void main(String[] args) {
            ArrayList<paixing> list1=getpxlist();//创建ABC集合//
            ArrayList<pairb> list2=getpblist();//创建AAB集合//
            ArrayList<paixingfor4> list3=getpx4list();//创建ABCD集合//
            ArrayList<pairbfor4> list4=getpb4list();//创建AABC或AABB集合//
            ArrayList<paixingfor5> list5=getpx5list();//创建ABCDE集合//
            ArrayList<paixingfor6> list6=getpx6list();//创建ABCDEF集合//
            ArrayList<pairbfor5> list7=getpb5list();//创建AABBC集合//
            ArrayList<pairbfor5d1> list8=getpb5d1list();//创建AABCD集合//
            //策略穷举//
            /*for (paixing paixing : list1)
            {
                paixing.value();
                paixing.drop();
            }//当前设定下同色三种不同牌的丢弃策略//
            for (pairb pairb : list2)//遍历//
            {
                pairb.value();
                pairb.drop();
            }//当前设定下同色一对带一张的丢弃策略//
            for (paixingfor4 paixingfor4 : list3) {
                paixingfor4.value();
                paixingfor4.drop();
            }
            for (pairbfor4 pairbfor4:list4)
            {
                pairbfor4.value();
                pairbfor4.drop();
            }
            for (paixingfor5 paixingfor5:list5)
            {
                paixingfor5.value();
                paixingfor5.drop();
            }
            for(pairbfor5 pairbfor5:list7)
            {
                pairbfor5.value();
                pairbfor5.drop();
            }
            for (pairbfor5d1 pairbfor5d1:list8)
            {
                pairbfor5d1.value();
                pairbfor5d1.drop();
            }
            for (paixingfor6 paixingfor6:list6)
            {
                paixingfor6.value();
                paixingfor6.drop();
            }*/
            String s=inrec.input();
            System.out.println(inrec.recognize(s,1));
            System.out.println(inrec.corres(s));
            //split7 spl7=new split7(inrec.recognize(s,1),inrec.corres(s),s);
            //spl7.getvalue();
            //spl7.print();
            split8 spl8=new split8(inrec.recognize(s,1),inrec.corres(s),s);
            spl8.print();
        }
    public static ArrayList<paixing> getpxlist(){
        ArrayList<paixing> list=new ArrayList<>();
        for(int i=1;i<=7;i++)
        {
            for(int j=i+1;j<=8;j++)
            {
                for(int k=j+1;k<=9;k++)
                {
                    if(i+1==j&&j+1==k){
                        continue;
                    }
                    else {
                        list.add(new paixing(i,j,k));
                    }
                }
            }
        }
        return list;
    }
    //循环遍历全排列//
    public static ArrayList<pairb> getpblist(){
            ArrayList<pairb> list=new ArrayList<>();
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(i!=j)
                    list.add(new pairb(i,j));
            }
        }
        return list;
    }
    //循环遍历带对的排列//
    public static ArrayList<paixingfor4> getpx4list(){
        ArrayList<paixingfor4> list=new ArrayList<>();
        for(int i=1;i<=7;i++)
        {
            for(int j=i+1;j<=8;j++)
            {
                for(int k=j+1;k<=9;k++)
                {
                    if(i+1==j&&j+1==k){
                        continue;
                    }
                    else {
                        for (int l = 1; l <=9 ; l++) {
                            paixingfor4 pxf4=new paixingfor4(i,j,k,l);
                            if(l!=i&&l!=j&&l!=k&&pxf4.check())
                            {
                                list.add(pxf4);
                                }
                        }
                    }
                }
            }
        }
        return list;
    }
    //循环遍历全排列后多一张的排列//
    public static ArrayList<pairbfor4> getpb4list(){
        ArrayList<pairbfor4> list=new ArrayList<>();
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(i!=j)
                {
                    for (int k = 1; k <=9 ; k++) {
                        pairbfor4 pbf4=new pairbfor4(i,j,k);
                        if(k!=i&&pbf4.check(k))
                            list.add(pbf4);
                    }
                }
            }
        }
        return list;
    }
    //循环遍历带对的排列后多一张的排列 //
    public static ArrayList<paixingfor5> getpx5list(){
        ArrayList<paixingfor5> list=new ArrayList<>();
        for(int i=1;i<=7;i++)
        {
            for(int j=i+1;j<=8;j++)
            {
                for(int k=j+1;k<=9;k++)
                {
                    if(i+1==j&&j+1==k){
                        continue;
                    }
                    else {
                        for (int l = 1; l <=9 ; l++) {
                            paixingfor4 pxf4=new paixingfor4(i,j,k,l);
                            if(l!=i&&l!=j&&l!=k&&pxf4.check()){
                                for (int m = 1; m <=9 ; m++) {
                                    paixingfor5 pxf5=new paixingfor5(i,j,k,l,m);
                                    if(m!=i&&m!=j&&m!=k&&m!=l&&pxf5.check())
                                        list.add(pxf5);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    //循环遍历全排列后多一张的基础上再多一张的排列//
    public static ArrayList<pairbfor5> getpb5list(){
        ArrayList<pairbfor5> list=new ArrayList<>();
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(j!=i)
                {
                    for (int k = 1; k <=9 ; k++) {
                        if(j==k)
                        {
                            pairbfor4 pbf4=new pairbfor4(i,j,k);
                            if(pbf4.check(k)){
                                for (int l = 1; l <=9 ; l++) {
                                    pairbfor5 pbf5=new pairbfor5(i,j,l);
                                    if(pbf5.check()&&l!=i&&l!=j)
                                        list.add(pbf5);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    public static ArrayList<pairbfor5d1> getpb5d1list(){
        ArrayList<pairbfor5d1> list=new ArrayList<>();
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=9;j++)
            {
                if(j!=i)
                {
                    for (int k = 1; k <=9 ; k++) {
                        if(k!=j)
                        {
                            pairbfor4 pbf4=new pairbfor4(i,j,k);
                            if(i!=k&&pbf4.check(k)){
                                for (int l = 1; l <=9 ; l++) {
                                    pairbfor5d1 pbf5d1=new pairbfor5d1(i,j,k,l);
                                    if(pbf5d1.check()&&l!=i&&l!=j&&l!=k)
                                        list.add(pbf5d1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    public static ArrayList<paixingfor6> getpx6list(){
        ArrayList<paixingfor6> list=new ArrayList<>();
        for(int i=1;i<=7;i++)
        {
            for(int j=i+1;j<=8;j++)
            {
                for(int k=j+1;k<=9;k++)
                {
                    if(i+1==j&&j+1==k){
                        continue;
                    }
                    else {
                        for (int l = 1; l <=9 ; l++) {
                            paixingfor4 pxf4=new paixingfor4(i,j,k,l);
                            if(l!=i&&l!=j&&l!=k&&pxf4.check()){
                                for (int m = 1; m <=9 ; m++) {
                                    paixingfor5 pxf5=new paixingfor5(i,j,k,l,m);
                                    if(m!=i&&m!=j&&m!=k&&m!=l&&pxf5.check()) {
                                        for (int n = 1; n <= 9; n++) {
                                            paixingfor6 pxf6 = new paixingfor6(i, j, k, l, m, n);
                                            if (n != i && n != j && n != k && n != l && n != m && pxf6.check())
                                                list.add(pxf6);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    //循环遍历全排列后多两张的基础上再多一张的排列//
}

