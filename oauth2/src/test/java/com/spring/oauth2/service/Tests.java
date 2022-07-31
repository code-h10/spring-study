package com.spring.oauth2.service;


import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@ExtendWith(SpringExtension.class)
public class Tests {


    public int binarySearch(int arr[], int l, int r, int x) {

        if (r >= 1) {

            int mid = l + (r - 1) / 2;

            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] > x) {
                return binarySearch(arr, l, mid -1, x);
            }

            return binarySearch(arr, mid + 1, r, x);
        }

        return -1;

    }

    public PrivateKey getPrivateKey() throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("AuthKey_WVLS758Z7P.p8")), "utf-8");
        try {
            String privateKey = content
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("EC");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));

        } catch (Exception e) {
            throw e;
        }
    }


}
