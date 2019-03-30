package io.hauer.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Title("Controller spec")
@Narrative("Test if the endpoints need the expected roles to access")
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @WithAnonymousUser
    def "public endpoint with anonymous user"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello public!"))
    }

    @WithMockUser(roles ="USER")
    def "public endpoint as user"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello public!"))
    }

    @WithMockUser(roles ="EDITOR")
    def "public endpoint as editor"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello public!"))
    }

    @WithMockUser(roles ="ADMIN")
    def "public endpoint as admin"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello public!"))
    }

    @WithAnonymousUser
    def "user endpoint with anonymous user"() {
        expect: "status unauthorized"
        mockMvc.perform(get("/user")).andExpect(status().isUnauthorized())
    }

    @WithMockUser(roles ="USER")
    def "user endpoint as user"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello user!"))
    }

    @WithMockUser(roles ="EDITOR")
    def "user endpoint as editor"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello user!"))
    }

    @WithMockUser(roles ="ADMIN")
    def "user endpoint as admin"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello user!"))
    }

    @WithAnonymousUser
    def "editor endpoint with anonymous user"() {
        expect: "status unauthorized"
        mockMvc.perform(get("/editor")).andExpect(status().isUnauthorized())
    }

    @WithMockUser(roles ="USER")
    def "editor endpoint as user"() {
        expect: "status forbidden"
        mockMvc.perform(get("/editor")).andExpect(status().isForbidden())
    }

    @WithMockUser(roles ="EDITOR")
    def "editor endpoint as editor"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/editor"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello editor!"))
    }

    @WithMockUser(roles ="ADMIN")
    def "editor endpoint as admin"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/editor"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello editor!"))
    }

    @WithAnonymousUser
    def "admin endpoint with anonymous user"() {
        expect: "status unauthorized"
        mockMvc.perform(get("/admin")).andExpect(status().isUnauthorized())
    }

    @WithMockUser(roles ="USER")
    def "admin endpoint as user"() {
        expect: "status forbidden"
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden())
    }

    @WithMockUser(roles ="EDITOR")
    def "admin endpoint as editor"() {
        expect: "status forbidden"
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden())
    }

    @WithMockUser(roles ="ADMIN")
    def "admin endpoint as admin"() {
        expect: "status 200 and response"
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello admin!"))
    }
}
