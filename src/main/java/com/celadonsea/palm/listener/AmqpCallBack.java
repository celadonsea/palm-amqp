package com.celadonsea.palm.listener;

import com.celadonsea.palm.client.MessageClient;
import com.celadonsea.palm.config.MessageClientConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Provides functionality for arrived message callback.
 *
 * @author Rafael Revesz
 * @since 1.0
 */
@Slf4j
public class AmqpCallBack extends CallBack implements Consumer {

    public AmqpCallBack(MessageClient messageClient, MessageClientConfig messageClientConfig) {
        super(messageClient, messageClientConfig);
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        log.debug("Consumer Ok: {}", consumerTag);
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        log.debug("Cancel Ok: {}", consumerTag);
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        log.debug("Cancel: {}", consumerTag);
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        log.debug("Shutdown signal: {}", consumerTag);
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        if(log.isDebugEnabled()) {
            log.debug("Recovery Ok: {}", consumerTag);
        }
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        messageArrived(envelope.getRoutingKey(), body);
    }
}
