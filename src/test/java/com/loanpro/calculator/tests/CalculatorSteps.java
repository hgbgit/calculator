package com.loanpro.calculator.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanpro.calculator.repository.OperationRepository;
import com.loanpro.calculator.repository.RecordRepository;
import com.loanpro.calculator.repository.RoleRepository;
import com.loanpro.calculator.repository.UserRepository;
import com.loanpro.calculator.tests.data.LoanProCaculatorTestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;

@AllArgsConstructor
public class CalculatorSteps {

    public static final String CALCULATION_ENDPOINT = "/api/calculation";

    private final OperationRepository operationRepository;

    private final RecordRepository recordRepository;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final LoanProCaculatorTestData testData;

    private final ResourceLoader resourceLoader;


    @Before
    public void setUp() {
        this.testData.reset();
    }

    @After
    public void wrapUp() {
        reset(operationRepository, recordRepository, roleRepository, userRepository);
    }



    /*@Given("this are the owners currently in database:")
    public void thisAreTheOwnersCurrentlyInDatabase(List<Owner> owners) {
        given(ownerRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        given(ownerRepository.findByDocument(any(String.class))).willReturn(Optional.empty());

        given(ownerRepository.findAll()).willReturn(owners);
        owners.forEach(own -> given(ownerRepository.findById(eq(own.getId()))).willReturn(Optional.of(own)));
        owners.forEach(own -> given(ownerRepository.findByDocument(eq(own.getDocument()))).willReturn(Optional.of(own)));
    }

    @Given("this are the accounts currently in database:")
    public void thisAreTheAccountsCurrentlyInDatabase(List<Account> accounts) {
        given(accountRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        given(accountRepository.findByMpa(any(String.class))).willReturn(Optional.empty());

        given(accountRepository.findAll()).willReturn(accounts);
        accounts.forEach(acc -> given(accountRepository.findById(eq(acc.getId()))).willReturn(Optional.of(acc)));
        accounts.forEach(acc -> given(accountRepository.findByMpa(eq(acc.getMpa()))).willReturn(Optional.of(acc)));
    }

    @Given("the database next key insertion data will be:")
    public void theNextKeyInsertionDataWillBe(Map<String, Object> nextInsertionDataMap) throws JsonProcessingException {
        NextInsertionData nextInsertionData = objectMapper.readValue(objectMapper.writeValueAsString(nextInsertionDataMap), NextInsertionData.class);
        this.testData.setNextInsertionData(nextInsertionData);
    }

    @Given("this are the keys currently in database:")
    public void thisAreTheKeysCurrentlyInDatabase(List<Key> keys) {
        given(keyRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        given(keyRepository.findByIdAndAccountMpaAndKeyStatus(any(UUID.class), anyString(), any(KeyStatus.class))).willReturn(Optional.empty());
        given(keyRepository.findByAccountIdAndKeyStatus(any(UUID.class), any(KeyStatus.class))).willReturn(Collections.emptySet());
        given(keyRepository.findAll()).willReturn(keys);

        keys.forEach(key -> given(keyRepository.findById(eq(key.getId()))).willReturn(Optional.of(key)));

        keys.forEach(key -> Optional.ofNullable(key.getAccount())
                .ifPresent(account -> given(keyRepository.findByIdAndAccountMpaAndKeyStatus(eq(key.getId()), ArgumentMatchers.eq(account.getMpa()), eq(key.getKeyStatus()))).willReturn(
                        Optional.of(key))));
        keys.stream()
                .filter(key -> Objects.nonNull(key.getAccount()))
                .collect(Collectors.groupingBy(key -> Pair.of(key.getAccount()
                        .getId(), key.getKeyStatus())))
                .forEach((keyPair, proprietaryKeys) -> given(this.keyRepository.findByAccountIdAndKeyStatus(ArgumentMatchers.eq(keyPair.getFirst()), ArgumentMatchers.eq(keyPair.getSecond())))
                        .willReturn(new HashSet<>(proprietaryKeys)));

        keys.forEach(key -> given(keyRepository.save(any(Key.class))).will(invocation -> {
            Map<String, Key> keyMap = keys.stream()
                    .collect(Collectors.toMap(Key::getKeyValue, Function.identity()));
            Key keyToSave = invocation.getArgument(0);
            String keyValueToSave = keyToSave.getKeyValue();

            Optional.ofNullable(keyMap.get(keyValueToSave))
                    .ifPresentOrElse(k -> {
                        throw new DataIntegrityViolationException("Key already exists: " + k.getKeyValue());
                    }, () -> {
                        NextInsertionData nextInsertionData = this.testData.getNextInsertionData();
                        keyToSave.setId(nextInsertionData.getNextId());
                        keyToSave.setCreatedAt(nextInsertionData.getNextCreatedAt());
                        keyToSave.setUpdatedAt(nextInsertionData.getNextUpdatedAt());
                    });

            return keyToSave;
        }));
    }

    @When("this user request key creation with Mpa: {string}")
    public void thisUserRequestsTransactions(String headerMpa) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(KEYS_ENDPOINT)
                .header("Mpa", headerMpa)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.testData.getRequestJson());

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        this.testData.setResultActions(resultActions);
    }

    @When("user {string} request key {string}")
    public void userRequestKey(String mpa, String keyId) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(KEYS_ENDPOINT + keyId)
                .header("Mpa", mpa)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        this.testData.setResultActions(resultActions);
    }

    @When("user {string} request key listing")
    public void userRequestKeyListing(String mpa) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(KEYS_ENDPOINT)
                .header("Mpa", mpa)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        this.testData.setResultActions(resultActions);
    }

    @When("user {string} request to inactivate all keys")
    public void userRequestToInactivateAllKeys(String mpa) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete(KEYS_ENDPOINT)
                .header("Mpa", mpa)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        this.testData.setResultActions(resultActions);
    }

    @When("user {string} request to inactivate key: {uuid}")
    public void userRequestToInactivateKeyCFBCcAdEe(String mpa, UUID keyId) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete(KEYS_ENDPOINT + "/" + keyId)
                .header("Mpa", mpa)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        this.testData.setResultActions(resultActions);
    }

    @Then("the service will reply this key creation response: {string}")
    public void theServiceWillReplyThisKeyCreationResponse(String responsePath) throws Exception {
        Resource expectedResponseFile = resourceLoader.getResource("classpath:" + responsePath);
        KeyCreationResponse expectedResponse = objectMapper.readValue(expectedResponseFile.getInputStream(), KeyCreationResponse.class);
        MvcResult mvcResult = this.testData.getResultActions()
                .andReturn();

        KeyCreationResponse keyCreationResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(), KeyCreationResponse.class);

        Assert.assertEquals(expectedResponse, keyCreationResponse);
    }

    @Then("the service will reply this key response: {string}")
    public void theServiceWillReplyThisKeyResponse(String responsePath) throws Exception {
        Resource expectedResponseFile = resourceLoader.getResource("classpath:" + responsePath);
        KeyResponse expectedResponse = objectMapper.readValue(expectedResponseFile.getInputStream(), KeyResponse.class);
        MvcResult mvcResult = this.testData.getResultActions()
                .andReturn();

        KeyResponse keyResponse = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(), KeyResponse.class);

        Assert.assertEquals(expectedResponse, keyResponse);
    }

    @Then("the service will reply this key list response: {string}")
    public void theServiceWillReplyThisKeyListResponse(String responsePath) throws Exception {
        Resource expectedResponseFile = resourceLoader.getResource("classpath:" + responsePath);
        TypeReference<Set<KeyResponse>> keyResponseSetType = new TypeReference<>() {
        };
        Set<KeyResponse> expectedResponse = objectMapper.readValue(expectedResponseFile.getInputStream(), keyResponseSetType);
        MvcResult mvcResult = this.testData.getResultActions()
                .andReturn();

        Set<KeyResponse> keyResponseList = objectMapper.readValue(mvcResult.getResponse()
                .getContentAsString(), keyResponseSetType);

        Assert.assertEquals(expectedResponse, keyResponseList);
    }

    @Then("accounts database will be called to find Mpa: {string}")
    public void accountsDatabaseWillBeCalledToFindMpa(String mpa) {
        then(this.accountRepository).should(atMostOnce())
                .findByMpa(eq(mpa));
    }

    @Then("accounts database will not be called")
    public void accountsDatabaseWillNotBeCalled() {
        then(this.accountRepository).should(never())
                .findByMpa(anyString());
    }

    @Then("owners database will be called to find document: {string}")
    public void ownersDatabaseWillBeCalledToFindDocument(String document) {
        then(this.ownerRepository).should(atMostOnce())
                .findByDocument(eq(document));
    }

    @Then("the database is called to find key with status: {keystatus} id: {uuid} and mpa: {string}")
    public void theDatabaseIsCalledToFindKeyWithStatusIdAndMpa(KeyStatus keyStatus, UUID keyId, String mpa) {
        then(this.keyRepository).should(only())
                .findByIdAndAccountMpaAndKeyStatus(keyId, mpa, keyStatus);
    }

    @Then("the database is called to list keys from account id: {uuid} with status: {keystatus}")
    public void theDatabaseIsCalledToListKeysAccountIdAndStatus(UUID accountId, KeyStatus keyStatus) {
        then(this.keyRepository).should(only())
                .findByAccountIdAndKeyStatus(accountId, keyStatus);
    }

    @Then("the database is not called to list keys")
    public void theDatabaseIsNotCalledToListKeys() {
        then(this.keyRepository).should(never())
                .findByAccountIdAndKeyStatus(any(UUID.class), any(KeyStatus.class));
    }


    @Then("owners database will not be called")
    public void ownersDatabaseWillNotBeCalled() {
        then(this.ownerRepository).should(never())
                .findByDocument(anyString());
    }

    @Then("the database is not called to find key by id and Mpa")
    public void theDatabaseIsNotCalledToFindKeyByIdAndMpa() {
        then(this.keyRepository).should(never())
                .findByIdAndAccountMpaAndKeyStatus(any(UUID.class), anyString(), any(KeyStatus.class));
    }

    @Then("the database is not called to list keys from account id {uuid} with status: {keystatus}")
    public void theDatabaseIsNotCalledToListKeysFromUser(UUID accountId, KeyStatus keyStatus) {
        then(this.keyRepository).should(never())
                .findByAccountIdAndKeyStatus(accountId, keyStatus);
    }

    @Then("key repository will be called to save a key as:")
    public void keyRepositoryWillBeCalledToSaveAKeyAs(Key expectedKey) {
        ArgumentCaptor<Key> keyArgumentCaptor = ArgumentCaptor.forClass(Key.class);

        then(this.keyRepository).should(only())
                .save(keyArgumentCaptor.capture());
        Key savedKey = keyArgumentCaptor.getValue();

        Assert.assertEquals(expectedKey, savedKey);
    }*/

}
