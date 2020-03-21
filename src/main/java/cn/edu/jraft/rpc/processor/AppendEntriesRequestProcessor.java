package cn.edu.jraft.rpc.processor;

import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;

import java.util.concurrent.Executor;

public class AppendEntriesRequestProcessor extends RpcRequestProcessor<AppendEntriesRequest> {
    public AppendEntriesRequestProcessor(Executor executor, RaftServerService raftServerService) {
        super(executor, raftServerService);
    }

    public Message doProcessRequest(AppendEntriesRequest request, RpcContext context) {
        return raftServerService.handleAppendEntriesRequest(request, context);
    }

    public String interest() {
        return AppendEntriesRequest.class.getName();
    }
}
