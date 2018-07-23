public class HitCounter {
    Deque<Second> q = new LinkedList<Second>();
    int hits = 0;
    /** Initialize your data structure here. */
    public HitCounter() {

    }

    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        while(!q.isEmpty() && timestamp - q.peek().sec > 299){ //at most 299 cycles, therefore O(1)
            hits -= q.peek().count;
            q.poll();
        }
        if(!q.isEmpty() && q.peekLast().sec == timestamp){
            q.getLast().count++;
            hits++;
        }else{
            q.offer(new Second(timestamp));
            hits++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!q.isEmpty() && timestamp - q.peek().sec > 299){ //at most 299 cycles, therefore O(1)
            hits -= q.peek().count;
            q.poll();
        }
        return hits;
    }
}
class Second{
    int sec, count;
    public Second(int sec){
        this.sec = sec;
        count = 1;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
