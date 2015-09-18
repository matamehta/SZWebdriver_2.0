package com.bizrate.common;

import java.util.ArrayList;
import java.util.List;

public class Tokens {

	// Helper helper;
	List<String> tokenList;

	public Tokens() {
		// helper=new Helper();
	}

	public List<String> getAllTokens(Helper helper) {
		tokenList = new ArrayList<String>();
		tokenList.add(helper.t7s);
		tokenList.add(helper.t7y);
		tokenList.add(helper.t10j);
		tokenList.add(helper.t8b);
		tokenList.add(helper.t8b_ib);
		tokenList.add(helper.t8n);
		tokenList.add(helper.t8n_nopdt);
		tokenList.add(helper.t8nr);
		tokenList.add(helper.t8sm);
		tokenList.add(helper.t8no);
		tokenList.add(helper.t8f);
		tokenList.add(helper.t7x);
		tokenList.add(helper.t7xc);
		tokenList.add(helper.tmlt);
		tokenList.add(helper.tarl);
		tokenList.add(helper.t7t);
		tokenList.add(helper.t8f);
		tokenList.add(helper.t12);
		tokenList.add(helper.t12_openbox);
		tokenList.add(helper.t3h);
		tokenList.add(helper.t3h_amz);
		tokenList.add(helper.t8i);
		tokenList.add(helper.t7r);
		tokenList.add(helper.t7r_old);
		tokenList.add(helper.t7r_etrx);
		tokenList.add(helper.t7r_garden);
		tokenList.add(helper.t7r_clothes);
		tokenList.add(helper.t7r_sports);
		tokenList.add(helper.t7r_computer);
		tokenList.add(helper.t7r_kids);
		tokenList.add(helper.t7r_appliance);
		tokenList.add(helper.t2);
		tokenList.add(helper.t10g);
		tokenList.add(helper.t6b);
		tokenList.add(helper.t6b_old2);
		tokenList.add(helper.t6c);
		tokenList.add(helper.t6d);
		tokenList.add(helper.t6e);
		tokenList.add(helper.t6e_norating);
		tokenList.add(helper.t6e_p2010);
		tokenList.add(helper.tcdl2);
		tokenList.add(helper.t13w);
		tokenList.add(helper.t13s);
		tokenList.add(helper.tfsv);
		tokenList.add(helper.trt1);
		tokenList.add(helper.tfrv);
		tokenList.add(helper.t98);
		tokenList.add(helper.t20);
		tokenList.add(helper.t3j);
		tokenList.add(helper.t15k);
		tokenList.add(helper.t12f);
		tokenList.add(helper.te5);
		tokenList.add(helper.te5_1);
		tokenList.add(helper.t14z);
		tokenList.add(helper.t31);
		tokenList.add(helper.tes1);
		tokenList.add(helper.t33);
		tokenList.add(helper.t15z);
		tokenList.add(helper.t13v);
		tokenList.add(helper.t25);
		tokenList.add(helper.t7r_mature);
		tokenList.add(helper.t7x_mature);
		tokenList.add(helper.t8b_mature);
		tokenList.add(helper.t7y_mature);
		tokenList.add(helper.t12f_mature);
		tokenList.add(helper.t8nG);
		tokenList.add(helper.tmltG);
		tokenList.add(helper.t10jG);
		tokenList.add(helper.t7sG);
		tokenList.add(helper.t7xG);
		tokenList.add(helper.t7yG);
		tokenList.add(helper.t8bG);
		tokenList.add(helper.t8fG);
		tokenList.add(helper.t8nrG);
		tokenList.add(helper.trt1G);
		tokenList.add(helper.t8b_matureG);
		// tokenList.add(helper.t8i);
		// tokenList.add(helper.tbong);
		tokenList.add(helper.tmkt);

		return tokenList;
	}

	public List<String> getHotSRPTokensForSanity(Helper helper) {
		tokenList = new ArrayList<String>();
		tokenList.add(helper.t7s);
		tokenList.add(helper.t12);
		tokenList.add(helper.t7r);
		tokenList.add(helper.t8b);
		return tokenList;
	}

	public List<String> getAllTokensOnce(Helper helper) {
		tokenList = new ArrayList<String>();
		tokenList.add(helper.t7s);
		tokenList.add(helper.t7y);
		tokenList.add(helper.t10j);
		tokenList.add(helper.t8b);
		tokenList.add(helper.t8n);
		tokenList.add(helper.t8n_nopdt);
		tokenList.add(helper.t8nr);
		tokenList.add(helper.t8no);
		tokenList.add(helper.t8f);
		tokenList.add(helper.t7x);
		tokenList.add(helper.t7xc);
		tokenList.add(helper.tmlt);
		tokenList.add(helper.tarl);
		tokenList.add(helper.t7t);
		tokenList.add(helper.t12);
		tokenList.add(helper.t3h);
		tokenList.add(helper.t8i);
		tokenList.add(helper.t7r);
		tokenList.add(helper.t7r_old);
		tokenList.add(helper.t10g);
		tokenList.add(helper.t6b);
		tokenList.add(helper.t6b_old2);
		tokenList.add(helper.t6c);
		tokenList.add(helper.t6d);
		tokenList.add(helper.t6e);
		tokenList.add(helper.tcdl2);
		tokenList.add(helper.t13w);
		tokenList.add(helper.t13s);
		tokenList.add(helper.tfsv);
		tokenList.add(helper.trt1);
		tokenList.add(helper.tfrv);
		tokenList.add(helper.t98);
		tokenList.add(helper.t15k);
		tokenList.add(helper.t12f);
		tokenList.add(helper.te5);
		tokenList.add(helper.t14z);
		tokenList.add(helper.t31);
		tokenList.add(helper.tes1);
		tokenList.add(helper.t33);
		tokenList.add(helper.t15z);
		tokenList.add(helper.t13v);
		tokenList.add(helper.t25);
		tokenList.add(helper.t7r_mature);
		tokenList.add(helper.t7x_mature);
		tokenList.add(helper.t8b_mature);
		tokenList.add(helper.t7y_mature);
		tokenList.add(helper.t12f_mature);
		tokenList.add(helper.t8i);
		tokenList.add(helper.tmkt);

		return tokenList;
	}

}
