package com.dengbo.model;

public class ReqCheckImg extends ReqNetDate{

	@Override
	public void initHeadParam() {
		// TODO Auto-generated method stub
		super.initHeadParam();
		headParamHashMap.put("Accept", "image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5");
	}
}
