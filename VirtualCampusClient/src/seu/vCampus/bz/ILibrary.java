package seu.vCampus.bz;
import java.util.List;

import common.BookInfo;
import common.BookStatusInfo;
public abstract interface ILibrary {
	public abstract List EnquiryAllBook(BookInfo book);//�������е��鼮
	public abstract boolean BorrowBook(BookStatusInfo book);//�����鼮
	public abstract List EnquiryRecord(BookStatusInfo book);//�鿴���ļ�¼
	public abstract boolean ReturnBook(BookStatusInfo book);//����
	
	
	
}
