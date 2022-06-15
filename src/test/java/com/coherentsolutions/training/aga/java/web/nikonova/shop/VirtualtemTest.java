package com.coherentsolutions.training.aga.java.web.nikonova.shop;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.coherentsolutions.training.aqa.java.web.nikonova.shop.VirtualItem;

import java.math.BigDecimal;

public class VirtualtemTest {
    @Test
    public void testSizeOfVirtualItem() {
        BigDecimal expectedResult = new BigDecimal(25.5);
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(expectedResult.doubleValue());
        Assertions.assertEquals(virtualItem.getSizeOnDisk(), expectedResult.doubleValue(), "Size is mismatched");

    }
}
