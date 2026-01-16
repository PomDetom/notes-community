package com.pomdetom.notes.common.filter;

import com.pomdetom.notes.common.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * Dubbo 上下文过滤器
 * 负责在 Consumer 端将 UserContext 写入 RpcContext
 * 负责在 Provider 端从 RpcContext 读取并写入 UserContext
 */
@Slf4j
@Activate(group = { CommonConstants.PROVIDER, CommonConstants.CONSUMER })
public class DubboContextFilter implements Filter {

    private static final String USER_ID_KEY = "User-Id";
    private static final String TOKEN_KEY = "Authorization";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext rpcContext = RpcContext.getContext();

        // Consumer Side: Write Context -> RPC Attachment
        if (RpcContext.getContext().isConsumerSide()) {
            Long userId = UserContext.getUserId();
            if (userId != null) {
                rpcContext.setAttachment(USER_ID_KEY, String.valueOf(userId));
            }
            String token = UserContext.getToken();
            if (token != null) {
                rpcContext.setAttachment(TOKEN_KEY, token);
            }
        }

        // Provider Side: Read RPC Attachment -> Context
        if (RpcContext.getContext().isProviderSide()) {
            String userIdStr = rpcContext.getAttachment(USER_ID_KEY);
            String token = rpcContext.getAttachment(TOKEN_KEY);

            if (userIdStr != null) {
                try {
                    UserContext.setUserId(Long.valueOf(userIdStr));
                } catch (NumberFormatException e) {
                    log.error("Invalid User-Id format in Dubbo Context: {}", userIdStr);
                }
            }
            if (token != null) {
                UserContext.setToken(token);
            }
        }

        try {
            return invoker.invoke(invocation);
        } finally {
            // Provider Side: Clean up Context
            if (RpcContext.getContext().isProviderSide()) {
                UserContext.clear();
            }
        }
    }
}
