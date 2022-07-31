package com.spring.social.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.social.api.social.AppleApi;
import com.spring.social.api.social.FacebookApi;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.yaml.snakeyaml.tokens.KeyToken;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.security.Key;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
class UserServiceTest {




    @Test
    public void test_input() throws Exception {
        String name = "japan";
        System.out.println(isAlphabet(name));
    }

    public boolean isAlphabet(String name) {
        for (int index = 0; index < name.length(); index++) {
            if (!((name.charAt(index) >= 65) && (name.charAt(index) <= 90)) && !((name.charAt(index) >= 97) && (name.charAt(index) <= 120))) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void test_jwt() throws Exception{
        String jwt = "eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLndoaXRlcGFlay5zZXJ2aWNlcyIsImV4cCI6MTU5ODgwMDEyOCwiaWF0IjoxNTk4Nzk5NTI4LCJzdWIiOiIwMDAxNDguZjA2ZDgyMmNlMGIyNDgzYWFhOTdkMjczYjA5NzgzMjUuMTcxNyIsIm5vbmNlIjoiMjBCMjBELTBTOC0xSzgiLCJjX2hhc2giOiJ1aFFiV0gzQUFWdEc1OUw4eEpTMldRIiwiZW1haWwiOiJpNzlmaWl0OWIzQHByaXZhdGVyZWxheS5hcHBsZWlkLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImlzX3ByaXZhdGVfZW1haWwiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNTk4Nzk5NTI4LCJub25jZV9zdXBwb3J0ZWQiOnRydWV9.GQBCUHza0yttOfpQ-J5OvyZoGe5Zny8pI06sKVDIJaQY3bdiphllg1_pHMtPUp7FLv3ccthcmqmZn7NWVoIPkc9-_8squ_fp9F68XM-UsERKVzBvVR92TwQuKOPFr4lRn-2FlBzN4NegicMS-IV8Ad3AKTIRMIhvAXG4UgNxgPAuCpHwCwEAJijljfUfnRYO-_ywgTcF26szluBz9w0Y1nn_IIVCUzAwYiEMdLo53NoyJmWYFWu8pxmXRpunbMHl5nvFpf9nK-OGtMJrmZ4DlpTc2Gv64Zs2bwHDEvOyQ1WiRUB6_FWRH5FV10JSsccMlm6iOByOLYd03RRH2uYtFw";

        String stringHeader =  new String(Decoders.BASE64.decode(jwt.substring(0, jwt.indexOf("."))), "UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        Map headers = mapper.readValue(stringHeader, Map.class);
        System.out.println(headers.get("kid") + " " + headers.get("alg"));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://appleid.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppleApi appleApi = retrofit.create(AppleApi.class);
        Call<Map> result = appleApi.getPublicKey();
        retrofit2.Response<Map> response = result.execute();

        Map keys = response.body();
        List<Map> list = (List<Map>) keys.get("keys");

        System.out.println(list);

    }


}