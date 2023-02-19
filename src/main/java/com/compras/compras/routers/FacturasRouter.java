package com.compras.compras.routers;

import com.compras.compras.collections.Factura;
import com.compras.compras.model.FacturaDTO;
import com.compras.compras.model.Producto;
import com.compras.compras.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FacturasRouter {


    @Bean
    @CrossOrigin(origins = "*")
    @RouterOperation
            (
                    path = "/getAll/{page}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = FacturasRouter.class,
                    beanMethod = "getAllProducts",
                    operation = @Operation(
                            operationId = "getAllProducts",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Succesful",
                                            content = @Content(schema = @Schema(
                                                    implementation = FacturaDTO.class
                                            ))
                                    )
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "page")
                            }
                    )
            )
    public RouterFunction<ServerResponse> getAllProducts(GetAllProductsUseCase getAllProductsUseCase) {
        return route(
                GET("/getAll/{page}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProductsUseCase
                                .getAllProducts(request.pathVariable("page")),Producto[].class
                        ))
        );
    }
   @Bean
   @RouterOperation
           (
                   path = "/getAllBills",
                   produces = {
                           MediaType.APPLICATION_JSON_VALUE
                   },
                   method = RequestMethod.GET,
                   beanClass = FacturasRouter.class,
                   beanMethod = "getAllBills",
                   operation = @Operation(
                           operationId = "getAllBills",
                           responses = {
                                   @ApiResponse(
                                           responseCode = "200",
                                           description = "Succesful",
                                           content = @Content(schema = @Schema(
                                                   implementation = FacturaDTO.class
                                           ))
                                   )
                           }
                   )
           )
   public RouterFunction<ServerResponse> getAllBills(GetAllBillsUseCase getAllBillsUseCase) {
       return route(
               GET("/getAllBills"),
               request -> ok()
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(BodyInserters.fromPublisher(getAllBillsUseCase.get(), FacturaDTO.class))
       );
   }
    @Bean
    @RouterOperation(
            path = "/createBill",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = FacturasRouter.class,
            beanMethod = "create",
            operation = @Operation(
                    operationId = "create",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Succesful",
                                    content = @Content(schema = @Schema(
                                            implementation = FacturaDTO.class
                                    ))
                            ),
                            @ApiResponse(
                                    responseCode  ="400", description = "Not found"
                            ),
                    },
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = FacturaDTO.class)))

            )
    )
    public RouterFunction<ServerResponse> create(CreateFacturaUseCase createFacturaUseCase){
        Function<FacturaDTO, Mono<ServerResponse>> executor = facturaDTO ->  createFacturaUseCase.apply(facturaDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createBill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(FacturaDTO.class).flatMap(executor)
        );

    }
    @Bean
    @RouterOperation
            (
                    path = "/seekByName/{name}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = FacturasRouter.class,
                    beanMethod = "findById",
                    operation = @Operation(
                            operationId = "findById",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Succesful",
                                            content = @Content(schema = @Schema(
                                                    implementation = FacturaDTO.class
                                            ))
                                    )
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "name")
                            }
                    )
            )
    public RouterFunction<ServerResponse> findById(FindByNameUseCase findByNameUseCase) {
        return route(
                GET("/seekByName/{name}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByNameUseCase
                                .findByName(request.pathVariable("name")),Producto[].class
                        ))
        );
    }
    @Bean
    @RouterOperation
            (
                    path = "/seekById/{id}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.GET,
                    beanClass = FacturasRouter.class,
                    beanMethod = "seekById",
                    operation = @Operation(
                            operationId = "seekById",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Succesful",
                                            content = @Content(schema = @Schema(
                                                    implementation = FacturaDTO.class
                                            ))
                                    )
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id")
                            }
                    )
            )
    public RouterFunction<ServerResponse> seekById(FindByIdUseCase findByIdUseCase) {
        return route(
                GET("/seekById/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findByIdUseCase
                                .findById(request.pathVariable("id")),Producto.class
                        ))
        );
    }

    @Bean
    @RouterOperation
            (
                    path = "/updateInventary/{id}/{quantity}",
                    produces = {
                            MediaType.APPLICATION_JSON_VALUE
                    },
                    method = RequestMethod.PATCH,
                    beanClass = FacturasRouter.class,
                    beanMethod = "updateInventary",
                    operation = @Operation(
                            operationId = "updateInventary",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Succesful",
                                            content = @Content(schema = @Schema(
                                                    implementation = FacturaDTO.class
                                            ))
                                    )
                            },
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id"),
                                    @Parameter(in = ParameterIn.PATH, name = "quantity")
                            }
                    )
            )
    public RouterFunction<ServerResponse> updateInventary(UpdateProductUseCase updateProductUseCase) {
        return route(PATCH("/updateInventary/{id}/{quantity}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .accepted()
                        .body(BodyInserters
                                .fromPublisher(updateProductUseCase
                                                .updateById(request.pathVariable("id"),
                                                        Integer.valueOf(request.pathVariable("quantity"))),
                                        Producto.class)));
    }
    @Bean
    public RouterFunction<ServerResponse> createProduct(CreateProductUseCase createProductUseCase) {
        Function<Producto, Mono<ServerResponse>> executor = producto ->   createProductUseCase.apply(producto)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/createProduct").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Producto.class).flatMap(executor)
        );

    }
    @Bean
    public RouterFunction<ServerResponse> updateProduct(UpdateTotalProdUseCase updateTotalProdUseCase) {
        Function<Producto, Mono<ServerResponse>> executor = producto ->   updateTotalProdUseCase.apply(producto)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                PUT("/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Producto.class).flatMap(executor)
        );

    }
    @Bean
    public RouterFunction<ServerResponse> patchState(PatchStateUseCase patchStateUseCase) {
        return route(
                PATCH("/changeState/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .body(BodyInserters.fromPublisher(patchStateUseCase.apply(request.pathVariable("id")),Producto.class))
        );
    }


}
