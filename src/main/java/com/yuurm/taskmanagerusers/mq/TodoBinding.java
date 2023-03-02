package com.yuurm.taskmanagerusers.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

// интерфейс, который нужен для работы mq
// здесь создаем нужные нам каналы message broker
public interface TodoBinding { // назавние может быть любым

    String OUTPUT_CHANNEL = "todoOutputChannel"; // нужен, чтобы на него ссылаться, а не везде использовать антипаттерн magic string

    // создает канал для отправки сообщений
    @Output(OUTPUT_CHANNEL)
    MessageChannel todoOutputChannel();// название канала может браться из названия метода (если не указано в аннотации)
}