package cn.edu.jraft.rpc;

import cn.edu.jraft.entry.Message;
import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;

public class RpcContext {
    private final BizContext bizContext;
    private final AsyncContext asyncContext;

    public RpcContext(BizContext bizContext, AsyncContext asyncContext) {
        this.bizContext = bizContext;
        this.asyncContext = asyncContext;
    }

    public void sendResponse(Message message) {
        asyncContext.sendResponse(message);
    }
}
