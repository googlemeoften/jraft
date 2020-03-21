package cn.edu.jraft.entry;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseRequest implements Message, Serializable {

    /**
     * 候选人的任期号
     */
    public long term;

    /**
     * 请求者id
     */
    public String serverId;

    /**
     * 被请求者id
     */
    public String peerId;

}