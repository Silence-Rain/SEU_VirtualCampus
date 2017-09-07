package seu.vCampus.common;


public abstract interface MsgType {
	/*
	 * All the commands are 3-digit integer
	 * First digit: Command type
	 * Second digit: Access authority (0: General, 1: Admin, 2: Student&teacher, 3: Student only, 4: Teacher only)
	 * Third digit: Command handler 
	 */
	public static final int LOGIN  = 101;
	public static final int REGISTER = 102;
	public static final int LOGOUT = 103;
	
	public static final int BANK_BALANCE_QUERY = 201;
	public static final int BANK_TRANSFER = 202;
	public static final int BANK_TRANSFER_RECORD_QUERY = 203;
	
	public static final int STUDENTROLL_INFO_QUERY = 301;
	public static final int STUDENTROLL_ADD_ADMIN = 311;
	public static final int STUDENTROLL_DELETE_ADMIN = 312;
	public static final int STUDENTROLL_MODIFY_ADMIN = 313;
	public static final int STUDENTROLL_MODIFY_STUTEA = 321;
	
	public static final int LIBRARY_BOOK_QUERY = 401;
	public static final int LIBRARY_ADD_ADMIN = 411;
	public static final int LIBRARY_DELETE_ADMIN = 412;
	public static final int LIBRARY_MODIFY_ADMIN = 413;
	public static final int LIBRARY_BORROW_STUTEA = 421;
	public static final int LIBRARY_RETURN_STUTEA = 422;
	public static final int LIBRARY_BORROWHISTORY_STUTEA = 423;
	
	public static final int SHOP_GOODS_QUERY = 501;
	public static final int SHOP_ADD_ADMIN = 511;
	public static final int SHOP_DELETE_ADMIN = 512;
	public static final int SHOP_MODIFY_ADMIN = 513;
	public static final int SHOP_BUY_STUTEA = 521;
	public static final int SHOP_EXPORT_ORDER_STUTEA = 522;
	
	public static final int COURSE_QUERY = 601;
	public static final int COURSE_ADD_ADMIN = 611;
	public static final int COURSE_DELETE_ADMIN = 612;
	public static final int COURSE_MODIFY_ADMIN = 613;
	public static final int COURSE_SELECT_STUDENT = 631;
	public static final int COURSE_DESELECT_STUDENT = 632;
	public static final int COURSE_CURRICULUM_STUDENT = 633;
	public static final int COURSE_QUERY_TEACHER = 641;
	
	public static final int APPOINT_ITEM_QUERY = 701;
	public static final int APPOINT_ITEM_ADD_ADMIN = 711;
	public static final int APPOINT_ITEM_DELETE_ADMIN = 712;
	public static final int APPOINT_ITEM_MODIFY_ADMIN = 713;
	public static final int APPOINT_ADD_STUTEA = 721;
	public static final int APPOINT_DELETE_STUTEA = 722;
	public static final int APPOINT_MODIFY_STUTEA = 723;
	public static final int APPOINT_RECORD_QUERY_STUTEA = 724;
	
}
