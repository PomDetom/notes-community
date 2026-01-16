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
        // Consumer Side
        if (RpcContext.getContext().isConsumerSide()) {
            Long userId = UserContext.getUserId();
            String token = UserContext.getToken();
            log.info("Dubbo Consumer: Preparing to invoke. UserId: {}, Token: {}", userId, token);

            if (userId != null) {
                // Dubbo 3 recommended way for passing attachments to provider
                RpcContext.getClientAttachment().setAttachment(USER_ID_KEY, String.valueOf(userId));
            }
            if (token != null) {
                RpcContext.getClientAttachment().setAttachment(TOKEN_KEY, token);
            }
        }

        // Provider Side
        if (RpcContext.getContext().isProviderSide()) {
            // Dubbo 3 recommended way for reading attachments from consumer
            String userIdStr = RpcContext.getServerAttachment().getAttachment(USER_ID_KEY);
            String token = RpcContext.getServerAttachment().getAttachment(TOKEN_KEY);

            log.info("Dubbo Provider: Received invocation. UserId: {}, Token: {}", userIdStr, token);

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
                log.info("Dubbo Provider: Clearing UserContext.");
                UserContext.clear();
            }
        }
    }
}
