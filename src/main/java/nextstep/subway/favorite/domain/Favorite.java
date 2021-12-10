package nextstep.subway.favorite.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nextstep.subway.BaseEntity;
import nextstep.subway.line.domain.Distance;
import nextstep.subway.member.domain.Member;
import nextstep.subway.path.domain.Path;
import nextstep.subway.path.exception.PathBeginIsEndException;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

/**
 * packageName : nextstep.subway.favorites.domain
 * fileName : Favorite
 * author : haedoang
 * date : 2021/12/06
 * description :
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = LAZY)
    @JoinColumn(name = "SOURCE_STATION_ID")
    private Station sourceStation;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = LAZY)
    @JoinColumn(name = "TARGET_STATION_ID")
    private Station targetStation;

    @Embedded
    private Distance distance;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Favorite(Station sourceStation, Station targetStation, Distance distance) {
        if(Objects.equals(sourceStation, targetStation)) {
            throw new PathBeginIsEndException();
        }
        this.sourceStation = sourceStation;
        this.targetStation = targetStation;
        this.distance = distance;
    }

    public static Favorite of(Station sourceStation, Station targetStation, Distance distance) {
        return new Favorite(sourceStation, targetStation, distance);
    }

    public static Favorite of(Path path) {
        return new Favorite(path.source(), path.target(), path.distance());
    }

    public Favorite by(Member member) {
        this.member = member;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public Distance getDistance() {
        return distance;
    }

    public Member getMember() {
        return member;
    }
}
