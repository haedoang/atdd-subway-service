package nextstep.subway.station.domain;

import nextstep.subway.station.dto.StationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName : nextstep.subway.station.domain
 * fileName : Stations
 * author : haedoang
 * date : 2021-12-02
 * description :
 */
public class Stations {
    private final List<Station> stations;

    private Stations(List<Station> stations) {
        this.stations = new ArrayList<>(stations);
    }

    public static Stations of(List<Station> stations) {
        return new Stations(stations);
    }

    public boolean isExist(Station station) {
        return stations.stream().anyMatch(it -> it.equals(station));
    }

    public boolean isExist(Long stationId) {
        return stations.stream().anyMatch(it -> it.getId().equals(stationId));
    }

    public boolean isEmpty() {
        return stations.isEmpty();
    }

    public List<Station> getList() {
        return stations;
    }

    public List<StationResponse> toResponses() {
        return stations.stream().map(StationResponse::of).collect(Collectors.toList());
    }
}
