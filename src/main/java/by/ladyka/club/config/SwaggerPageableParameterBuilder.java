//package by.ladyka.club.config;
//
//
//import org.springframework.core.MethodParameter;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Component;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.Parameter;
//import springfox.documentation.service.ResolvedMethodParameter;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.ParameterBuilderPlugin;
//import springfox.documentation.spi.service.contexts.ParameterContext;
//import springfox.documentation.swagger.common.SwaggerPluginSupport;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
//public class SwaggerPageableParameterBuilder implements ParameterBuilderPlugin {
//
//	@Override
//	public boolean supports(DocumentationType delimiter) {
//		return DocumentationType.SWAGGER_2.equals(delimiter);
//	}
//
//	@Override
//	public void apply(ParameterContext parameterContext) {
//		final ResolvedMethodParameter resolvedMethodParameter = parameterContext.resolvedMethodParameter();
//		if (Pageable.class.equals(resolvedMethodParameter.getParameterType().)) {
//			List<Parameter> parameters = new ArrayList<>();
//			parameters.add(parameterContext.parameterBuilder()
//					.parameterType("query")
//					.name("page")
//					.modelRef(new ModelRef("int"))
//					.description("zero-based page index")
//					.build());
//			parameters.add(parameterContext.parameterBuilder()
//					.parameterType("query")
//					.name("size")
//					.modelRef(new ModelRef("int"))
//					.description("the size of the page to be returned")
//					.build());
//			parameters.add(parameterContext.parameterBuilder()
//					.parameterType("query")
//					.name("sort")
//					.modelRef(new ModelRef("string"))
//					.allowMultiple(true)
//					.description("sorting criteria. example: sort=firstname&sort=lastname,asc")
//					.build());
//			parameterContext.getOperationContext().operationBuilder().parameters(parameters);
//		}
//	}
//}
