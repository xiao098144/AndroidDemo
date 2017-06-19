package com.example;

import java.util.ArrayList;
import java.util.List;

public class ChanlUtil {

    public static void main(String[] args) {
    channel2();
//    channel1();
//    channel3();
//        channel4();
//        channel5();
        // name();

//        String s = "FB";

    }

    private static void channel4() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            String string = String.valueOf(i);
            sb.append("c1");
            int k = 7 - 1 - string.length();
            for (int j = 0; j < k; j++) {
                sb.append("0");
            }
            sb.append(string);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void channel2() {
        String channel = "5、14、32、169、240";
        String[] split = channel.split("、");
        StringBuilder sb = new StringBuilder();
        for (String string : split) {
//            sb.append(string);
//            sb.append(":");
            sb.append("1");
            int i = 7 - 1 - string.length();
            for (int j = 0; j < i; j++) {
                sb.append("0");
            }
            sb.append(string);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void channel1() {
        // String chanl =
        // "1000000、1000001、1000003、1000004、1000005、1000006、1000008、1000009、1000010、1000012、1000014、1000015、1000017、1000018、1000020、1000025、1000029、1000030、1000031、1000032、1000042、1000063、1000105、1000131、1000132、1000142、1000147、1000159、1000169、1000175、1000177、1000200、1000203、1000210、1000211、1000212、1000216、1000220、1000225、1000226、1000227、1000228";
        String chanl = "2000000、2000001、2000002、2000003、2000005、2000007、2000009、2000010、2000011、2000012、2100011";
        String[] split = chanl.split("、");
        StringBuilder sb = new StringBuilder();
        for (String str : split) {
            String head = str.substring(str.length() - 3);
            if (head.startsWith("0")) {
                head = head.substring(1);
            }
            sb.append(head + ":");
            sb.append(str);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void channel5() {
        // String chanl =
        // "1000000、1000001、1000003、1000004、1000005、1000006、1000008、1000009、1000010、1000012、1000014、1000015、1000017、1000018、1000020、1000025、1000029、1000030、1000031、1000032、1000042、1000063、1000105、1000131、1000132、1000142、1000147、1000159、1000169、1000175、1000177、1000200、1000203、1000210、1000211、1000212、1000216、1000220、1000225、1000226、1000227、1000228";
        String chanl = "2000000、2000001、2000002、2000003、2000005、2000007、2000009、2000010、2000011、2000012、2100011";
        String[] split = chanl.split("、");
        StringBuilder sb = new StringBuilder();
        for (String str : split) {
            sb.append(str);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void name() {
        String s = "ask" + ",device" + ",diet" + ",health" + ",knowledge" + ",login" + ",medicine" + ",mine" + ",msgcenter" + ",pub" + ",register"
                + ",shop" + ",sport" + ",sugar" + ",sugarmore" + ",traffic" + ",tsl" + ",tyq";

        String[] split = s.split(",");
        for (String str : split) {
            StringBuilder sb = new StringBuilder();
            sb.append("-keep class com.ddoctor.user.module." + str + ".bean.** { *; }" + "\n");
            sb.append("-keep class com.ddoctor.user.module." + str + ".request.** { *; }" + "\n");
            sb.append("-keep class com.ddoctor.user.module." + str + ".response.** { *; }" + "\n" + "\n");
            System.out.println(sb.toString());
        }
    }

    private static void channel3() {
        String channel = "237、238";
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < channel.length(); i++) {
            if (Character.isDigit(channel.charAt(i))) {
                sb.append(channel.charAt(i));
                if (i == channel.length() - 1) {
                    if (sb.length() > 0) {
                        list.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            } else {
                if (sb.length() > 0) {
                    list.add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
        Object[] array = list.toArray();
        for (Object obj : array) {
            String string = String.valueOf(obj);
            sb.append(string);
            sb.append(":");
            sb.append("1");
            int i = 7 - 1 - string.length();
            for (int j = 0; j < i; j++) {
                sb.append("0");
            }
            sb.append(string);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

}
