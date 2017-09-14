package common;

public abstract interface MsgType {
	/*
	 * All the commands are 3 or 4-digit integer
	 * First digit: Command type
	 * Second digit: Access authority (0: General, 1: Admin, 2: Student&teacher, 3: Student only, 4: Teacher only)
	 * Third digit: Command handler 
	 * Fourth digit (if exists): Command status (1: success, 2: fail)
	 */
	public static final int LOGIN  = 101;
	public static final int LOGIN_SUCCESS = 1011;
	public static final int LOGIN_FAIL = 1012;
	public static final int REGISTER = 102;
	public static final int REGISTER_SUCCESS = 1021;
	public static final int REGISTER_FAIL = 1022;
	public static final int LOGOUT = 103;
	public static final int LOGOUT_SUCCESS = 1031;
	public static final int LOGOUT_FAIL = 1032;
	
	public static final int BANK_BALANCE_QUERY = 201;
	public static final int BANK_BALANCE_QUERY_SUCCESS = 2011;
	public static final int BANK_BALANCE_QUERY_FAIL = 2012;
	public static final int BANK_TRANSFER = 202;
	public static final int BANK_TRANSFER_SUCCESS = 2021;
	public static final int BANK_TRANSFER_FAIL = 2022;
	public static final int BANK_TRANSFER_RECORD_QUERY = 203;
	public static final int BANK_TRANSFER_RECORD_SUCCESS = 2031;
	public static final int BANK_TRANSFER_RECORD_FAIL = 2032;
	
	public static final int STUDENTROLL_INFO_QUERY = 301;
	public static final int STUDENTROLL_INFO_QUERY_SUCCESS = 3011;
	public static final int STUDENTROLL_INFO_QUERY_FAIL = 3012;
	public static final int STUDENTROLL_ADD_ADMIN = 311;
	public static final int STUDENTROLL_ADD_ADMIN_SUCCESS = 3111;
	public static final int STUDENTROLL_ADD_ADMIN_FAIL = 3112;
	public static final int STUDENTROLL_DELETE_ADMIN = 312;
	public static final int STUDENTROLL_DELETE_ADMIN_SUCCESS = 3121;
	public static final int STUDENTROLL_DELETE_ADMIN_FAIL = 3122;
	public static final int STUDENTROLL_MODIFY_ADMIN = 313;
	public static final int STUDENTROLL_MODIFY_ADMIN_SUCCESS = 3131;
	public static final int STUDENTROLL_MODIFY_ADMIN_FAIL = 3132;
	public static final int STUDENTROLL_MODIFY_STUTEA = 321;
	public static final int STUDENTROLL_MODIFY_STUTEA_SUCCESS = 3211;
	public static final int STUDENTROLL_MODIFY_STUTEA_FAIL = 3212;
	
	public static final int LIBRARY_BOOK_QUERY = 401;
	public static final int LIBRARY_BOOK_QUERY_SUCCESS = 4011;
	public static final int LIBRARY_BOOK_QUERY_FAIL = 4012;
	public static final int LIBRARY_ADD_ADMIN = 411;
	public static final int LIBRARY_ADD_ADMIN_SUCCESS = 4111;
	public static final int LIBRARY_ADD_ADMIN_FAIL = 4112;
	public static final int LIBRARY_DELETE_ADMIN = 412;
	public static final int LIBRARY_DELETE_ADMIN_SUCCESS = 4121;
	public static final int LIBRARY_DELETE_ADMIN_FAIL = 4122;
	public static final int LIBRARY_MODIFY_ADMIN = 413;
	public static final int LIBRARY_MODIFY_ADMIN_SUCCESS = 4131;
	public static final int LIBRARY_MODIFY_ADMIN_FAIL = 4132;
	public static final int LIBRARY_BORROW_STUTEA = 421;
	public static final int LIBRARY_BORROW_STUTEA_SUCCESS = 4211;
	public static final int LIBRARY_BORROW_STUTEA_FAIL = 4212;
	public static final int LIBRARY_RETURN_STUTEA = 422;
	public static final int LIBRARY_RETURN_STUTEA_SUCCESS = 4221;
	public static final int LIBRARY_RETURN_STUTEA_FAIL = 4222;
	public static final int LIBRARY_BORROWHISTORY_STUTEA = 423;
	public static final int LIBRARY_BORROWHISTORY_STUTEA_SUCCESS = 4231;
	public static final int LIBRARY_BORROWHISTORY_STUTEA_FAIL = 4232;
	
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
	public static final int SHOP_ORDER_QUERY_STUTEA = 522;
	public static final int SHOP_ORDER_QUERY_STUTEA_SUCCESS = 5221;
	public static final int SHOP_ORDER_QUERY_STUTEA_FAIL = 5222;	
	
	public static final int COURSE_QUERY = 601;
	public static final int COURSE_QUERY_SUCCESS = 6011;
	public static final int COURSE_QUERY_FAIL = 6012;
	public static final int COURSE_ADD_ADMIN = 611;
	public static final int COURSE_ADD_ADMIN_SUCCESS = 6111;
	public static final int COURSE_ADD_ADMIN_FAIL = 6112;
	public static final int COURSE_DELETE_ADMIN = 612;
	public static final int COURSE_DELETE_ADMIN_SUCCESS = 6121;
	public static final int COURSE_DELETE_ADMIN_FAIL = 6122;
	public static final int COURSE_MODIFY_ADMIN = 613;
	public static final int COURSE_MODIFY_ADMIN_SUCCESS = 6131;
	public static final int COURSE_MODIFY_ADMIN_FAIL = 6132;
	public static final int COURSE_SELECT_STUDENT = 631;
	public static final int COURSE_SELECT_STUDENT_SUCCESS = 6311;
	public static final int COURSE_SELECT_STUDENT_FAIL = 6312;
	public static final int COURSE_DESELECT_STUDENT = 632;
	public static final int COURSE_DESELECT_STUDENT_SUCCESS = 6321;
	public static final int COURSE_DESELECT_STUDENT_FAIL = 6322;
	public static final int COURSE_CURRICULUM_STUDENT = 633;
	public static final int COURSE_CURRICULUM_STUDENT_SUCCESS = 6331;
	public static final int COURSE_CURRICULUM_STUDENT_FAIL = 6332;
	public static final int COURSE_QUERY_TEACHER = 641;
	public static final int COURSE_QUERY_TEACHER_SUCCESS = 6411;
	public static final int COURSE_QUERY_TEACHER_FAIL = 6412;
	
	public static final int APPOINT_ITEM_QUERY = 701;
	public static final int APPOINT_ITEM_QUERY_SUCCESS = 7011;
	public static final int APPOINT_ITEM_QUERY_FAIL = 7012;
	public static final int APPOINT_ITEM_ADD_ADMIN = 711;
	public static final int APPOINT_ITEM_ADD_ADMIN_SUCCESS = 7111;
	public static final int APPOINT_ITEM_ADD_ADMIN_FAIL = 7112;
	public static final int APPOINT_ITEM_DELETE_ADMIN = 712;
	public static final int APPOINT_ITEM_DELETE_ADMIN_SUCCESS = 7121;
	public static final int APPOINT_ITEM_DELETE_ADMIN_FAIL = 7122;
	public static final int APPOINT_ITEM_MODIFY_ADMIN = 713;
	public static final int APPOINT_ITEM_MODIFY_ADMIN_SUCCESS = 7131;
	public static final int APPOINT_ITEM_MODIFY_ADMIN_FAIL = 7132;
	public static final int APPOINT_ADD_STUTEA = 721;
	public static final int APPOINT_ADD_STUTEA_SUCCESS = 7211;
	public static final int APPOINT_ADD_STUTEA_FAIL = 7212;
	public static final int APPOINT_DELETE_STUTEA = 722;
	public static final int APPOINT_DELETE_STUTEA_SUCCESS = 7221;
	public static final int APPOINT_DELETE_STUTEA_FAIL = 7222;
	public static final int APPOINT_MODIFY_STUTEA = 723;
	public static final int APPOINT_MODIFY_STUTEA_SUCCESS = 7231;
	public static final int APPOINT_MODIFY_STUTEA_FAIL = 7232;
	public static final int APPOINT_RECORD_QUERY_STUTEA = 724;
	public static final int APPOINT_RECORD_QUERY_STUTEA_SUCCESS = 7241;
	public static final int APPOINT_RECORD_QUERY_STUTEA_FAIL = 7242;
	
}
