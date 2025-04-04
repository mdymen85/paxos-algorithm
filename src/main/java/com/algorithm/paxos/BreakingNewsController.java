package com.algorithm.paxos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BreakingNewsController {

    private final LastBreakingNews lastBreakingNews;

    @RequestMapping(path = "/v1/breakingnews", method = RequestMethod.POST)
    public ResponseEntity<BreakingNews> breakingNews(@RequestBody BreakingNewsDTO breakingNews) {
        return ResponseEntity.ok(lastBreakingNews.push(breakingNews));
    }

}
