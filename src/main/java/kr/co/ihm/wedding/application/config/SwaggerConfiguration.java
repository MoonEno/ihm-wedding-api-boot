package kr.co.ihm.wedding.application.config;///**
// * (주)오픈잇 | http://www.openit.co.kr
// * Copyright (c)2006-2021, openit Inc.
// * All right reserved.
// */
//package kr.co.openit.openmallbackmd.application.config;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.ResponseEntity;
//
//import kr.co.openit.openmallbackmd.common.ServiceConstant;
//
//import com.google.common.collect.Lists;
//
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// * @author DLNA
// * @desc SwaggerConfiguration
// */
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//
//    @Bean
//    public Docket swaggerSpringfoxDocket() {
//
//		Docket docket = new Docket(DocumentationType.SWAGGER_2)
//				.apiInfo(apiInfo())
//				.pathMapping("/")
//				.forCodeGeneration(true)
//				.genericModelSubstitutes(ResponseEntity.class)
//				.ignoredParameterTypes(java.sql.Date.class)
//				.securityContexts(Lists.newArrayList(securityContext()))
//				.securitySchemes(Lists.newArrayList(authorizationKey(), httpRequestKey()))
//				.useDefaultResponseMessages(false);
//
//		docket = docket
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("kr.co.openit.openmallbackmd.api"))
//				.paths(PathSelectors.any()).build();
//
//		return docket;
//    }
//
//    /**
//     * apiInfo
//     *
//     * @return
//     */
//    private ApiInfo apiInfo() {
//
//        return new ApiInfo(
//                "Open Mall MD Back-end API",               		// Title
//                "Open Mall MD Back-end APIs",						// Description
//                "v1.0",                                      	// Version
//                "",                                          	// termsOfServiceUrl
//                new Contact("DLNA", "","dlna@openit.co.kr"), 	// contact (Name, Url, Email)
//                "",                                          	// license
//                "",                                          	// licenseUrl
//                Collections.emptyList());
//    }
//
//	/**
//	 * authorizationKey
//	 * @return
//	 */
//	private ApiKey authorizationKey() {
//		return new ApiKey("JWT_TOKEN", ServiceConstant.Keys.AUTHORIZATION, "header");
//	}
//
//	/**
//	 * httpRequestKey
//	 * @return
//	 */
//	private ApiKey httpRequestKey() {
//		return new ApiKey("HTTP_REQUEST", ServiceConstant.Keys.X_REQUESTED_WITH, "header");
//	}
//
//	/**
//	 * securityContext
//	 * @return
//	 */
//	private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
//
//		return springfox.documentation.spi.service.contexts.SecurityContext.builder()
//				.securityReferences(defaultAuth())
//				.forPaths(PathSelectors.any())
//				.build();
//	}
//
//	/**
//	 * defaultAuth
//	 * @return
//	 */
//	private List<SecurityReference> defaultAuth() {
//
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//
//		return Lists.newArrayList(new SecurityReference("JWT_TOKEN", authorizationScopes),
//				new SecurityReference("HTTP_REQUEST", authorizationScopes));
//	}
//}
