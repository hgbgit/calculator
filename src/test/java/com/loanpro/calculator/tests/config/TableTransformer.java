package com.loanpro.calculator.tests.config;
/*
import br.com.nutri.tool.data.entity.account.Account;
import br.com.nutri.tool.data.entity.account.Owner;
import br.com.nutri.tool.data.entity.claim.Claim;
import br.com.nutri.tool.data.entity.claim.ClaimStatus;
import br.com.nutri.tool.data.entity.key.Key;
import br.com.nutri.tool.data.entity.key.KeyStatus;*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import com.loanpro.calculator.models.Operation;
import com.loanpro.calculator.models.Role;
import io.cucumber.java.DataTableType;
import org.springframework.stereotype.Component;

import java.util.Map;

public class TableTransformer {

    private final ObjectMapper objectMapper;

    public TableTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @DataTableType
    public Role defineRole(Map<String, String> entry) throws JsonProcessingException {
        return objectMapper.readValue(this.getUnflatten(entry), Role.class);
    }

    @DataTableType
    public Operation defineOperation(Map<String, String> entry) throws JsonProcessingException {
        return objectMapper.readValue(this.getUnflatten(entry), Operation.class);
    }

    private String getUnflatten(Map<String, String> entry) throws JsonProcessingException {
        return JsonUnflattener.unflatten(objectMapper.writeValueAsString(entry));
    }
 /*
    @DataTableType
    public Owner defineOwner(Map<String, String> entry) throws JsonProcessingException {
        return objectMapper.readValue(this.getUnflatten(entry), Owner.class);
    }

    @DataTableType
    public Account defineAccount(Map<String, String> entry) throws JsonProcessingException {
        return objectMapper.readValue(this.getUnflatten(entry), Account.class);
    }

    @DataTableType
    public Claim defineClaim(Map<String, String> entry) throws JsonProcessingException {
        return objectMapper.readValue(this.getUnflatten(entry), Claim.class);
    }

    @ParameterType(name = "uuid", value = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}")
    public UUID uuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @ParameterType(name = "keystatus", value = "ATIVO|INATIVO")
    public KeyStatus keyStatus(String keyStatus) {
        return KeyStatus.valueOf(keyStatus);
    }

    @ParameterType(name = "claimstatus", value = "OPEN|WAITING|CONFIRMED|CANCELED|FINISHED")
    public ClaimStatus claimStatus(String claimStatus) {
        return ClaimStatus.valueOf(claimStatus);
    }


*/
}
