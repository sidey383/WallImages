package ru.sidey383.wallimages;

public interface Callback<T> {

	public void callback(T data);

	public default void exception(Exception ex) {}
	
}
