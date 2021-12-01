package nextstep.subway.path;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Section;
import nextstep.subway.station.domain.Station;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * packageName : nextstep.subway.path
 * fileName : ShortestPathProvider
 * author : haedoang
 * date : 2021-12-01
 * description :
 */
@Component
public class ShortestPathProvider {

    public PathResponse getDijkstraShortestPath(List<Station> stations, List<Line> lines, Station src, Station target) {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);

        stations.stream().forEach(graph::addVertex);

        lines.stream()
                .forEach(line -> line.getSections().stream()
                        .forEach(section -> graph.setEdgeWeight(graph.addEdge(section.getUpStation(), section.getDownStation()), section.getDistance())));

        DijkstraShortestPath<Station, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        List<Station> shortestPath = dijkstraShortestPath.getPath(src, target).getVertexList();

        return PathResponse.of(shortestPath);
    }
}
