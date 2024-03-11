
import java.util.ArrayList;
public class test_1 {
        public static void main(String[] args) {
            ArrayList<paixing> list1=getpxlist();//创建全排集合//
            ArrayList<pairb> list2=getpblist();//创建带对集合//
            ArrayList<paixingfor4> list3=getpx4list();
            ArrayList<pairbfor4> list4=getpb4list();
            //遍历//
            for (paixing paixing : list1)
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
                            if(l!=i&&l!=j&&l!=k&&pxf4.check(l))
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
}

