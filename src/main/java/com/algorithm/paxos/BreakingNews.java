package com.algorithm.paxos;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BreakingNews {

    private String uuid;
    private String title;
    private Integer position;

    public BreakingNews(String uuid, String title, Integer position) {
        this.uuid = uuid;
        this.title = title;
        this.position = position;
    }

    public static BreakingNews of(String title, Integer position) {
        return new BreakingNews(UUID.randomUUID().toString(), title, position);
    }

}
