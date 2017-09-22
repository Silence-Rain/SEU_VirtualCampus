package seu.vCampus.bz;

import java.util.List;


public interface ILibraryAdmin {
	public abstract List inquireBook();//查询书籍
	public abstract boolean addBook(String isbn,String name,String author,String pub,int num);//添加书籍
	public abstract boolean deleteBook(int id);//删除库存书籍
	public abstract boolean modifyBook(int id,String isbn,String name,String author,String pub,boolean isBorrowed);//修改库存可借书籍为不可借
}
