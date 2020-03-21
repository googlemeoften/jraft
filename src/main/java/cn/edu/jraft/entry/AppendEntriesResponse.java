package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class AppendEntriesResponse implements Message, Serializable {
    /**
     * 当前的任期号，用于领导人去更新自己
     */
    int term;

    /**
     * 跟随者包含了匹配上 prevLogIndex 和 prevLogTerm 的日志时为真
     */
    boolean success;

    /**
     * 最后的日志log
     */
    long lastLogIndex;
}
