package com.mikazukichandamege.reinforcedae.common.item.part;

import appeng.items.parts.PartItem;

public class RnfPatternProviderPartItem extends PartItem<RnfPatternProviderPart> {
    public RnfPatternProviderPartItem(Properties properties) {
        super(properties, RnfPatternProviderPart.class, RnfPatternProviderPart::new);
    }
}
