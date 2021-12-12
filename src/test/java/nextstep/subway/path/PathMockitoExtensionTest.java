package nextstep.subway.path;

import nextstep.subway.auth.domain.Stranger;
import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.*;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.path.infrastructure.SeoulMetroFare;
import nextstep.subway.path.infrastructure.SeoulMetroType;
import nextstep.subway.path.application.PathService;
import nextstep.subway.path.domain.Path;
import nextstep.subway.path.domain.PathEdge;
import nextstep.subway.path.domain.SubwayFare;
import nextstep.subway.path.infrastructure.JGraphPathFinder;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * packageName : nextstep.subway.path
 * fileName : PathMockitoExtensionTest
 * author : haedoang
 * date : 2021/12/04
 * description :
 */
@ExtendWith(MockitoExtension.class)
public class PathMockitoExtensionTest {
    @Mock
    private JGraphPathFinder finder;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private LineRepository lineRepository;

    @Mock
    private Station 강남역;

    @Mock
    private Station 역삼역;

    @Mock
    private Stranger 비로그인;

    @Test
    @DisplayName("역 조회")
    void findStations() {
        when(stationRepository.findAll()).thenReturn(Lists.newArrayList(강남역, 역삼역));

        StationService stationService = new StationService(stationRepository);

        List<StationResponse> stations = stationService.findStations();

        assertThat(stations).hasSize(2);
    }

    @Test
    @DisplayName("노선 조회")
    void findLines() {
        when(lineRepository.findLines()).thenReturn(
                Lists.newArrayList(
                        Line.of("1호선", "남색", 강남역, 역삼역, 5)
                )
        );

        StationService stationService = new StationService(stationRepository);
        LineService lineService = new LineService(lineRepository, stationService);

        List<LineResponse> lines = lineService.findLines();

        assertThat(lines).hasSize(1);
    }


    @Test
    @DisplayName("경로 조회")
    void findPaths() throws Exception {
        // given
        final Line line1 = Line.of("1호선", "남색", 강남역, 역삼역, 5);
        List<Line> lines = Lists.newArrayList(line1);

        List<Station> stations = Lists.newArrayList(강남역, 역삼역);

        when(stationRepository.findAll()).thenReturn(stations);
        when(lineRepository.findAll()).thenReturn(lines);
        when(finder.getShortestPath(anyList(), anyList(), anyLong(), anyLong()))
                .thenReturn(Path.of(Lists.newArrayList(PathEdge.of(line1.sections().getList().get(0))), stations, 강남역, 역삼역, Distance.of(5)));
        when(비로그인.isStranger()).thenReturn(true);
        SubwayFare fare = new SeoulMetroFare();
        PathService pathService = new PathService(finder, fare, stationRepository, lineRepository);

        // when
        Path path = pathService.getShortestPath(강남역.getId(), 역삼역.getId());

        // then
        assertThat(path.stations()).hasSize(2);
        assertThat(path.distance()).isEqualTo(Distance.of(5));
        assertThat(fare.rateInquiry(path, 비로그인)).isEqualTo(Money.of(SeoulMetroType.BASE_RATE));
    }
}
