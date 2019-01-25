package algo;

import java.util.LinkedList;
import java.util.Queue;

/**无向图bfs和dfs搜索
 * Created by xiaoming on 24/01/2019.
 */
public class Graph {
    private int v;
    private LinkedList<Integer>[] array;
    private boolean found;

    Graph(int v){
        this.v = v;
        array = new LinkedList[v];

        for(int i=0; i<v; i++){
            array[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int s, int t){
        array[s].add(t);
        array[t].add(s);
    }

    public void bfs(int s, int t){
        if(s==t){
            return;
        }

        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        int[] prev = new int[v];

        for(int i=0; i<v; i++){
            prev[i] = -1;
        }

        while (queue.size() !=0){
            int pre = queue.poll();

            for(int i=0; i<array[pre].size(); i++){
                int next = array[pre].get(i);

                if(!visited[next]){
                    prev[next] = pre;
                    if(next == t){
                        print(prev, s, t);
                    }

                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public void dfs(int s, int t){
        int[] prev = new int[v];
        for(int i=0; i<v; i++){
            prev[i] = -1;
        }

        boolean[] visited = new boolean[v];
        visited[s] = true;
        if(s == t){
            return;
        }

        recurDfs(s, prev, visited, s, t);
    }

    /**
     * 通过记录初始起点值，所以s==t时候就可以直接print
     * @param initial
     * @param prev
     * @param visited
     * @param s
     * @param t
     */
    private void recurDfs(int initial, int[] prev, boolean[] visited, int s, int t) {
        if(found){
            return;
        }

        if(s == t){
            found = true;
            print(prev, initial, t);
            return;
        }

        for(int i=0; i<array[s].size(); i++){
            int next = array[s].get(i);

            if(!visited[next]){
                prev[next] = s;
                visited[next] = true;
                recurDfs(initial, prev, visited, next, t);
            }
        }
    }

    public void dfs2(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs2(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs2(int w, int t, boolean[] visited, int[] prev) {
        if (found == true) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < array[w].size(); ++i) {
            int q = array[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs2(q, t, visited, prev);
            }
        }
    }

    public void print(int[] prev, int s, int t){
        if(s != t && prev[t] != -1){
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        graph.dfs(5, 7);
    }
}
