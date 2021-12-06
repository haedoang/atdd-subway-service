package nextstep.subway.favorite.domain;

import nextstep.subway.favorites.domain.Favorite;
import nextstep.subway.line.domain.Distance;
import nextstep.subway.member.domain.Member;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName : nextstep.subway.favorite.domain
 * fileName : FavoriteTest
 * author : haedoang
 * date : 2021/12/06
 * description :
 */
@SpringBootTest
public class FavoriteTest {
    @BeforeEach
    void setUp() {
        /*Member member = memberRepository.save(new MemberRequest("haedoang@gmail.com", "11", 33).toMember());
        Station 강남역 = stationRepository.save(new Station("강남역"));
        Station 역삼역 = stationRepository.save(new Station("역삼역"));
        Line 일호선 = lineRepository.save(Line.of("일호선", "남색", 강남역, 역삼역, 100));*/
    }

    @Test
    @DisplayName("즐겨찾기를 생성한다.")
    public void create() throws Exception {
        //given
        Favorite favorite = new Favorite(
                new Station("강남역"),
                new Station("역삼역"),
                Distance.of(100)
        );
        //when
        Member member = new Member("haedoang@gmail.com", "11", 33);
        member.addFavorite(favorite);

        //then
        assertThat(member.getFavorites()).hasSize(1);
        assertThat(favorite.getDistance()).isEqualTo(Distance.of(100));
    }
}
