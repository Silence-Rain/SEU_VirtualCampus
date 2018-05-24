package database;

/**
 * 数据库操作（Model）抽象接口
 * 
 * @author Silence
 *
 */
public abstract interface Model {
	public abstract boolean insert(Object obj);
	public abstract boolean modify(Object obj);
	public abstract boolean delete(Object obj);
	public abstract Object search(Object obj);
}
