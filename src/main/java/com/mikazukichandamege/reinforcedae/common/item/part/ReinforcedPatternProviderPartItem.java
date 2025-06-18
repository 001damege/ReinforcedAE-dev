package com.mikazukichandamege.reinforcedae.common.item.part;

import appeng.items.parts.PartItem;

public class ReinforcedPatternProviderPartItem extends PartItem<ReinforcedPatternProviderPart> {
    public ReinforcedPatternProviderPartItem(Properties properties) {
        super(properties, ReinforcedPatternProviderPart.class, ReinforcedPatternProviderPart::new);
    }
}
