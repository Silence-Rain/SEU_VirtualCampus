package common;

public abstract interface MessageTypes {

	public static final int TRANSFER_ACCOUNTS = 202;
	public static final int BALANCE_ACCOUNTS = 201;
	public static final int RECORD_ACCOUNTS = 203;
	
	public static final int SHOP_GOODS_QUERY = 501;
	public static final int SHOP_GOODS_QUERY_SUCCESS = 5011;
	public static final int SHOP_GOODS_QUERY_FAIL = 5012;
	public static final int SHOP_ADD_ADMIN = 511;
	public static final int SHOP_ADD_ADMIN_SUCCESS = 5111;
	public static final int SHOP_ADD_ADMIN_FAIL = 5112;
	public static final int SHOP_DELETE_ADMIN = 512;
	public static final int SHOP_DELETE_ADMIN_SUCCESS = 5121;
	public static final int SHOP_DELETE_ADMIN_FAIL = 5122;
	public static final int SHOP_MODIFY_ADMIN = 513;
	public static final int SHOP_MODIFY_ADMIN_SUCCESS = 5131;
	public static final int SHOP_MODIFY_ADMIN_FAIL = 5132;
	public static final int SHOP_ORDER_QUERY_ADMIN=514;
	public static final int SHOP_ORDER_QUERY_ADMIN_SUCCESS=5141;
	public static final int SHOP_ORDER_QUERY_ADMIN_FAIL=5142;
	public static final int SHOP_BUY_STUTEA = 521;
	public static final int SHOP_BUY_STUTEA_SUCCESS = 5211;
	public static final int SHOP_BUY_STUTEA_FAIL = 5212;
	public static final int SHOP_ORDER_QUERY_STUTEA=522;
	public static final int SHOP_ORDER_QUERY_STUTEA_SUCCESS=5221;
	public static final int SHOP_ORDER_QUERY_STUTEA_FAIL=5222;
	
}
