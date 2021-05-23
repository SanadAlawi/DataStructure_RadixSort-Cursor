package application;

public class RadixSort {
	
	CursorArray<String> bucked = new CursorArray();
	
	public RadixSort() { // Radix Sort Constructor, Create 27 cursor array
		for(int i = 0 ; i < 27 ; i++)
			bucked.createList();
	}
	
	/*
	 take an array of string and number of item in the array,
	 Sort the string using radix sort
	 */
	public String [] RadixSort(String [] name, int size) {
		int max = MaxWord(name);
		for(int i = max-1 ; i >= 0 ; i--) {
			for(int j = 0 ; j < size ; j++) {
				if(name[j].length()-1 < i || name[j].charAt(i) == 32)
					bucked.insertAtEnd(name[j], 1); 
				else {
					int rem = (int)Character.toLowerCase(name[j].charAt(i)) - 95;
					bucked.insertAtEnd(name[j], rem);
				}
			}
			int count = 0;
			for(int j = 1 ;  j <= 27 ; j++) {
				if(!bucked.isNull(j) ) {
					while(!bucked.isEmpty(j)) 
						name[count++] = bucked.deleteAtHead(j);
				}
			}
		}
		return name;
	}
	
	private int MaxWord(String [] str) { // get the max Character from the array
		int max = str[0].length();
		for(int i = 1 ; i < str.length ; i++) {
//			if(str[i] == null)
//				continue;
			if(max <= str[i].length())
				max = str[i].length();
		}
		return max;
	}
}
