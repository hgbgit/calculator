package com.loanpro.calculator.tests.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.loanpro.calculator.repository.OperationRepository;
import com.loanpro.calculator.repository.RecordRepository;
import com.loanpro.calculator.repository.RoleRepository;
import com.loanpro.calculator.repository.UserRepository;
import com.loanpro.calculator.tests.data.LoanProCaculatorTestData;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;

@Configuration
@MockBeans({@MockBean(UserRepository.class),
        @MockBean(RoleRepository.class),
        @MockBean(RecordRepository.class),
        @MockBean(OperationRepository.class)})
public class LoanProCalculatorTestConfig {

    @Bean
    public LoanProCaculatorTestData loanProCaculatorTestDataTestData() {
        return new LoanProCaculatorTestData();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.featuresToEnable(WRITE_BIGDECIMAL_AS_PLAIN)
                .modules(new JSR310Module())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }
}
