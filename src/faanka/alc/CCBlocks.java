package faanka.alc;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

public class CCBlocks {
    public static Block mirrorNode;

    public static void load()
    {
        mirrorNode = new Block("mirror-node"){{
            requirements(Category.power, ItemStack.with(Items.copper, 1));
            size = 2;
        }};
    }
}
