package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestVoteRequest extends BaseRequest {
    /**
     * 候选人的最后日志条目的索引值
     */
    long lastLogIndex;

    /**
     * 候选人最后日志条目的任期号
     */
    long lastLogTerm;

    /**
     * 是否预投票
     */
    boolean preVote;
}
