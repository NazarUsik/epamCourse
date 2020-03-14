������������ ������� �2
_______________________

���������. ��������� ������������ ����� ���� �� ������ java.util, ����� ����:
1) java.util.Iterator
2) java.util.NoSuchElementException

_______________________

������� � ����������� � �������� ������ (ua.nure.your_last_name.practice2) ��������� Container:

-----------------------------
package ua.nure.your_last_name.practice2;

public interface Container extends Iterable<Object> {
	
	// Removes all of the elements.
	void clear();

	// Returns the number of elements.
	int size();
	
	// Returns a string representation of this container.
	String toString();

	// Returns an iterator over elements.
	// Iterator must implements the remove method.
	Iterator<Object> iterator();

}
-----------------------------

_______________________

������� 1
-------------------------------------------------------
����� �����: Array, ArrayImpl
-------------------------------------------------------

1.1. ������� ��������� Array ���������� ����������:

-----------------------------
package ua.nure.your_last_name.practice2;

public interface Array extends Container {

	// Add the specified element to the end.
	void add(Object element);

	// Sets the element at the specified position.
	void set(int index, Object element);

	// Returns the element at the specified position.
	Object get(int index);

	// Returns the index of the first occurrence of the specified element,
	// or -1 if this array does not contain the element.
	// (use 'equals' method to check an occurrence)
	int indexOf(Object element);

	// Removes the element at the specified position.
	void remove(int index);

}
-----------------------------

1.2. ������� ����� ArrayImpl, ������� ��������� ��������� Array.

�������� �������� ������ ���������� ����������� � ������� ������� ��������.

����� iterator ������ ���������� ��������� ������ IteratorImpl, ������� ��������� ��������� java.util.Iterator<Object>.
����� IteratorImpl ������ ���� ��������� ������ ������ ArrayImpl (�������� ���������� �������).

���� � ��������� ���� ��������� � ������� ������ add ��� �������� A, B, C, ��:
1) ����� toString ������ ���������� ������ "[A, B, C]"
2) ������� ������ ��������� ���������� ����������: A B C

1.3. � ������ ArrayImpl ������� ����� main, � ������� ������������������ ������:
1) ���� ������� ���������� Array (������� �������������� �� Container � Iterable);
2) ���� ������� ���������� Iterator (hasNext/next/remove).

_______________________

������� 2
-------------------------------------------------------
��� ������: ua.nure.your_last_name.practice2
����� �����: List, ListImpl
-------------------------------------------------------

2.1. ������� ��������� List ���������� ����������:

-----------------------------
package ua.nure.your_last_name.practice2

public interface List extends Container {

	// Inserts the specified element at the beginning.
	void addFirst(Object element);

	// Appends the specified element to the end.
	void addLast(Object element);

	// Removes the first element.
	void removeFirst();

	// Removes the last element.
	void removeLast();

	// Returns the first element.
	Object getFirst();

	// Returns the last element.
	Object getLast();

	// Returns the first occurrence of the specified element.
	// Returns null if no such element.
	// (use 'equals' method to check an occurrence)
	Object search(Object element);

	// Removes the first occurrence of the specified element.
	// Returns true if this list contained the specified element. 
	// (use 'equals' method to check an occurrence)
	boolean remove(Object element);

}
-----------------------------

2.2. ������� ����� ListImpl, ������� ��������� ��������� List.

�������� �������� ������ ���������� ����������� � ������� ������������ ����� - ����������� ������ Node.
������ ���� ������ ������ � ������ �� ��������� ����.
����� Node ������ ���� ��������� ������ ������ ListImpl (�������� ��������� static �������).

����� iterator ������ ���������� ��������� ������ IteratorImpl, ������� ��������� ��������� java.util.Iterator<Object>.
����� IteratorImpl ������ ���� ��������� ������ ������ ListImpl (�������� ���������� �������).

���� � ��������� ���� ��������� � ������� ������ addLast ��� �������� A, B, C, ��:
1) ����� toString ������ ���������� ������ "[A, B, C]"
2) ������� ������ ��������� ���������� ����������: A B C

2.3. � ������ ListImpl ������� ����� main, � ������� ������������������ ������:
1) ���� ������� ���������� List (������� �������������� �� Container � Iterable);
2) ���� ������� ���������� Iterator (hasNext/next/remove).
_______________________

������� 3
-------------------------------------------------------
����� �����: Queue, QueueImpl
-------------------------------------------------------

3.1. ������� ��������� Queue ���������� ����������:

-----------------------------
package ua.nure.your_last_name.practice2;

public interface Queue extends Container {

	// Appends the specified element to the end.
	void enqueue(Object element);

	// Removes the head.
	Object dequeue();

	// Returns the head.
	Object top();

}
-----------------------------

3.2. ������� ����� QueueImpl, ������� ��������� ��������� Queue.

���� � ��������� ���� ��������� ��� �������� A, B, C � ������� ������ enqueue

1) ����� toString ������ ���������� ������ "[A, B, C]"
2) ������� ������ ��������� ���������� ����������: A B C

3.3. � ������ QueueImpl ������� ����� main, � ������� ������������������ ������:
1) ���� ������� ���������� Queue (������� �������������� �� Container � Iterable);
2) ���� ������� ���������� Iterator (hasNext/next/remove).

_______________________

������� 4
-------------------------------------------------------
����� �����: Stack, StackImpl
-------------------------------------------------------

4.1. ������� ��������� Stack ���������� ����������:

-----------------------------
package ua.nure.your_last_name.practice2;

public interface Stack extends Container {
	
	// Pushes the specified element onto the top.
	void push(Object element);
	
	// Removes and returns the top element.
	Object pop();

	// Returns the top element.
	Object top();
	
}
-----------------------------

4.2. ������� ����� StackImpl, ������� ��������� ��������� Stack.

���� � ��������� ���� ��������� � ������� ������ push ��� �������� A, B, C, ��:
1) ����� toString ������ ���������� ������ "[A, B, C]"
2) ������� ������ ��������� ���������� ����������: C B A

4.3. � ������ StackImpl ������� ����� main, � ������� ������������������ ������:
1) ���� ������� ���������� Stack (������� �������������� �� Container � Iterable);
2) ���� ������� ���������� Iterator (hasNext/next/remove).

_______________________

���������.
1. ��������� ������ ���� ����������� � ���� ������� � ������ Practice2.
2. �������� ����� ��� ���� �������: ua.nure.your_last_name.practice2
3. ������������� � �������� ������ ����������� ����� Demo, ������� ������������ ������ ���� ��������.
4. ������ ��������� � �����������, ��������� ���������� ������ � Jenkins, �������������� ������� � Sonar.

_______________________
