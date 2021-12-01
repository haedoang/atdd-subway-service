package nextstep.subway.common.exception;

/**
 * packageName : nextstep.subway.common.exception
 * fileName : SectionNotCreateException
 * author : haedoang
 * date : 2021/12/01
 * description :
 */
public class SectionNotCreateException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public SectionNotCreateException(String message) {
        super(message);
    }
}
