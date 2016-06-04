package com.nts.cleancode.collections;

public abstract class AbstractCollection {
	
	protected static int INITIAL_CAPACITY = 10;
	protected Object[] elements = new Object[INITIAL_CAPACITY];
	protected int size = 0;
	protected boolean readOnly;

	public abstract boolean remove(Object element);
	
	public void addAll(AbstractCollection c) {
		if (c instanceof Set) {
			AbstractCollection s = (AbstractCollection)c;
			for (int i=0; i < s.size(); i++) {
				if (!contains(s.get(i))) {
					add(s.get(i));
				}
			}
			
		} else if (c instanceof List) {
			AbstractCollection l = (AbstractCollection)c;
			for (int i=0; i < l.size(); i++) {
				if (!contains(l.get(i))) {
					add(l.get(i));
				}
			}
		} else if (c instanceof Map) {
			Map m = (Map)c;
			for (int i=0; i<m.size(); i++) 
				add(m.keys[i], m.values[i]);			
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
}
