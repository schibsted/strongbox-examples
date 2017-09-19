package com.schibsted.security.secret.example;

import com.schibsted.security.strongbox.sdk.SimpleSecretsGroup;
import com.schibsted.security.strongbox.sdk.impl.DefaultSimpleSecretsGroup;
import com.schibsted.security.strongbox.sdk.types.Region;
import com.schibsted.security.strongbox.sdk.types.SecretsGroupIdentifier;

import java.util.Optional;

public class GetSecretUsingSDK {
    public static void main(String[] args) {
        SimpleSecretsGroup secretsGroup = new DefaultSimpleSecretsGroup(new SecretsGroupIdentifier(Region.EU_WEST_1, "team.project"));

        Optional<String> secret = secretsGroup.getStringSecret("MySecret");

        System.out.println(secret.get());
    }
}
