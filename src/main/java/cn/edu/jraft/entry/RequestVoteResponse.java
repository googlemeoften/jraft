package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RequestVoteResponse implements Message, Serializable {
    /**
     * 当前任期号，以便于候选人去更新自己的任期
     */
    int term;

    /**
     * 是否获取到投票
     */
    boolean granted;
}
