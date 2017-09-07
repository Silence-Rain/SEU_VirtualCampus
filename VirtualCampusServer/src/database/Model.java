package database;

public abstract interface Model {
	public abstract boolean insert(Object obj);
	public abstract boolean modify(Object obj);
	public abstract boolean delete(Object obj);
	public abstract Object search(Object obj);
}
