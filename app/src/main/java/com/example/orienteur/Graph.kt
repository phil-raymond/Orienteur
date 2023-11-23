package com.example.orienteur

object Graph {
    private val nodes: MutableList<Node> = mutableListOf()
    private val edges: MutableList<Edge> = mutableListOf()

    fun addNode(node: Node) {
        nodes.add(node)
    }

    fun addEdge(edge: Edge) {
        edges.add(edge)
    }

    // Méthodes pour trouver un Node, Edge, etc.
    fun findNode(localNumber: String): Node? {
        return nodes.find { it.localNumber == localNumber }
    }

    fun findEdge(startNode: Node, endNode: Node): Edge? {
        return edges.find { it.startNode == startNode && it.endNode == endNode }
    }

    fun findPath(startNode: Node, endNode: Node): List<Edge> {
        val path: MutableList<Edge> = mutableListOf()
        var currentNode = startNode
        while (currentNode != endNode) {
            val nextEdge = edges.find { it.startNode == currentNode }
            if (nextEdge != null) {
                path.add(nextEdge)
                currentNode = nextEdge.endNode
            } else {
                break
            }
        }
        return path
    }
}
