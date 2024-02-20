package it.unibo.commons;

import org.jgrapht.graph.WeightedPseudograph;

import it.unibo.model.city.api.City;
import it.unibo.model.route.api.Route;
import it.unibo.model.route.impl.RouteImpl;

/**
 * Utility class to copy a graph.
 */
public final class GraphCopier {

    /**
     * Private constructor to hide the implicit public one.
     */
    private GraphCopier() {
    }

    /**
     * Public static method to copy a graph.
     * 
     * @param originalGraph the original graph
     * @return the copied one
     */
    public static WeightedPseudograph<City, Route> copyGraph(final WeightedPseudograph<City, Route> originalGraph) {
        final WeightedPseudograph<City, Route> copiedGraph = new WeightedPseudograph<>(RouteImpl.class);
        originalGraph.vertexSet().forEach(copiedGraph::addVertex);
        originalGraph.edgeSet().forEach(edge -> {
            final City source = edge.getConnectedCity().first();
            final City target = edge.getConnectedCity().second();
            copiedGraph.addEdge(source, target, edge);
            copiedGraph.setEdgeWeight(edge, edge.getScore());
        });
        return copiedGraph;
    }
}
