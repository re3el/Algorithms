// FloydWarshall algorithm:
// All pairs shortest path in a directed graph with positive and negative weights
// Doesn't work for the graphs with negative edge cycles
// Time: O(n^3) Space: O(n^2) 

// Problem: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/submissions/
class Solution 
{
    public int findTheCity(int n, int[][] edges, int distanceThreshold) 
    {        
        int[][] dist = new int[n][n];
        for (int i=0; i<n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        for (int i=0; i<n; i++)
            dist[i][i] = 0;
        for (var edge:edges)
        {
            int u = edge[0], v = edge[1], w = edge[2];
            dist[u][v] = dist[v][u] = w;
            dist[v][u] = w;
        }
        
        for (int k=0; k<n; k++)
        {
            for (int i=0; i<n; i++)
            {
                for (int j=0; j<n; j++)
                {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        int minCities = n, result = n-1;
        for (int i=0; i<n; i++)
        {
            int cities = 0;
            for (int j=0; j<dist[i].length; j++)
            {
                if (i==j || dist[i][j] == Integer.MAX_VALUE || dist[i][j] > distanceThreshold) continue;
                cities++;
            }
            
            if (cities <= minCities)
            {
                minCities = cities;
                result = i;             
            }
        }
        return result;
    }
}