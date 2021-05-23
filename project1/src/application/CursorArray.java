package application;

public class CursorArray<T extends Comparable<T>> {
	
	private Node<T> [] cArray = new Node[201];
	
	public CursorArray() {
		initialization();
	}
	
	public int  initialization() { // initialization method.
		for(int i = 0 ; i < cArray.length - 1 ; i++)
			cArray[i] = new Node<>(null, i+1);
		cArray[cArray.length-1] = new Node<>(null, 0);
		return 0;
	}
	
	public int malloc() { // malloc method.
		int p = cArray[0].getNext();
		cArray[0].setNext(cArray[p].getNext());
		return p;
	}
	
	public void free(int p) { //free method.
		cArray[p] = new Node<>(null, cArray[0].getNext());
		cArray[0].setNext(p);
	}
	
	public boolean isNull(int l) { // isNll method, check if the node in index (l) is null or not.
		return cArray[l] == null;
	}
	
	public boolean isEmpty(int p) { // isEmpty method.
		return cArray[p].getNext() == 0;
	}
	
	public boolean isLast(int p) { // isLast method, check if that node is the last or not.
		return cArray[p].getNext() == 0;
	}
	
	public int createList() { // Create List method.
		int l = malloc();
		if(l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cArray[l] = new Node("-",0);
		return l;
	}
	
	public void insertAtHead(T data, int l) { // insert at head method.
		if(isNull(l))
			return ;
		int p = malloc();
		if(p != 0) {
			cArray[p] = new Node(data,cArray[l].getNext());
			cArray[l].setNext(p);
		}
		else
			System.out.println("Error: Out of space!!!");
	}
	
//	public void insertAtTail(T data, int l) { // insert at tail
//		if(isNull(l))
//			return ;
//		int p = malloc();
//		if(p != 0) {
//			cArray[p] = new Node<>(data, 0);
//			int c = cArray[l].getNext();
//			while( !isEmpty(l))
//				c = cArray[c].getNext();
//			cArray[c].setNext(p);
//		}
//	}
	
	public void insertAtEnd(T data, int l) { // insertAtTail method: insert the data at the end
		if (isNull(l))  // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cArray[p] = new Node<>(data, 0);
			if (isEmpty(l)) {
				cArray[l].setNext(p);
				// insertAtHead(data, l);same
			} else {
				int prev = l, index = cArray[l].getNext();
				while (index != 0) {
					prev = index;
					index = cArray[index].getNext();
				}
				cArray[p].setNext(index);
				cArray[prev].setNext(p);
			}
		} 
	}
	
	public void  traversList(int l) { // Print all the element in the array.
		System.out.print("Head --> ");
		while(!isNull(l) && !isEmpty(l)) {
			l = cArray[l].getNext();
			System.out.print(cArray[l].getData()+" --> ");
		}
		System.out.println("Null");
	}
	
	public int find(T data, int l) { // Find method.
		while(!isNull(l) && !isEmpty(l)) {
			l = cArray[l].getNext();
			if(cArray[l].getData().equals(data))
				return l;
		}
		return -1;
	}
	
	public int findPrevious(T data, int l) { // Find Previous method.
		while(!isNull(l) && !isEmpty(l)) {
			if(cArray[cArray[l].getNext()].getData().equals(data))
				return l;
			l = cArray[l].getNext();
		}
		return -1;
	}
	
	public Node delete(T data, int l) { // Delete method.
		int p = findPrevious(data,l);
		if(p != -1) {
			int c = cArray[p].getNext();
			Node temp = cArray[c];
			free(c);
		}
		return null;
	}
	
	public T deleteAtHead(int l) { // Store the data in first index, delete that node, then return the store data
		if(isNull(l))
			return null;
		int p = cArray[l].getNext();
		T data = cArray[p].getData();
		cArray[l].setNext(cArray[p].getNext());
		free(p);
		return data;
	}
	
	public Node get(int l) {
		return !isNull(l) ? cArray[l] : null;
	}
	
	
	
}
