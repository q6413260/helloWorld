package algo;

import java.util.LinkedList;
import java.util.Queue;

/**无向图bfs和dfs搜索；利用图进行kahn排序和dfs
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

    public void addSingleEdge(int s, int t){
        array[s].add(t);
    }

    public void topoSortByKahn(){
        int[] indegree = new int[v];
        //计算所有顶点的入度
        for(int i=0; i< array.length; i++){
            for(int j=0; j<array[i].size(); j++){
                indegree[array[i].get(j)] ++;
            }
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i=0; i<v; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int v = queue.poll();

            System.out.println("输出:" + v);
            for(int i=0; i<array[v].size(); i++){
                int next = array[v].get(i);

                if(--indegree[next] == 0){
                    queue.add(next);
                }
            }
        }
    }

    public void topoSortByDfs(){
        //构建逆邻接表
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for(int i=0; i<v; i++){
            inverseAdj[i] = new LinkedList<Integer>();
        }
        for(int i=0; i<v; i++){
            for(int j=0; j<array[i].size(); j++){
                int next = array[i].get(j);
                inverseAdj[next].add(i);
            }
        }
        boolean[] visited = new boolean[v];
        for(int i=0; i<v; i++){
            if(visited[i]){
                continue;
            }

            dfs(i, inverseAdj, visited);
        }
    }

    private void dfs(int v, LinkedList<Integer>[] inverseAdj, boolean[] visited){
        visited[v] = true;
        for(int i=0; i<inverseAdj[v].size(); i++){
            int next = inverseAdj[v].get(i);

            if(!visited[next]){
                dfs(next, inverseAdj, visited);
            }
        }

        // 先把 vertex 这个顶点可达的所有顶点都打印出来之...
        System.out.println("输出:" + v);
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
//        Graph graph = new Graph(8);
//        graph.addEdge(0,1);
//        graph.addEdge(0,3);
//        graph.addEdge(1,2);
//        graph.addEdge(1,4);
//        graph.addEdge(2,5);
//        graph.addEdge(3,4);
//        graph.addEdge(4,5);
//        graph.addEdge(4,6);
//        graph.addEdge(5,7);
//        graph.addEdge(6,7);
//        graph.dfs(5, 7);
        Graph graph = new Graph(5);
        graph.addSingleEdge(0, 1);
        graph.addSingleEdge(0, 3);
        graph.addSingleEdge(1, 2);
        graph.addSingleEdge(2, 3);
        graph.addSingleEdge(2, 4);
        graph.addSingleEdge(4, 3);
        graph.topoSortByDfs();
    }
}
