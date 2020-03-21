package cn.edu.jraft.entry;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 键值对
 */
@Getter
@Setter
public class Pair implements Serializable {
    /**
     * 键
     */
    String key;
    /**
     * 值
     */
    String value;
}
