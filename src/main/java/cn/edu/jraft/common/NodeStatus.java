package cn.edu.jraft.common;

public enum NodeStatus {
    LEADER(1, "leader"), CANDIDATE(2, "candidate"), FOLLOWER(3, "follower");

    private final int code;
    private final String msg;

    NodeStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
