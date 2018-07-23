// Given an integer array, heapify it into a min-heap array.
//
// For a heap array A, A[0] is the root of heap, and for each A[i]
// , A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
public class Solution {
  public void heapify (int[] A) {
    for (int i = 0; i < A.length; i++) {
      sink(A[i], i);
    }
  }

  public void sink(int[] A, int idx) {
    int N = A.length;
    int small = 0;
    while(idx * 2 < N) {
      if (idx * 2 + 2 >= N || A[idx * 2 + 1] < A[idx * 2 + 2]) {
        small = idx * 2 + 1;
      } else {
        small = idx * 2 + 2;
      }
      if(A[idx] > A[small]) {
        swap(A, idx, small);
        idx = small;
      } else break;
    }

  }

  private void swim(int[] A, int idx) {
    while(idx > 0 || A[idx / 2] > A[idx]) {
      swap(A, idx / 2, idx);
      idx = idx / 2;
    }
  }
  public void swap(int[] A, int a, int b) {
    int tmp = A[a];
    A[a] = A[b];
    A[b] = tmp;
  }
}
// remove min using the swap from last to first and sink
// insert is using swim method
