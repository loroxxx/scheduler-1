
import com.jinhui.scheduler.biz.core.util.UtilTool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxf on 2017/1/10.
 */
public class Test {





    private static <T> List<List<T>> splitListByNum(List<T> list, int num) {
        List<List<T>> listList = new ArrayList<List<T>>();
        int count = list.size() / num;
        int remainder = list.size() % num;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                listList.add(list.subList(i * num, (i + 1) * num));
            }
        }
        if (remainder > 0) {
            listList.add(list.subList(count * num, list.size()));
        }

        return listList;
    }

    public static void main(String[] strs){


        String admin = UtilTool.md5Tool("admin4");
        System.out.println(admin);

//        try {
//
//            BufferedReader br = new BufferedReader(new
//                    InputStreamReader(Test.class.getClassLoader().getResourceAsStream("OFD_118_JH_20161014_01.TXT"),"GBK"));
//
//            for(int i = 0;i<95;i++)
//                br.readLine();
//            String str = br.readLine();
//
//            System.out.println(byteSubString(str,0, 120));
//            /*String str = br.readLine();
//            System.out.println(str.substring(0,3));
//            System.out.println(str.length());*/
//            br.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static String byteSubString(String src, int byteStart, int byteEnd){
        int len = byteEnd - byteStart;
        byte[] srcBytes;
        try {
            srcBytes = src.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        byte[] resBytes = new byte[len];
        for(int i = 0; i<len; i++){
            resBytes[i] = srcBytes[byteStart+i];
        }
        try {
            return new String(resBytes,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
