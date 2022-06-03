package com.sample.utils;

import java.util.ArrayList;

public class Queue<T> {
  private ArrayList<T> queue;
  private int size;
  private int head;
  private int tail;

  public Queue(int size) {
    this.size = size;
    queue = new ArrayList<T>(size);
    for (int i = 0; i < size; i++) {
      queue.add(null);
    }
    head = 0;
    tail = 0;
  }

  public void enqueue(T item) {
    if (item == null || isFull()) {
      new RuntimeException("Queue is full");
    }
    queue.set(tail++, item);
    tail = tail % size;
  }

  public T dequeue() {
    if (isEmpty()) {
      new RuntimeException("Queue is empty");
    }
    T item = queue.get(head++);
    head = head % size;
    return item;
  }

  public boolean isEmpty() {
    return head == tail;
  }

  public boolean isFull() {
    return (tail + 1) % size == head;
  }
}
