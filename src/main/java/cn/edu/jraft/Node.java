package cn.edu.jraft;

import cn.edu.jraft.common.PeerId;

public interface Node {
    static Node getInstance() {
        return null;
    }

    PeerId getLeader();

    boolean isLeader();

}
