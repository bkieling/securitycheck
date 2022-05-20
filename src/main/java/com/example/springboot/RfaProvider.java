package com.example.springboot;

public interface RfaProvider {

    String getRfaContentById(Long ID) throws RfaNotFoundException;

}
