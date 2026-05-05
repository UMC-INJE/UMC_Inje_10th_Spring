/*package com.example.umc10th.global.config;

public class SwaggerConfig {
}
*/
package com.example.umc10th.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI UMCstudyAPI() {
        Info info = new Info()
                .title("UMC 10th 배달 앱 API")
                .description("UMC 10기 워크북 미션용 API 명세서입니다.")
                .version("1.0.0");

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info);
    }
}