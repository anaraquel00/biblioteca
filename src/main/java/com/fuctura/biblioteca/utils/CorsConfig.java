package com.fuctura.biblioteca.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {
    // Configurações de CORS podem ser definidas aqui, se necessário.
    // Por exemplo, você pode definir métodos permitidos, origens, etc.
    // No entanto, geralmente é melhor configurar CORS diretamente no controlador ou em um arquivo de configuração separado.
    // Exemplo de configuração de CORS:
    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                     .allowedMethods("GET", "POST", "PUT", "DELETE")
    //                     .allowedOrigins("*")
    //                     .allowedHeaders("*");
    //         }
}
