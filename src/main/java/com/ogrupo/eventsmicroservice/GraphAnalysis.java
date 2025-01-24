package com.ogrupo.eventsmicroservice;

import java.util.*;

public class GraphAnalysis {

    public static List<String[]> processInput(String input) {
        input = input.replace("{", "").replace("}", "");
        String[] edges = input.split(" ");
        List<String[]> edgeList = new ArrayList<>();

        for (String edge : edges) {
            String[] nodes = edge.split(",");
            edgeList.add(nodes);
        }

        return edgeList;
    }

    public static boolean validateGraph(List<String[]> edges) {
        for (String[] edge : edges) {
            if (edge.length != 2) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, List<String>> constructGraph(List<String[]> edges) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String[] edge : edges) {
            String u = edge[0];
            String v = edge[1];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());

            graph.get(u).add(v);
            graph.get(v).add(u); // Undirected graph
        }

        return graph;
    }

    public static List<List<String>> detectConnectedComponents(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        List<List<String>> components = new ArrayList<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfs(node, graph, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private static void dfs(String node, Map<String, List<String>> graph, Set<String> visited, List<String> component) {
        visited.add(node);
        component.add(node);

        for (String neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }

    public static boolean isComplete(Map<String, List<String>> graph) {
        int n = graph.size();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            if (entry.getValue().size() != n - 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Provide the graph edges in the format ({a,b} {a,c} ...): ");
        String input = scanner.nextLine();

        List<String[]> edges = processInput(input);

        if (!validateGraph(edges)) {
            System.out.println("Invalid input! Please ensure the edges are in the correct format.");
            return;
        }

        Map<String, List<String>> graph = constructGraph(edges);
        List<List<String>> components = detectConnectedComponents(graph);

        System.out.println("The graph has " + components.size() + " connected component(s):");
        for (int i = 0; i < components.size(); i++) {
            System.out.println("  Component " + (i + 1) + ": " + components.get(i));
        }

        if (isComplete(graph)) {
            System.out.println("The graph is complete.");
        } else {
            System.out.println("The graph is not complete.");
        }

        scanner.close();
    }
}
