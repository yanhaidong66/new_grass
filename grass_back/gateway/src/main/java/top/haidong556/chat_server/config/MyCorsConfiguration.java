package top.haidong556.chat_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Component
public class MyCorsConfiguration {

    /**
     *
     * @return CorsWebFilter 为愧于的webfilter 有spring框架提供
     */
    @Bean
    public CorsWebFilter corsWebFilter(){

        /**
         * 返回一个CorsWebFilter ，构造其中需要传入连个形参，均为接口，可以直接new 接口
         * 是借口可以使用它的实现类来处理
         */
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        //构建CorsConfiguration
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");


        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }

}
