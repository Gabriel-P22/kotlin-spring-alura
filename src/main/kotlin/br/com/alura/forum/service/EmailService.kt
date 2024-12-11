package br.com.alura.forum.service

import org.apache.logging.log4j.message.SimpleMessage
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val sender: JavaMailSender
) {

    fun send(user: String) {
        val message = SimpleMailMessage();
        message.subject = "[Alura] Answer Received"
        message.text = "Hey, your question has already been answered. Shall we take a look?"
        message.setTo(user)
        sender.send(message)

    }

}