package com.yuurm.taskmanagertasks.mq;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import com.yuurm.taskmanagertasks.service.TestDataService;


@EnableBinding(TodoBinding.class)
@Component
public class MessageConsumer {

    private TestDataService testDataService;

    private MessageConsumer(TestDataService testDataService){
        this.testDataService = testDataService;
    }

    // метод вызывается автометически как только появляется сообщение в канале
    @StreamListener(target = TodoBinding.INPUT_CHANNEL)
    public void initTestData(Long userId) throws Exception {
        throw new Exception("test dlq");
        //testDataService.initTestData(userId);
    }
}
