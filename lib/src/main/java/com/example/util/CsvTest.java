package com.example.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV操作(导出和导入)
 *
 * @author 林计钦
 * @version 1.0 Jan 27, 2014 4:17:02 PM
 */
public class CsvTest {

    public static void main(String[] args) {
//        exportCsv();
//        importCsv();
        try {
            List<String[]> list = POIUtil.readExcel("C:/Users/sks/Desktop/tsl-tysuser.csv");
            System.out.println("main,  [args] list =- "+list);
            if (list != null) {
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < list.size(); j++) {
                    String[] strings = list.get(j);
                    for (int i = 0; i < strings.length; i++) {
                        if (i>0) {
                            String s = strings[i];
                            s = s.replace(" ", "");
                            if (s != null) {
                                sb.append("'");
                                sb.append(s);
                                sb.append("'");
                                if (j < list.size() - 1) sb.append(",");
                            }
//                            System.out.println("main,  [args] i = " + i + " string = " + strings[i]);
                        }
                    }
                }
                System.out.println("main,  [args] "+sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        test();

    }

    private static void test() {
        String s = "136 7501 9816\n" +
                "134 0591 6166\n" +
                "181 8113 8283\n" +
                "156 9591 9295\n" +
                "152 0500 2118\n" +
                "136 6505 7188\n" +
                "158 8001 4032\n" +
                "150 0508 1849\n" +
                "189 9675 6444\n" +
                "177 6189 9995\n" +
                "186 0601 6498\n" +
                "133 4083 8003\n" +
                "187 8288 5689\n" +
                "133 4033 5488\n" +
                "150 2390 6026\n" +
                "177 8375 4368\n" +
                "130 5525 6807\n" +
                "183 9353 3360\n" +
                "159 9995 7622";
        String[] split = s.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s1 = split[i];
            s1 = s1.replace(" ", "");
            sb.append("'");
            sb.append(s1);
            sb.append("'");
            if (i < split.length - 1) sb.append(",");
        }

        System.out.println("test,  [] sb = " + sb);
    }

    /**
     * CSV导出
     *
     * @throws Exception
     */
    public static void exportCsv() {
        List<String> dataList = new ArrayList<String>();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess = CSVUtils.exportCsv(new File("D:/test/ljq.csv"), dataList);
        System.out.println(isSuccess);
    }

    /**
     * CSV导出
     *
     * @throws Exception
     */
    public static void importCsv() {
        List<String> dataList = CSVUtils.importCsv(new File("C:/Users/sks/Desktop/1.xlsx"));
        if (dataList != null && !dataList.isEmpty()) {
            for (String data : dataList) {
                System.out.println(data);
            }
        }
    }


}