package cn.edu.jraft.rpc.processor;

import cn.edu.jraft.entry.AppendEntriesRequest;
import cn.edu.jraft.entry.Message;
import cn.edu.jraft.rpc.RaftServerService;
import cn.edu.jraft.rpc.RpcContext;
import com.alipay.remoting.Connection;
import com.alipay.remoting.ConnectionEventProcessor;

import java.util.concurrent.Executor;

public class AppendEntriesRequestProcessor extends RpcRequestProcessor<AppendEntriesRequest> implements ConnectionEventProcessor {
    public AppendEntriesRequestProcessor(Executor executor) {
        super(executor);
    }

    @Override
    public Message doProcessRequest(RaftServerService raftServerService, AppendEntriesRequest request, RpcContext context) {
        return raftServerService.handleAppendEntriesRequest(request, context);
    }

    public String interest() {
        return AppendEntriesRequest.class.getName();
    }

    @Override
    public void onEvent(String remoteAddr, Connection conn) {

    }
}
