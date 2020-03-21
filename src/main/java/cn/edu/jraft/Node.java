package cn.edu.jraft;

import cn.edu.jraft.common.PeerId;

public interface Node {
    PeerId getLeader();

    boolean isLeader();

}
