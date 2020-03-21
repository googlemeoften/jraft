package cn.edu.jraft.rpc;

import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;

public interface RaftServerService {
    Message handlePreVoteRequest(RequestVoteRequest request);

    Message handleRequestVoteRequest(RequestVoteRequest request);

    Message handleAppendEntriesRequest(AppendEntriesRequest request, RpcContext context);
}
