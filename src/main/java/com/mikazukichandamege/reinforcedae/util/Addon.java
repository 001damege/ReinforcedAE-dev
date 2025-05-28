package com.mikazukichandamege.reinforcedae.util;

public enum Addon {
    Mekanism("mekanism"),
    Botania("botania"),
    Appflux("appflux"),
    Ars("ars_nouveau"),
    ExtendedAE("expatternprovider"),
    AdvancedAE("advanced_ae");

    private final String modId;

    Addon(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
    }
}
