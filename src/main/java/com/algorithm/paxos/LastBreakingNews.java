package com.algorithm.paxos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LastBreakingNews {

    private static final ConcurrentLinkedDeque<BreakingNews> breakingNewsConcurrentLinkedDeque = new ConcurrentLinkedDeque<>();

    public BreakingNews push(BreakingNewsDTO breakingNewsDTO) {
        BreakingNews topBreakingNews = breakingNewsConcurrentLinkedDeque.peekFirst();

        if (topBreakingNews == null) {
            BreakingNews breakingNews = BreakingNews.of(breakingNewsDTO.getTitle(), breakingNewsDTO.getPosition());
            breakingNewsConcurrentLinkedDeque.push(breakingNews);
            return breakingNews;

        }
        if (topBreakingNews.getPosition() > breakingNewsDTO.getPosition()) {
            BreakingNews breakingNews = BreakingNews.of(breakingNewsDTO.getTitle(), breakingNewsDTO.getPosition());
            breakingNewsConcurrentLinkedDeque.push(breakingNews);
            return breakingNews;
        }

        log.info("Breaking News : {}/{} is older than {}/{} ", breakingNewsDTO.getPosition(), breakingNewsDTO.getTitle(), topBreakingNews.getPosition(), topBreakingNews.getTitle());
        throw new RuntimeException("Breaking News is older than " + topBreakingNews.getPosition());
    }

    public BreakingNews first() {
        return breakingNewsConcurrentLinkedDeque.peekFirst();
    }

}
