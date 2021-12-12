package nextstep.subway.path.domain;

import nextstep.subway.auth.domain.User;
import nextstep.subway.line.domain.Money;

/**
 * packageName : nextstep.subway.line.domain
 * fileName : Fare
 * author : haedoang
 * date : 2021/12/12
 * description :
 */
public interface SubwayFare {
    Money rateInquiry(Path path, User user);
}
