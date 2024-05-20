package kr.co.littleriders.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import kr.co.littleriders.backend.global.auth.annotation.Auth;
import org.springdoc.core.utils.SpringDocAnnotationsUtils;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info =
        @Info(
                title = "littleriders API Document",
                description = "littleriders 프로젝트의 API 명세서",
                version = "v1"),
        servers = {
                @Server(url = "/api", description = ""),
        }
)

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        String schema = "Authorization";
        SpringDocUtils.getConfig().addAnnotationsToIgnore(Auth.class);
        SpringDocAnnotationsUtils.getAnnotation(Auth.class);


        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("littleriders")
                        .version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList(schema))
                .components(new Components()
                        .addSecuritySchemes(schema,
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .name(schema)
                                        .scheme("Bearer")
                                        .bearerFormat(schema)));

    }
}
