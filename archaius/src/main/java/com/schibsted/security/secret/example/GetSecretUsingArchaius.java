package com.schibsted.security.secret.example;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.schibsted.security.strongbox.archaius.InMemoryPlaintextSecret;
import com.schibsted.security.strongbox.archaius.JustInTimeDecryptedSecret;
import com.schibsted.security.strongbox.archaius.StrongboxConfigurationSource;
import com.schibsted.security.strongbox.sdk.SecretsGroup;
import com.schibsted.security.strongbox.sdk.SecretsGroupManager;
import com.schibsted.security.strongbox.sdk.impl.DefaultSecretsGroupManager;
import com.schibsted.security.strongbox.sdk.types.Region;
import com.schibsted.security.strongbox.sdk.types.SecretIdentifier;
import com.schibsted.security.strongbox.sdk.types.SecretsGroupIdentifier;

public class GetSecretUsingArchaius {

    public static void main(String[] args) throws InterruptedException {
        SecretsGroupManager sm = new DefaultSecretsGroupManager();
        SecretsGroup group = sm.get(new SecretsGroupIdentifier(Region.EU_WEST_1, "team.project"));
        configure(group);

        SecretIdentifier secretIdentifier = new SecretIdentifier("MySecret");

        InMemoryPlaintextSecret inMemorySecret = new InMemoryPlaintextSecret(group, secretIdentifier);
        JustInTimeDecryptedSecret jitSecret = new JustInTimeDecryptedSecret(group, secretIdentifier);

        for (int i=0; i<15; i++) {
            System.out.println("in memory decrypted: " +  inMemorySecret.get());
            System.out.println("just in time decrypted: " + jitSecret.get());
            Thread.sleep(1000);
        }
    }

    private static void configure(SecretsGroup group) {
        StrongboxConfigurationSource source = new StrongboxConfigurationSource(group);
        FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler(0, 10000, false);
        DynamicConfiguration dynamicConfig = new DynamicConfiguration(source, scheduler);

        ConfigurationManager.loadPropertiesFromConfiguration(dynamicConfig);
    }
}
