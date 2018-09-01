package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;

public class BreadthFirstSearch {

    private HashMap<UserNode, Integer> nodeDistance;

    public int distance(UserNode node1, UserNode node2) throws CannotReachNodeException {
        List<UserNode> visited = new LinkedList<>();
        List<UserNode> toVisit = new LinkedList<>();
        nodeDistance = new HashMap<UserNode, Integer>();

        toVisit.add(node1);
        nodeDistance.put(node1, 0);
        return breadthFirstSearch(visited, toVisit, node2);

    }

    private int breadthFirstSearch(List<UserNode> visited, List<UserNode> toVisit, UserNode searched) throws CannotReachNodeException {
        while(!toVisit.isEmpty()) {
            UserNode currentNode = toVisit.remove(0);
            int distanceOfCurrentNode = nodeDistance.get(currentNode);
            if (currentNode.equals(searched)) {
                return distanceOfCurrentNode;
            }
            visited.add(currentNode);
            for(UserNode node : currentNode.getFriends()) {
                nodeDistance.put(node, distanceOfCurrentNode + 1);
                if (!visited.contains(node)) {
                    toVisit.add(node);
                }
            }
        }
        throw new CannotReachNodeException("Given nodes are not connected");
    }

    public Set<UserNode> friendsOfFriends(UserNode node, int distance) {
        List<UserNode> visited = new LinkedList<>();
        List<UserNode> toVisit = new LinkedList<>();
        nodeDistance = new HashMap<UserNode, Integer>();

        toVisit.add(node);
        nodeDistance.put(node, 0);
        return findFriendsAtMaxDistance(visited, toVisit, distance);
    }

    private Set<UserNode> findFriendsAtMaxDistance(List<UserNode> visited, List<UserNode> toVisit, int distance) {
        Set<UserNode> result = new HashSet<>();
        while(!toVisit.isEmpty()) {
            UserNode currentNode = toVisit.remove(0);
            int distanceOfCurrentNode = nodeDistance.get(currentNode);
            visited.add(currentNode);

            if (distanceOfCurrentNode <= distance) {
                result.add(currentNode);
                for(UserNode node : currentNode.getFriends()) {
                    nodeDistance.put(node, distanceOfCurrentNode + 1);
                    if (!visited.contains(node)) {
                        toVisit.add(node);
                    }
                }
            }
        }
        return result;

    }
}
