class ImplementCirculerQueue {
    int maxSize;
    int rear;
    int front;
    int[] queue;
    ImplementCirculerQueue(int maxSize) {
        this.maxSize = maxSize;
        this.rear = -1;
        this.front = -1;
        this.queue = new int[maxSize];
    }

    void offer(int item) {
        if ((rear + 1) % maxSize == front) {
            System.out.println('The queue is full');
        } else {
            if (rear == -1 && front == -1) {
                front = 0;
            }
            rear = (rear + 1) % maxSize
            queue[rear] = item;
        }
    }

    int poll() {
        if (rear == -1 && front == -1) {
            System.out.println('No more item');
        } else {
            if (rear == front) {
                int tmp = queue[rear];
                rear = -1;
                front = -1;
                return tmp;
            } else {
                int tmp = queue[front];
                front = (front + 1) % maxSize;
                return tmp;
            }
        }
    }

    int peek() {
    }
}
