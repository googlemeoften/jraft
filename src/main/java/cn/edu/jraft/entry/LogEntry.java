package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LogEntry implements Serializable {
    /**
     *
     */
    private Long index;

    /**
     * 任期
     */
    private long term;

    /**
     * 键值
     */
    private Pair pair;
}
