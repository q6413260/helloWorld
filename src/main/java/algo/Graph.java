package algo;

import java.util.*;

/**无向图bfs和dfs搜索；利用图进行kahn排序和dfs
 * Created by xiaoming on 24/01/2019.
 */
public class Graph {
    private int v;
    private LinkedList<Integer>[] array;
    private LinkedList<Edge>[] adj;
    private boolean found;

    Graph(int v){
        this.v = v;
        array = new LinkedList[v];
        adj = new LinkedList[v];

        for(int i=0; i<v; i++){
            array[i] = new LinkedList<Integer>();
            adj[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(int s, int t){
        array[s].add(t);
        array[t].add(s);
    }

    public void addEdge(int s, int t, int w){
        adj[s].add(new Edge(s, t, w));
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

    public void dijkstra(int s, int t){
        //用来保存路径
        int[] chain = new int[v];
        boolean[] inQueue = new boolean[v];
        Vertex[] vertexes = new Vertex[v];

        for(int i=0; i<v; i++){
            Vertex vertex = new Vertex(i, Integer.MAX_VALUE);
            vertexes[i] = vertex;
            chain[i] = -1;
        }

        PriorityQueue queue = new PriorityQueue(v);
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inQueue[s] = true;

        while (!queue.isEmpty()){
            Vertex minDist = queue.poll();
            if(minDist.id == t){
                break;
            }

            LinkedList<Edge> edges = adj[minDist.id];
            for(Edge edge : edges){
                Vertex next = vertexes[edge.tid];
                if(minDist.dist + edge.weight < next.dist){
                    next.dist = minDist.dist + edge.weight;
                    chain[next.id] = minDist.id;

                    if(inQueue[edge.tid]){
                        queue.update(next);
                    }else{
                        inQueue[edge.tid] = true;
                        queue.add(next);
                    }
                }
            }
        }

        print(chain, s, t);
    }

    private class PriorityQueue{
        private int count;
        private Vertex[] nodes;
        private Map<Integer, Integer> id2IndexMap = new HashMap<Integer, Integer>();

        PriorityQueue(int capacity){
            nodes = new Vertex[capacity+1];
            this.count = 0;
        }

        public Vertex poll(){
            if(count<1){
                return null;
            }

            Vertex result = nodes[1];
            id2IndexMap.remove(result.id);
            nodes[1] = nodes[count--];
            heapify(nodes, count, 1);

            return result;
        }

        //从下往上堆化
        private void heapifyDown2Up(Vertex[] nodes, int count, int i){
            while (i/2 >=1){
                if(nodes[i].dist < nodes[i/2].dist){
                    swap(i, i/2, nodes);
                    i = i/2;
                }else{
                    break;
                }
            }
        }

        //从上往下堆化
        private void heapify(Vertex[] nodes, int count, int i) {
            while (2*i + 1 <= count){
                int minIndex = i;
                if(nodes[2*i].dist < nodes[minIndex].dist){
                    minIndex = 2*i;
                }

                if(nodes[2*i+1].dist < nodes[minIndex].dist){
                    minIndex = 2*i + 1;
                }

                if(minIndex == i){
                    break;
                }
                swap(minIndex, i, nodes);
                i = minIndex;
            }
        }

        public void add(Vertex vertex){
            nodes[++count] = vertex;
            id2IndexMap.put(vertex.id, count);
            int index = count;

            while (index>=2){
                if(nodes[index].dist < nodes[index/2].dist){
                    swap(index, index/2, nodes);
                    index = index/2;
                }else{
                    break;
                }
            }
        }

        private void swap(int sonIndex, int parentIndex, Vertex[] nodes) {
            Vertex temp = nodes[parentIndex];
            nodes[parentIndex] = nodes[sonIndex];
            nodes[sonIndex] = temp;
            id2IndexMap.put(nodes[sonIndex].id, sonIndex);
            id2IndexMap.put(nodes[parentIndex].id, parentIndex);
        }

        public void update(Vertex vertex){
            Integer index = id2IndexMap.get(vertex.id);
            if(index == null){
                return;
            }

            heapifyDown2Up(nodes, count, index);
        }

        public boolean isEmpty(){
            return count == 0;
        }
    }

    private class Vertex{
        private int id;
        private int dist;

        Vertex(int id, int dist){
            this.id = id;
            this.dist = dist;
        }
    }

    private class Edge{
        private int sid;
        private int tid;
        private int weight;

        Edge(int sid, int tid, int weight){
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
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
//        Graph graph = new Graph(5);
//        graph.addSingleEdge(0, 1);
//        graph.addSingleEdge(0, 3);
//        graph.addSingleEdge(1, 2);
//        graph.addSingleEdge(2, 3);
//        graph.addSingleEdge(2, 4);
//        graph.addSingleEdge(4, 3);
//        graph.topoSortByDfs();
        Graph graph = new Graph(6);
        graph.addEdge(0,1,10);
        graph.addEdge(0,4,15);
        graph.addEdge(1,2,15);
        graph.addEdge(1,3,2);
        graph.addEdge(3,2,1);
        graph.addEdge(3,5,12);
        graph.addEdge(2,5,5);
        graph.addEdge(4,5,10);

        graph.dijkstra(0, 5);
    }
}
