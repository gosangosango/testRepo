/**
 * Copyright (c) 1998-2018 Miracom Inc. All rights reserved.
 *
 * Don't copy or redistribute this source code without permission.
 * This software is provided "As Is" and any expressed or implied
 * warranties, including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose are disclaimed.
 * In no event shall Miracom Inc. or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential
 * damages including, but not limited to, procurement of substitute
 * goods or services; loss of use, data, or profits; or business
 * interruption) however caused and on any theory of liability, whether
 * in contract, strict liability, or tort (including negligence or otherwise)
 * arising in any way out of the use of this software, even if advised
 * of the possibility of such damage.
 *
 * For more information on this product, please see
 * http://www.miracom.co.kr
 */
package kr.co.miracom.mes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author myjung.jung
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                        .apis(RequestHandlerSelectors.basePackage("kr.co.miracom.mes")).paths(PathSelectors.any())
                        .build();

    }
}
