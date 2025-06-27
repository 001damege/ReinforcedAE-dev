package com.mikazukichandamege.reinforcedae.common.util;

public enum Addon {
    AppMek("appmek"),
    AppBot("appbot"),
    Appflux("appflux"),
    Ars("ars_nouveau"),
    ExtendedAE("expatternprovider"),
    AdvancedAE("advanced_ae"),
    Avaritia("avaritia");

    private final String modId;

    Addon(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
    }
}
