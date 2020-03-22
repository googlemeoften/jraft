package cn.edu.jraft.rpc.processor;

import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;

import java.util.concurrent.Executor;

public class RequestVoteRequestProcessor extends RpcRequestProcessor<RequestVoteRequest> {

    public RequestVoteRequestProcessor(Executor executor) {
        super(executor);
    }

    @Override
    public Message doProcessRequest(RaftServerService raftServerService, RequestVoteRequest request, RpcContext context) {
        if (request.isPreVote()) {
            return raftServerService.handlePreVoteRequest(request);
        } else {
            return raftServerService.handleRequestVoteRequest(request);
        }
    }

    public String interest() {
        return RequestVoteRequest.class.getName();
    }
}
