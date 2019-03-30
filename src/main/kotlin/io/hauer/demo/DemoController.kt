package io.hauer.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    @GetMapping("/public")
    fun getPublicHello(): String {
        return "Hello public!"
    }

    @GetMapping("/user")
    fun getUserHello(): String {
        return "Hello user!"
    }

    @GetMapping("/editor")
    fun getEditorHello(): String {
        return "Hello editor!"
    }

    @GetMapping("/admin")
    fun getAdminHello(): String {
        return "Hello admin!"
    }
}