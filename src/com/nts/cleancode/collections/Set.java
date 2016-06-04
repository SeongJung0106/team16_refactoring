package com.nts.cleancode.collections;

public class Set extends AbstractCollection {
	public void add(Object element) {
		
		if (contains(element))
			return;
		
		super.add(element);
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