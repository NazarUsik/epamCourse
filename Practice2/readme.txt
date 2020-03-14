Практическое занятие №2
_______________________

Замечание. Запрещено использовать любые типы из пакета java.util, кроме двух:
1) java.util.Iterator
2) java.util.NoSuchElementException

_______________________

Создать и расположить в корневом пакете (ua.nure.your_last_name.practice2) интерфейс Container:

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

Задание 1
-------------------------------------------------------
Имена типов: Array, ArrayImpl
-------------------------------------------------------

1.1. Создать интерфейс Array следующего содержания:

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

1.2. Создать класс ArrayImpl, который реализует интерфейс Array.

Хранение объектов внутри контейнера реализовать с помощью массива объектов.

Метод iterator должен возвращать экземпляр класса IteratorImpl, который реализует интерфейс java.util.Iterator<Object>.
Класс IteratorImpl должен быть определен внутри класса ArrayImpl (является внутренним классом).

Если в контейнер были добавлены с помощью метода add три элемента A, B, C, то:
1) метод toString должен возвращать строку "[A, B, C]"
2) порядок обхода элементов контейнера итератором: A B C

1.3. В классе ArrayImpl создать метод main, в котором продемонстрировать работу:
1) всех методов интерфейса Array (включая унаследованные от Container и Iterable);
2) всех методов интерфейса Iterator (hasNext/next/remove).

_______________________

Задание 2
-------------------------------------------------------
Имя пакета: ua.nure.your_last_name.practice2
Имена типов: List, ListImpl
-------------------------------------------------------

2.1. Создать интерфейс List следующего содержания:

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

2.2. Создать класс ListImpl, который реализует интерфейс List.

Хранение объектов внутри контейнера реализовать с помощью совокупности узлов - экземпляров класса Node.
Каждый узел хранит объект и ссылку на следующий узел.
Класс Node должен быть определен внутри класса ListImpl (является вложенным static классом).

Метод iterator должен возвращать экземпляр класса IteratorImpl, который реализует интерфейс java.util.Iterator<Object>.
Класс IteratorImpl должен быть определен внутри класса ListImpl (является внутренним классом).

Если в контейнер были добавлены с помощью метода addLast три элемента A, B, C, то:
1) метод toString должен возвращать строку "[A, B, C]"
2) порядок обхода элементов контейнера итератором: A B C

2.3. В классе ListImpl создать метод main, в котором продемонстрировать работу:
1) всех методов интерфейса List (включая унаследованные от Container и Iterable);
2) всех методов интерфейса Iterator (hasNext/next/remove).
_______________________

Задание 3
-------------------------------------------------------
Имена типов: Queue, QueueImpl
-------------------------------------------------------

3.1. Создать интерфейс Queue следующего содержания:

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

3.2. Создать класс QueueImpl, который реализует интерфейс Queue.

Если в контейнер были добавлены три элемента A, B, C с помощью метода enqueue

1) метод toString должен возвращать строку "[A, B, C]"
2) порядок обхода элементов контейнера итератором: A B C

3.3. В классе QueueImpl создать метод main, в котором продемонстрировать работу:
1) всех методов интерфейса Queue (включая унаследованные от Container и Iterable);
2) всех методов интерфейса Iterator (hasNext/next/remove).

_______________________

Задание 4
-------------------------------------------------------
Имена типов: Stack, StackImpl
-------------------------------------------------------

4.1. Создать интерфейс Stack следующего содержания:

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

4.2. Создать класс StackImpl, который реализует интерфейс Stack.

Если в контейнер были добавлены с помощью метода push три элемента A, B, C, то:
1) метод toString должен возвращать строку "[A, B, C]"
2) порядок обхода элементов контейнера итератором: C B A

4.3. В классе StackImpl создать метод main, в котором продемонстрировать работу:
1) всех методов интерфейса Stack (включая унаследованные от Container и Iterable);
2) всех методов интерфейса Iterator (hasNext/next/remove).

_______________________

Замечания.
1. Результат должен быть представлен в виде проекта с именем Practice2.
2. Корневой пакет для всех классов: ua.nure.your_last_name.practice2
3. Дополнительно в корневом пакете расположить класс Demo, который демонстриует работу всех подзадач.
4. Проект загрузить в репозиторий, проверить успешность сборки в Jenkins, оптимизировать метрики в Sonar.

_______________________
