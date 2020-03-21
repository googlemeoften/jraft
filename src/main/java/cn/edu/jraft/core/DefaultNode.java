package cn.edu.jraft.core;

import cn.edu.jraft.Node;
import cn.edu.jraft.common.PeerId;
import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;

public class DefaultNode implements Node, RaftServerService {
    public PeerId getLeader() {
        return null;
    }

    public boolean isLeader() {
        return false;
    }

    public Message handlePreVoteRequest(RequestVoteRequest request) {
        return null;
    }

    public Message handleRequestVoteRequest(RequestVoteRequest request) {
        return null;
    }

    public Message handleAppendEntriesRequest(AppendEntriesRequest request, RpcContext context) {
        return null;
    }
}
