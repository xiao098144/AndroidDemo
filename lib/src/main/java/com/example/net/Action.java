package com.example.net;

public class Action
{
	public final static int INIT_CLIENT = 10101;// 客户端初始化

	public final static int GET_CLIENT_UPDATE = 10102;// 版本升级

	public final static int GET_CLIENT_ABOUT = 10103;// 关于信息获取

	public final static int SEND_VERIFY_SMS = 10104;// 发送短信验证码

	public final static int ADD_RECOMENT = 60202;// 推荐好友

	public final static int UPLOAD_FILE = 10106;// 上传文件

	public final static int DELETE_RECORD = 10107;// 删除记录

	public final static int GET_BANNER_LIST = 10109;// 查询banner列表

	public final static int GET_RECORD_TOTAL = 10108;// 查询记录总天数与总记录数

	public final static int DELETE_RECORDS = 10110;// 删除记录（一个或批量）

	public final static int ADD_FEEDBACK = 101010; // 用户反馈

	public final static int GET_KNOWLEGE_CATAGORY = 10201;// 查询知识库目录

	public final static int GET_KNOWLEGE_LIST = 10202;// 查看知识库列表

	public final static int GET_KNOWLEGE = 10203;// 查看知识库详情

	public final static int GET_KNOWLEGE_SEARCH = 10204;// 搜索知识库文章

	public final static int ADD_COLLECTION = 10205;// 添加收藏记录

	public final static int GET_COLLECTION_LIST = 10206;// 查看收藏列表

	public final static int ADD_SHARE = 10207;// 添加文章分享记录

	public final static int GET_KNOWLEGE_CATEGORY_CONTENT_LIST = 10211;// 知识库首页(患者端)

	public final static int EMPTY_COLLECTIONS = 10212;// 一键清空收藏列表

	public final static int IS_KNOWLEDGE_COLLECTED = 10213; // 知识库文章是否已经收藏

	public final static int GET_POINT_TOTAL = 10301;// 查询积分余额

	public final static int GET_POINT_LIST = 10302;// 查看积分列表

	public final static int UNIFIED_ORDER = 10401;// 微信支付获取package

	public final static int GET_WECHAT_SIGN = 10402;// 微信支付获取sign

	public final static int GET_MIO_BOX_GLUCOSEMETER = 10501; // 盒子支持的血糖仪

	public final static int MIO_BOX_BINDING = 10502; // 绑定盒子

	public final static int MIO_BOX_BINDING_CANCEL = 10503; // 解绑

	public final static int MIO_BOX_BINDING_STATE = 10504; // 查询盒子绑定状态

	public final static int VERIFICATION_BINDING_DEVICE = 10511; // 验证绑定设备

	public final static int DO_BINGDING_DEVICE = 10512; // 添加绑定设备记录

	public final static int DO_CHATROOM = 10601; // 后台创建/修改聊天室

	public final static int GET_CHATROOM_LIST = 10602; // 查询聊天室列表

	public final static int ADD_GROUP = 10605; // 创建群

	public final static int PULL_KICK_GROUP = 10606; // 拉人入群/踢人出群

	public final static int REMOVE_GROUP = 10607; // 解散群

	public final static int UPDATE_GROUP = 10608; // 编辑群资料

	public final static int GET_GROUP_MEMBER = 10609; // 查询群成员列表

	public final static int CHANGE_GROUP_OWNER = 10610; // 移交群主

	public final static int UPDATE_TEAM_NICK = 10611; // 修改群昵称

	public final static int UPDATE_MUTETEAM = 10612; // 修改消息提醒开关

	public final static int GET_GROUP_MEMBER_DETAIL = 10613; // 获取群成员信息

	public final static int ADD_FRIENDS = 10615; // 添加好友

	public final static int GET_FRIENDS_LIST = 10616; // 查询我的好友列表

	public final static int GET_FRIENDS_DETAIL = 10617; // 查询好友信息

	public final static int DELETE_FRIENDS = 10618; // 删除我的好友

	public final static int SET_FRIENDS_SHOW = 10619; // 好友显示设置

	public final static int GET_TYQ_FRIENDS_LIST = 10620; // 查询糖友圈可聊友列表

	public final static int GET_RECOMMEND_LIST = 10625;// 查询推荐商品/文章

	public final static int GETT_MY_FAVORITE = 10626; // 查询我的收藏

	public final static int SEARCH_QZ = 10630; // 搜索好友，圈子或聊天室

	public final static int GET_CHATROOM_MESSAGE_LIST = 10631; // 获取聊天室消息列表 分页

	public final static int DO_SIGN = 10901;// 签到

	public final static int MESSAGES_SYS_EXCEPTION = 11109;// 获取系统与异常消息

	public final static int GET_FAQ_CATEGORY_LIST = 11201;// 查询常见问题分类

	public final static int GET_FAQ_LIST = 11202;// 查询常见问题与解答列表

	public final static int GET_FAQ_DETAIL = 11203;// 查询常见问题与解答详情

	public final static int DO_FAQ_ANSWER = 11204;// 提交解答(医生)

	public final static int XM_USER_BINDING = 11401;// 小米用户绑定

	public final static int XM_USER_FIRSTTONGBU = 11402;// 小米数据第一次同步

	public final static int XM_USER_TONGBU = 11404;// 同步数据

	public final static int XM_USER_JIEBANG = 11405;// 解除绑定

	public final static int BIND_LINNAI = 11701; // 绑定林奈

	public final static int YWT_PAYWAY = 11901; // 招商一网通支付接口

	public final static int YWT_MCCODE = 11902; // 获取招商校验码

	public final static int YWT_QQRECORD = 11903; // 招商一网通支付请求记录

	public final static int SCAN_SCAN = 12001; // 扫一扫

	public final static int GET_CMB_DISCOUNT_INFO = 21421; // 查询一网通支付优惠信息

	public final static int PATIENT_REGISTER = 20101;// 患者注册

	public final static int PATIENT_LOGIN = 20102;// 患者登录

	public final static int PATIENT_QUICK_LOGIN = 20103;// 患者快捷登录

	public final static int PATIENT_UPDATE_PASSWORD = 20104;// 患者重置密码

	public final static int PATIENT_BINDING_MOBILE = 20105;// 患者绑定手机号码

	public final static int GET_PATIENT = 20106;// 查询患者信息

	public final static int UPDATE_PATIENT = 20107;// 修改患者信息

	public final static int GET_SUGARVALUE_LIST = 20201;// 查询血糖记录

	public final static int DO_SUGARVALUE = 20202;// 添加/修改血糖记录

	public final static int ADD_GLUCOMETER = 20203;// 添加/设置血糖仪

	public final static int GET_SUGARVALUE_MONTH = 20204;// 查询月血糖分析记录

	public final static int GET_UPLOWVALUE = 20205;// 查询用户血糖上下限

	public final static int UPDATE_UPLOWVALUE = 20206;// 修改用户血糖上下限

	public final static int GET_EVALUATION = 20207; // 计算健康报告

	public final static int GET_SUGAR_STATISTICS_VALUE = 20209; // 获取血糖统计信息

	public final static int GET_PATIENT_GLUCOMETER_LIST = 20210; // 查询常用血糖仪列表

	public final static int DO_BATCH_SUGARVALUE = 20211; // 批量添加/修改血糖记录

	public final static int UPDATE_SUGAR_TIMETYPE = 20212; // 修改血糖记录时段
	public final static int SYNC_SGBL = 20213; // 同步瞬感伴侣血糖记录

	public final static int DO_PATIENT_ASSESSMENT = 21601; // 添加/修改健康评估结果

	public final static int GET_HEALTH_ASSESSMENT_LIST = 21602; // 查询健康评估记录列表

	public final static int GET_MEDICALRECORD_LIST = 20301;// 查询用药记录

	public final static int DO_MEDICALRECORD = 20302;// 添加用药记录

	public final static int GET_DIETRECORD_LIST = 20401;// 查询饮食记录列表

	public final static int DO_DIETRECORD = 20402;// 添加/修改饮食记录

	public final static int DO_DIETRECORD_NEW = 20405;// 添加/修改饮食记录(避免老版本冲突)

	public final static int GET_DIETRECORD = 20403;// 查询饮食记录详情

	public final static int GET_DIETITIAN = 20404;// 获取营养师

	public final static int GET_SPORTRECORD_LIST = 20501;// 查询运动记录列表

	public final static int ADD_SPORTRECORD = 20502;// 添加运动记录

	public final static int GET_LAST_AND_NOW_SPORTRECORD = 20503;// 查询最近一次和今天的运动记录

	public static final int FETCH_SPORTSTYPELIST = 20504; // 请求吃动平衡各项运动消耗热量所需时间列表

	public static final int FETCH_TODAY_SPORT_RECORD = 20505; // 请求吃动平衡各项运动消耗热量所需时间列表

	public final static int GET_BLOODPRESSURE_LIST = 20601;// 查询血压记录列表

	public final static int DO_BLOODPRESSURE = 20602;// 添加/修改血压记录

	public final static int GET_HEIGHT_LIST = 20701;// 查询身高体重记录列表

	public final static int DO_HEIGHT = 20702;// 添加/修改身高体重记录

	public final static int GET_TROUBLERECORD_LIST = 20801;// 查询不适记录列表

	public final static int DO_TROUBLERECORD = 20802;// 添加/修改不适记录

	public final static int GET_HYDCFYZ_LIST = 20901;// 查询化验单/处方医嘱列表

	public final static int ADD_HYDCFYZ = 20902;// 添加化验单/处方医嘱记录

	public final static int GET_SUGARPROTEIN_LIST = 21001;// 查询糖化血红蛋白记录列表

	public final static int DO_SUGARPROTEIN = 21002;// 添加/修改糖化血红蛋白记录

	public final static int GET_DISEASE_LIST = 21101;// 查询病历列表

	public final static int DO_DISEASE = 21102;// 添加/修改病历

	public final static int GET_PUBLICQUESTION_PATIENT_LIST = 21201;// 查询公共区提问列表

	public final static int GET_PUBLICQUESTION_PATIENT_SEARCH = 21202;// 搜索公共区提问列表

	public final static int GET_PUBLICQUESTION_PATIENT_MINE = 21203;// 查询我的提问与回复列表

	public final static int GET_PUBLICQUESTION_PATIENT_REPLY = 21204;// 查看提问详情及回复列表

	public final static int GET_DOCTOR_LIST = 21205;// 查询医生列表

	public final static int GET_DOCTOR_SEARCH = 21206;// 搜索医生列表

	public final static int SCORE_DOCTOR = 21207;// 为医生评星

	public final static int ADD_DOCTOR_PATIENT = 21208;// 申请添加医生 ...此为补填

	public final static int ADD_QUESTION = 21209;// 添加问医记录

	public final static int ADD_QUESTION_REPLY = 21210;// 添加问医的问题回复

	public final static int GET_LATELY_DOCTOR = 21212; // 查询最近添加的医生

	public final static int GET_ONLINE_DOCTOR_LIST = 21214;// 快速在线问医医生列表

	public final static int DO_ONLINE_CONSULTATION = 21215;// 快速在线问医记录

	public final static int GET_FAMOUS_DOCTOR_LIST = 21216;// 推荐名医

	public final static int GET_TYS_TEAM = 21217;// 快速在线问医记录

	public final static int GET_ESSENCE_QUESTION = 21218;// 精华问题列表

	public final static int ADD_QUESTIONJBZL = 21303;// 提交基本调查信息

	public final static int GET_PRODUCT_LIST = 21401;// 查询商品列表

	public final static int GET_PRODUCT = 21402;// 查询商品详情

	public final static int GET_PAYPREPARE = 21403;// 订单支付前的确认

	public final static int ADD_BUYCART = 21404;// 同步购物车记录

	public final static int GET_ORDER_LIST = 21405;// 查询订单列表

	public final static int GET_ORDER = 21406;// 查询订单详情

	public final static int ADD_ORDER = 21407;// 新增订单

	public final static int UPDATE_ORDER = 21408;// 支付状态同步

	public final static int GET_DELIVER_LIST = 21409;// 查询收货地址列表

	public final static int GET_DELIVER = 21410;// 查询收货地址详情

	public final static int DO_DELIVER = 21411;// 添加/修改收货地址

	public final static int GET_VOUCHER_LIST = 21412; // 查询个人优惠券列表

	public final static int GET_MYDIT_HEAT_LIST = 101011; // 饮食能量列表

	public final static int GET_PRODUCT_CATEGORY_LIST = 21413;// 新版分类列表

	public final static int GET_PRODUCT_EXTEND_LIST = 21414;// 新版商品列表

	public final static int GET_BRAND_PRODUCT_LIST = 21415;// 品牌馆详情列表

	public final static int GET_PRODUCT_LIST_SERACH = 21416;// 商城搜索

	public final static int ADD_SERVICE_ORDER = 21417;// 新增服务订单

	public final static int GET_BLOODFAT_LIST = 21501;// 查询血脂记录列表

	public final static int DO_BLOODFAT = 21502;// 添加/修改血脂记录

	public final static int DO_PATIENT_ECG = 21701; // 添加/修改心电记录

	public final static int GET_ECG_LIST = 21702; // 查询心电记录列表

	public final static int GET_MY_RECORD_NUM = 21802; // 查询个人记录总数

	public final static int GET_TASK_POINT_LIST = 21803; // 查询我的任务列表

	public final static int GET_HEALTH_RECORD = 21804; // 查看健康档案

	public final static int GET_HEALTHEXAM = 21805; // 体检参数

	public final static int GET_HOME_TASK_LIST = 21806; // 查询首页今日任务

	public final static int DOCTOR_REGISTER = 30101;// 医生注册

	public final static int DOCTOR_LOGIN = 30102;// 医生登录

	public final static int DOCTOR_QUICK_LOGIN = 30103;// 医生快捷登录

	public final static int DOCTOR_UPDATE_PASSWORD = 30104;// 医生重置密码

	public final static int DOCTOR_BINDING_MOBILE = 30105;// 医生绑定手机号码

	public final static int GET_DOCTOR = 30106;// 医生查询个人信息

	public final static int UPDATE_DOCTOR = 30107;// 医生修改个人信息

	public final static int PATIENT_REVIEW_DOCTOR = 30111;// 患者给予医生评价及星评

	public final static int GET_DOCTOR_REVIEW_DETAIL = 30112;// 查看所有医生的评论

	public final static int GET_UNCONFIRMED_PATIENT_LIST = 30201;// 查询等待确认添加的患者列表

	public final static int GET_PATIENT_LIST = 30202;// 查询我的患者列表

	public final static int GET_PATIENT_SEARCH_LIST = 30203;// 搜索我的患者列表

	public final static int DO_PATIENT_DOCTOR_RELATION = 30204;// 设置医患关系

	public final static int GET_SUGARVALUE_MONTH_LIST = 30205;// 查询我的患者月血糖分析记录列表

	public final static int GET_PATIENT_LOG_COUNT = 30206;// 查询患者所有日志记录

	public final static int GET_PATIENT_LOG = 30207;// 查询患者日志记录详情

	public final static int GET_RELATION_DISEASE_LIST = 30208;// 查询医生记录的患者病历列表

	public final static int ADD_DOCTOR_DISEASE = 30209;// 医生添加/修改患者病历

	public final static int GET_PUBLICQUESTION_DOCTOR_LIST = 30301;// 按回复查看患者公共提问列表

	public final static int GET_PUBLICQUESTION_DOCTOR_REPLY = 30302;// 查看医生的已回复列表

	public final static int ADD_REPLY = 30303;// 回复公共区提问

	public final static int GET_RECOMMENT_LIST = 30401;// 查询医生推荐过的患者列表

	public final static int GET_OUTPATIENT = 30402;// 查询医生的我的门诊

	public final static int UPDATE_OUTPATIENT = 30403;// 修改医生的我的门诊

	public final static int GET_WORK_DIARY_LIST = 30421;// 查询医生工作日记列表

	public final static int GET_MYRECORD_CORD = 30500; // 我的记录

	public final static int COMPLETE_TSL_PATIENT = 50101; // 完善医保信息

	public final static int ADD_TSL_PRESCRIPTION = 50102; // 添加处方

	public final static int GET_PRESCRIPTION_LIST = 50103; // 获取处方列表

	public final static int GET_PRESCRIPTION_DETAIL = 50104; // 查询单个处方详情

	public final static int GET_PRESCRIPTION_PRODUCT_LIST = 50105; // 获取某个人的所有处方下的药品

	public final static int GET_TSL_ORDER_LIST = 50106; // 获取订单列表

	public final static int ADD_TSL_ORDER = 50107; // 添加订单

	public final static int ADD_TSL_ADDRESS = 50108; // 添加tsl收货地址

	public final static int UPDATE_TSL_ADDRESS = 50109;// 修改tsl收货地址

	public final static int GET_TSL_ADDRESS = 50110; // 获取tsl收货地址

	public final static int DELETE_TSL_ADDRESS = 50111;// 删除tsl收货地址

	public final static int SEND_TSL_VERIFICATIONCODE = 50112;// 发送短信验证码

	public final static int GET_TSL_ORDER_DETAIL = 50113; // 获取订单详情

	public final static int GET_RONGYUN = 60101;// 获取融云token

	public final static int GET_KNOWLEGECONTENT_LIST = 60102; // 获取知识库列表

	public final static int GET_KNOWLEGECONTENT = 60103; // 获取知识库详情

	public final static int GET_KNOWLEGECONTENT_SEARCH = 60104; // 搜索知识库列表

	public final static int GET_COLLECTION_CONTENT_LIST = 60106; // 我的收藏

	public final static int GET_COLLECTION_KNOWLEGE_CONTENT = 60107;// 新版查看收藏列表的知识库详情

	public final static int CHANGE_ACCOUNT_MOBILE = 60201; // 更改绑定手机号

	public final static int GET_MY_RECOMMENT_LIST = 60203; // 推荐过的记录

	/**
	 * 根据云信id获取用户信息 可能医生 也可能用户 根据userType判断
	 */
	public final static int GET_INFO_BYNIMID = 60204;

	// 抽流量
	public final static int GET_TODAY_TIMES = 10801; // 查询今天是否抽过

	public final static int ADD_FLOW = 10802; // 抽流量

	public final static int GET_FLOWINFO_AND_LIST = 10803; // 查询流量信息和明细

	public final static int RECHARGE_FLOW = 10804; // 流量充值

	/**
	 * 会话消息首页 获取系统消息 和 添加消息 11101
	 */
	public final static int MESSAGES_SESSION_HOME = 11109;

	/**
	 * 糖友圈被添加消息
	 */
	public final static int MESSAGES_SESSION_TYQ = 11108;

	/**
	 * 系统消息列表 11102
	 */
	public final static int MESSAGES_SYS_LIST = 11102;

	/**
	 * 添加消息列表 11103
	 */
	public final static int MESSAGES_ADD_MSG_LIST = 11103;

	/**
	 * 异常消息列表 所有患者的异常信息列表 11104
	 */
	public final static int MESSAGES_ABNORMAL_LIST = 11104;

	/**
	 * 具体某个患者异常消息列表 11105
	 */
	public final static int MESSAGES_ABNORMAL_DETAIL = 11105;

	/**
	 * 更新添加消息已读 11106
	 */
	public final static int MESSAGES_READ = 11106;

	/**
	 * 是否有未读消息 11107
	 */
	public final static int HAVENEWMESSAGE = 11107;

	/**
	 * 查询推荐商品/文章
	 */
	public final static int TYQ_GET_RECOMMEND_LIST = 10625;

}
