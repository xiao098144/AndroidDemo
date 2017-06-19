import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

File file = new File('file.txt')
if (file != null) {
    StringBuilder sb = new StringBuilder();
    file.eachLine { value ->
        if (value != null && value.length() > 0) {
            sb.append(value);
        }
    };
//    println(sb)
    JSONArray json = JSONArray.parse(sb.toString());
    println(json)
    sb.setLength(0)
    List<String> list = new ArrayList<>()
    for (int i = 0; i < json.size(); i++) {
        JSONObject obj = json.get(i);
        list.add(obj.get("time"))
    }
    println(list)
    File file1 = new File('time.txt')
    List<String> list2 = new ArrayList<>();
    if (file1 != null) {
        file1.eachLine { value ->
            if (value != null && value.length() > 0) {
                if (!list2.contains(value))
                    list2.add(value)
            }
        }
    }
    println(list2)
    List<String> list3 = new ArrayList<>();
    String s1, ss2;
    boolean isCatch = false;
    for (int i = 0; i < list.size(); i++) {
        s1 = list.get(i);
        isCatch = false;
        for (int j = 0; j < list2.size(); j++) {
            s2 = list2.get(j);
            if (s1.equals(s2)) {
                isCatch = true;
                break;
            }
        }
        if (!isCatch) list3.add(s1);
    }
    String s = "SELECT * FROM tys_ext_xt_rd_sugar_values WHERE is_del = 0 AND tys_ext_xt_rd_sugar_values.sugar_values_record_time >= '%1s' and tys_ext_xt_rd_sugar_values.sugar_values_record_date <= '%2s'  and tys_ext_xt_rd_sugar_values.sugar_values_user_id = 10000039 and tys_ext_xt_rd_sugar_values.sugar_values_source_type = 10 ORDER BY tys_ext_xt_rd_sugar_values.sugar_values_record_time desc limit 1";
    java.lang.Object[] objects = new Object[2];
//    objects[0] = "2016"
//    objects[1] = "20202"
//    println(String.format(s, objects))
    if (list3.size() > 0) {
        sb.setLength(0)
        for (int i = 0; i < list3.size(); i++) {
            String time = list3.get(i)
            objects[0] = time
            objects[1] = time
            sb.append(String.format(s, objects) + "\n");
        }
    }
    println(" run finish list3 = " + list3)
    println(" list.size = " + list.size() + " list2.size = " + list2.size() + " list3.size = " + list3.size())
    println(sb.toString())
}