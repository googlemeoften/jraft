package cn.edu.jraft.rpc.processor;

import cn.edu.jraft.entry.Message;
import cn.edu.jraft.entry.RequestVoteRequest;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;

import java.util.concurrent.Executor;

public class RequestVoteRequestProcessor extends RpcRequestProcessor<RequestVoteRequest> {

    public RequestVoteRequestProcessor(Executor executor, RaftServerService raftServerService) {
        super(executor, raftServerService);
    }

    public Message processRequest(RequestVoteRequest request, RpcContext context) {

        if(request.isPreVote()){

        }else {

        }


        return null;
    }

    public String interest() {
        return null;
    }
}
