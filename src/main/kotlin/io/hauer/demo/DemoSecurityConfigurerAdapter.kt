package io.hauer.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class DemoSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("user").password(PASSWORD).authorities(ROLE_USER)
        auth.inMemoryAuthentication().withUser("editor").password(PASSWORD).authorities(ROLE_EDITOR)
        auth.inMemoryAuthentication().withUser("admin").password(PASSWORD).authorities(ROLE_ADMIN)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests() //
                .mvcMatchers("/admin").access(has(ROLE_ADMIN)) //
                .mvcMatchers("/editor").access(has(ROLE_EDITOR)) //
                .mvcMatchers("/user").access(has(ROLE_USER)) //
                .mvcMatchers("/**").permitAll()
                .anyRequest().authenticated()
        http.httpBasic()
    }

    @Bean
    fun roleHierarchy(): RoleHierarchy = RoleHierarchyImpl().apply {
        setHierarchy("$ROLE_EDITOR > $ROLE_USER \n $ROLE_ADMIN > $ROLE_EDITOR")
    }

    private fun has(role: String): String {
        return String.format("hasRole('%s')", role)
    }

    companion object {
        private const val ROLE_USER = "ROLE_USER"
        private const val ROLE_EDITOR = "ROLE_EDITOR"
        private const val ROLE_ADMIN = "ROLE_ADMIN"
        private const val PASSWORD = "{noop}password"
    }


}