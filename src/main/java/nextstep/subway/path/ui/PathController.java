package nextstep.subway.path.ui;

import nextstep.subway.path.dto.PathResponse;
import nextstep.subway.path.application.PathService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName : nextstep.subway.path.ui
 * fileName : PathController
 * author : haedoang
 * date : 2021-12-01
 * description :
 */
@RestController
@RequestMapping("paths")
public class PathController {
    private final PathService service;

    public PathController(PathService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getShortestPath(@RequestParam(name = "source") Long source, @RequestParam(name = "target") Long target) {
        PathResponse response = service.getShortestPath(source, target);
        return ResponseEntity.ok().body(response);
    }
}
