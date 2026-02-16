package com.himanshu.boardgamebuddy;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SpringAiBoardGameService implements BoardGameService {

    private final ChatClient chatClient;

    public SpringAiBoardGameService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();    //create a chat client
    }

    @Override
    public Answer askQuestion(Question question) {
        // defining the prompt
        var answerText = chatClient.prompt()
                //submit a question
                .user(question.question())
                // finished defining the prompt and are ready to submit the prompt to LLM
                .call()
//                submit the prompt and return the content of response
                .content();
        return new Answer(answerText);
    }
}
