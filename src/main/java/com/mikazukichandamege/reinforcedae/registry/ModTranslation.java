package com.mikazukichandamege.reinforcedae.registry;

import appeng.core.localization.LocalizationEnum;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;

public enum ModTranslation implements LocalizationEnum {
    Mod_Name("Reinforced AE", Type.GUI),
    Threads_4x("Provides 4 co-processing threads per block.", Type.TOOLTIP),
    Threads_16x("Provides 16 co-processing threads per block.", Type.TOOLTIP),
    Threads_32x("Provides 32 co-processing threads per block.", Type.TOOLTIP),
    Threads_64x("Provides 64 co-processing threads per block.", Type.TOOLTIP),
    Threads_128x("Provides 128 co-processing threads per block.", Type.TOOLTIP),
    Threads_256x("Provides 256 co-processing threads per block.", Type.TOOLTIP),
    Threads_1024x("Provides 1024 co-processing threads per block.", Type.TOOLTIP),
    Threads_2048x("Provides 2048 co-processing threads per block.", Type.TOOLTIP),
    Threads_8192x("Provides 8192 co-processing threads per block.", Type.TOOLTIP),
    ;

    private final String englishText;
    private final Type type;
    private final ModTranslation text;

    ModTranslation(String englishText, Type type) {
        this(englishText, type, null);
    }

    ModTranslation(String englishText, Type type, ModTranslation text) {
        this.englishText = englishText;
        this.type = type;
        this.text = text;
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }

    @Override
    public String getTranslationKey() {
        return switch (type) {
            case CONFIG_OPTION -> type.root.formatted(ReinforcedAE.MOD_ID) + "." + name();
            case CONFIG_TOOLTIP -> type.root.formatted(ReinforcedAE.MOD_ID) + "." + text.name() + ".@Tooltip";
            default -> String.format("%s.%s.%s", type.root, ReinforcedAE.MOD_ID, name());
        };
    }

    private enum Type {
        GUI("gui"),
        TOOLTIP("gui.tooltips"),
        CONFIG_OPTION("text.autoconfig.%s.option"),
        CONFIG_TOOLTIP("text.autconfig.%s.option");

        private final String root;

        Type(String root) {
            this.root = root;
        }
    }
}
