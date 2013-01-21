package com.dengbo.util;

public interface StringPoolUtil {

	/*
	 * 所有的action 字符串
	 */
	String GET_COOKIE = "com.dengbo.view.getCookie";
	String GET_CHECK_IMG = "com.dengbo.view.getCheckImg";
	String F5_CHECK_IMG = "com.dengbo.view.refreshCheckImg";
	String SEND_LOGIN = "com.dengbo.view.sendLogin";
	String SEND_LOGIN_AUTH = "com.dengbo.view.sendLoginAuth";
	String SEND_YUPIAOREQ = "com.dengbo.view.sendYupiaoReq";
	String CHECK_ORDER_UNPAID = "com.dengbo.view.checkOrderUnpaid";
	String GET_Order_TOKRN = "com.dengbo.view.getOrderToken";
	String QUERY_TICKET = "com.dengbo.view.queryTicket";
	String QUERY_BOOK = "com.dengbo.view.queryBook";
	String SUB_BOOK ="com.dengbo.view.subBook";
	String BOOK_IMG = "com.dengbo.view.bookImg";
	String F5_BOOK_IMG = "com.dengbo.view.F5BookImg";
	String ADD_PASSAGER = "com.dengbo.view.addPassager";
	String READ_PASSAGER ="com.dengbo.view.readPassager";



	// sharePrefrence key
	String SHARE_PREF = "share_pref";

	/*
	 * 数据库相关的字符串
	 */
	// 创建m表格的列名
	String KEY_NAME = "name";
	String KEY_ACTION = "action";
	String KEY_URL = "url";
	String KEY_PRO = "protocol";
	String KEY_METHOD = "method";

	// 表名
	String MODEL_TABLE = "model";
	String PARSE_TABLE = "parse";
	/*
	 * 错误原因
	 */
	String CHECK_IMG_ERROR = "checkImgError";
	String NAME_OR_PW_ERROR = "nameOrPwError";

	/*
	 * 所有的信息交互key,主要用于信息存在bundle中流动时，get和put使用同一份key，避免造成混乱
	 */
	String RESP_CHECK = "resp_check"; // 请求返回的验证码
	String ERROR = "error"; // 错误信息
	String ERROR_REASON = "error_reson"; // 错误原因

	// login 界面
	String LOGIN_ERROR = "loginerror"; // 登录请求auth中返回的error字符串

	String LOGIN = "login"; // 登录时发送数据
	String LOGIN_RAND = "loginrand"; // 登录请求中的loginrand参数已经auth中返回的loginrand字符串

	// home 界面
	String ORDER_TOKEN = "orderToken"; // 请求订单时获取token

	//query页面
	String QUERY_START = "queryStart";
	String QUERY_END = "queryEnd";
	String QUERY_DATE = "queryDate";
	String QUERY_TIME = "queryTime";
	String QUERY_KIND = "queryKind";
	String QUERY_CLASS = "queryClass";

	String QUERY_RESULT = "queryResult";
	String QUERY_ORDER_LINK = "queryOrderLink";

	//query result
	String TRAIN = "train";
	String START = "start";
	String END = "end";
	String TIME = "time";
	String NUM = "num";
	String IMG_S ="startImg";
	String IMG_E ="endImg";

	//book
	String BOOK = "book";
}
