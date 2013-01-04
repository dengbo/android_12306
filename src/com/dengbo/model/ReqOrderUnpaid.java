package com.dengbo.model;

import android.os.Bundle;

public class ReqOrderUnpaid extends ReqNetDate {
	@Override
	public void initHeadParam() {
		super.initHeadParam();
		headParamHashMap.put("accept", "text/html, application/xhtml+xml, */*");
	}

	@Override
	public void initParam(Bundle mBundle) {
		super.initParam(mBundle);
		// paramHashMap.put("method", "queryMyOrderNotComplete");
		param.append("method=queryMyOrderNotComplete&leftmenu=Y");
	}
}
