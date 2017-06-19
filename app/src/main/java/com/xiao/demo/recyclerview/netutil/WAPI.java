package com.xiao.demo.recyclerview.netutil;

public class WAPI {

	// 测试接口地址

	public final static String WAPI_URL_TEST = "http://test.ddoctor.cn/";
	public final static String WAPI_URL_ONLINE = "http://api.ddoctor.cn/";

	// 正式接口地址
	// public final static String WAPI_URL = "http://api.ddoctor.cn/";

	// 文件
	// public final static String WAPI_FILE_URL = WAPI_URL;

	// 通用接口

	public final static String WAPI_RECOMMEND_URL = "http://www.ddoctor.cn";

	// 接口的字典标记，与CMS的字典更新序列号对应，值为CMS的字典更新序列号-1
	public final static int WAPI_DIC_SN = 1;

	// 常见问题
	public final static String WAPI_CHANGJIAN = "http://cms.ddoctor.cn/tyscms/events/commonProblem/index.html";

	// 医生赚取积分规则说明页面(测试)
	// public final static String WAPI_GETPOINTS =
	// "http://cmstest.ddoctor.cn/tyscms/events/doctorFunctionInfo/get_points.html";

	// 医生赚取积分规则说明页面(正式)
	public final static String WAPI_GETPOINTS = "http://cms.ddoctor.cn/tyscms/events/doctorFunctionInfo/get_points.html";

	// 支付宝接口
	// public final static String WAPI_ZFB_URL = WAPI_URL + "zfb";

	/**
	 * 医网签测试地址
	 */
	// private String tmsUrl = "http://60.247.77.124:8080/am";
	public final static String WAPI_YWQ_APPID_TEST = "2016030715394577";

	public final static String WAPI_YWQ_SECRET_TEST = "111111";

	public final static String WAPI_YWQ_URL_TEST = "http://123.56.26.178:8080/am";

	/**
	 * 医网签正式地址
	 */
	public final static String WAPI_YWQ_APPID_ONLINE = "2016031515245911";

	public final static String WAPI_YWQ_SECRET_ONLINE = "111111";

	public final static String WAPI_YWQ_URL_ONLINE = "http://www.yiwangqian.com/am";

	/**
	 * 商城首页地址 需拼接上用户id 测试
	 */

	public final static String WAPI_SHOP_TEST = "http://cmstest.ddoctor.cn/tyscms/admin.php?r=tyscms/DdoctorMall/index";

	public final static String WAPI_SHOP_ONLINE = "http://cms.ddoctor.cn/tyscms/admin.php?r=tyscms/DdoctorMall/index";
	//芝麻开花测试地址
//	public final static String WAPI_BUSINESS = "http://cmstest.ddoctor.cn/tyscms/events/zmkh/index.html";
	//芝麻开花正式地址
	public final static String WAPI_BUSINESS = "http://cms.ddoctor.cn/tyscms/events/zmkh/index.html";

}
