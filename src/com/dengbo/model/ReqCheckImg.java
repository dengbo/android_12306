package com.dengbo.model;


public class ReqCheckImg extends ReqNetDate{

//	public ReqCheckImg() {
//		// TODO Auto-generated constructor stub
//		super();
//		register();
//	}

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept", "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5");
		headParamHashMap.put("Content-Type", "application/x-www-form-urlencoded");
	}

//	//所有的子类重载该方法，调用initModel的register
//	public static void register() {
//		// TODO Auto-generated method stub
//		InitModel.register("ReqCheckImg", StringPoolUtil.GET_CHECK_IMG, StringPoolUtil.f5CheckImgString, "https", "GET");
//	}
}
