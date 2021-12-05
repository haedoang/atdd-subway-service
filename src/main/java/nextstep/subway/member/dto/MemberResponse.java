package nextstep.subway.member.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nextstep.subway.member.domain.Member;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
    private Long id;
    private String email;
    private Integer age;

    public MemberResponse(Long id, String email, Integer age) {
        this.id = id;
        this.email = email;
        this.age = age;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getAge());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }
}
