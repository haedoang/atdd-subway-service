package nextstep.subway.path.dto;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.Stations;
import nextstep.subway.station.dto.StationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : nextstep.subway.path
 * fileName : PathResponse
 * author : haedoang
 * date : 2021-12-01
 * description :
 */
public class PathResponse {
    private List<StationResponse> stations;

    private PathResponse() {
    }

    private PathResponse(Stations stations) {
        this.stations = stations.getList()
                .stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    public static PathResponse of(Stations stations) {
        return new PathResponse(stations);
    }

    public List<StationResponse> getStations() {
        return stations;
    }
}
