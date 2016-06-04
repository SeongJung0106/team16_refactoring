package com.nts.cleancode.collections;

public abstract class AbstractCollection {
	
	protected static int INITIAL_CAPACITY = 10;
	protected Object[] elements = new Object[INITIAL_CAPACITY];
	protected int size = 0;
	protected boolean readOnly;
	
	public void addAll(AbstractCollection collection) {
		for (int i=0; i < collection.size(); i++) {
			if (!contains(collection.get(i)))
				add(collection.get(i));
		}
	}

	public void add(Object key, Object value) {
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public boolean contains(Object element) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element))
				return true;
		return false;
	}
	public int size() {
		return size;
	}
	public int capacity() {
		return elements.length;
	}
	public void setReadOnly(boolean b) {
		readOnly = b;
	}
	public Object get(int index) {
		return elements[index];
	}
	public void add(Object element) {
		if (readOnly)
			return;
		
		if (shouldGrow()) 
			grow();
		
		addElement(element);
	}
	private void addElement(Object element) {
		elements[size++] = element;
	}
	private void grow() {
		Object[] newElements =
			new Object[elements.length + 10];
		for (int i = 0; i < size; i++)
			newElements[i] = elements[i];
		elements = newElements;
	}
	private boolean shouldGrow() {
		return size + 1 > elements.length;
	}

	public boolean remove(Object element) {
		if (readOnly)
			return false;
		
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element)) {
				removeElementAt(i);
				return true;
			}
		
		return false;
	}

	protected void removeElementAt(int i) {
		elements[i] = null;
		Object[] newElements = new Object[size - 1];
		int k = 0;
		for (int j = 0; j < size; j++) {
			if (elements[j] != null)
				newElements[k++] = elements[j];
		}
		size--;
		elements = newElements;
	}
}
