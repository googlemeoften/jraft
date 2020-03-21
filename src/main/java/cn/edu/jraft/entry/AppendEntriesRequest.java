package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppendEntriesRequest extends BaseRequest {

    /**
     * 新的日志条目紧随之前的索引值
     */
    long prevLogIndex;

    /**
     * prevLogIndex 条目的任期号
     */
    long preLogTerm;

    /**
     * leader 已提交日志
     */
    long committedIndex;

    /** 准备存储的日志条目（表示心跳时为空；一次性发送多个是为了提高效率） */
    LogEntry[] entries;
}
