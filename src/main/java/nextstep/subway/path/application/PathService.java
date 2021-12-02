package nextstep.subway.path.application;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.path.dto.PathResponse;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.domain.Stations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * packageName : nextstep.subway.path.application
 * fileName : PathService
 * author : haedoang
 * date : 2021-12-01
 * description :
 */
@Service
@Transactional(readOnly = true)
public class PathService {
    private final StationRepository stations;
    private final LineRepository lines;
    private final ShortestPathProvider provider;

    public PathService(StationRepository stations, LineRepository lines, ShortestPathProvider provider) {
        this.stations = stations;
        this.lines = lines;
        this.provider = provider;
    }

    public PathResponse getShortestPath(Long srcStationId, Long destStationId) {
        //전체 역 조회
        List<Station> stations = this.stations.findAll();

        //전체 노선 조회
        List<Line> lines = this.lines.findAll();

        Station srcStation = findStation(stations, srcStationId);

        Station destStation = findStation(stations, destStationId);

        return PathResponse.of(provider.getDijkstraShortestPath(stations, lines, srcStation, destStation));
    }

    private Station findStation(List<Station> stations, Long stationId) {
        return stations.stream()
                .filter(it -> it.getId().equals(stationId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
