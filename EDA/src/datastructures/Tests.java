package datastructures;

public class Tests {
	
	public static void skipListTest() {
		SkipList<Integer> list = new SkipList<Integer>();
		list.add(4);
		list.add(6);
		list.add(1);
		list.add(20);
		System.out.println(list.toString());
		list.delete(6);
		System.out.println(list.toString());
	}
	
	public static void hashTableTest() {
		HashTable<Integer> hashTable = new HashTable<Integer>();
		int i = 0;
		while(i < 100000) {
			hashTable.add((int)Math.round(Math.random()*100));
			i++;
		}
		System.out.println(hashTable);
	}

	public static void main(String[] args) {
		//skipListTest();
		hashTableTest();
	}

}
