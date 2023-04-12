package net.redciscso.javapmproject.dto;

import lombok.Data;

@Data
public class SessionInfo {
    private Integer tryCount;
    private Integer bestScores;
    private Integer lastScores;
}
