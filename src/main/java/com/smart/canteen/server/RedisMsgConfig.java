package com.smart.canteen.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author lc
 * @date 2020/3/15下午 7:04
 */
public class RedisMsgConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter readCard, MessageListenerAdapter updateMsg) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(readCard, new PatternTopic("readCard"));
        container.addMessageListener(updateMsg, new PatternTopic("update"));
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter readCard(MsgReceiver receiver) {
        return new MessageListenerAdapter(receiver, "readCard");
    }

    @Bean
    MessageListenerAdapter updateMsg(MsgReceiver receiver) {
        return new MessageListenerAdapter(receiver, "updateMessage");
    }


}
