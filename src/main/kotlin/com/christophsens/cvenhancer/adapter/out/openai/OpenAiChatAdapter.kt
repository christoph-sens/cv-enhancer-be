package com.christophsens.cvenhancer.adapter.out.openai

import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import org.springframework.ai.chat.model.ChatModel
import org.springframework.stereotype.Component

/**
 * Adapter implementing OpenAiChatPort.
 */
@Component
class OpenAiChatAdapter(
    private val chatModel: ChatModel
) : OpenAiChatPort {

    override fun call(prompt: String): String {
        return chatModel.call(prompt)?: ""
    }
}