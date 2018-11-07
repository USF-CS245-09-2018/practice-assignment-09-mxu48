public class BinaryHeap {

	int size = 10;
	int count = 0;
	int [] heap = new int [size];

	public void add(int toAdd) {
		if(count < 0) {
			throw new RuntimeException("Cannot add out of array boundaries.");
		}
		if(count == size-1) {
			doubleArr();
		}
		heap[count] = toAdd; /* Add to end */
		count++;
		int prev = count-1;
		/* Move newly added up the tree until in ordered place */
		while(prev > 0) {
			int parent = (prev-1)/2;
			if(heap[prev] < heap[parent]) {
				swap(heap, prev, parent);
				prev = parent;
			} else {
				return;
			}
		}
	}

	public int remove() {
		if(count == 0) {
			throw new RuntimeException("The heap is empty.");
		}
		/* Remove at root */
		int toRemove = heap[0];
		/* Take left bottom right-most child to replace at root */
		int newRoot = heap[size-1];
		heap[0] = newRoot;
		shiftdown(0); /* Re-order */
		count--;
		return toRemove;
	}

	public void shiftdown (int index) {
		/* Compare between two children of curr, swap with curr if smaller */
		int leftChild = index*2+1;
		if(leftChild < size) {
			return;
		}
		int rightChild = leftChild + 1;
		int child = 0;
		/* Get smaller child */
		if(heap[leftChild] < heap[rightChild]) {
			child = leftChild;
		}
		if(heap[rightChild] < heap[leftChild]) {
			child = rightChild;
		}
		/* Swap smaller child with parent if parent is bigger */
		if(heap[index] > heap[child]) {
			swap(heap, index, child);
			shiftdown(child);
		}
	}

	public void doubleArr() {
		int [] newArr = new int [heap.length * 2];
		for(int i = 0; i < heap.length; i++) {
			newArr[i] = heap[i]; /* Copy values */
		}

		heap = newArr;
		size = heap.length;
	}

	public void swap(int [] heap, int x, int y) {
		int temp = heap[x];
		heap[x] = heap[y];
		heap[y] = temp;
	}

}