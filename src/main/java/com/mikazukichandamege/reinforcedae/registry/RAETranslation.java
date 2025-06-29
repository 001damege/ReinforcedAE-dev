package com.mikazukichandamege.reinforcedae.registry;

import appeng.core.localization.LocalizationEnum;
import com.mikazukichandamege.reinforcedae.ReinforcedAE;

public enum RAETranslation implements LocalizationEnum {
    Mod_Name("Reinforced AE", Type.GUI),
    Threads_4x("Provides 4 co-processing threads per block.", Type.TOOLTIP),
    Threads_16x("Provides 16 co-processing threads per block.", Type.TOOLTIP),
    Threads_64x("Provides 64 co-processing threads per block.", Type.TOOLTIP),
    Threads_256x("Provides 256 co-processing threads per block.", Type.TOOLTIP),
    Threads_1024x("Provides 1024 co-processing threads per block.", Type.TOOLTIP),
    Threads_4096x("Provides 4096 co-processing threads per block.", Type.TOOLTIP),
    Threads_16384x("Provides 16384 co-processing threads per block.", Type.TOOLTIP),
    Threads_65536x("Provides 65536 co-processing threads per block.", Type.TOOLTIP);

    private final String englishText;
    private final Type type;
    private final RAETranslation text;

    RAETranslation(String englishText, Type type) {
        this(englishText, type, null);
    }

    RAETranslation(String englishText, Type type, RAETranslation text) {
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
