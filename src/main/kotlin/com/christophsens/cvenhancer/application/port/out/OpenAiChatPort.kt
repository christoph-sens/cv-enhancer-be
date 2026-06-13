package com.christophsens.cvenhancer.application.port.out

/**
 * Port for external OpenAI Chat service.
 * This is an output port that defines the contract for interacting with AI services.
 */
interface OpenAiChatPort {
    fun call(prompt: String): String
}