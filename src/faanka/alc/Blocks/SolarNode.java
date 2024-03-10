package faanka.alc.Blocks;

import mindustry.gen.Building;
import mindustry.world.blocks.power.PowerBlock;

public class SolarNode extends PowerBlock {
    public SolarTower connectedTower = null;
    public SolarNode(String name)
    {
        super(name);
        buildType = SolarNodeBuild::new;
    }

    public class SolarNodeBuild extends Building {
        @Override
        public void created()
        {
            super.created();
        }

        @Override
        public void placed()
        {
            super.placed();
        }

        @Override
        public void onDestroyed()
        {
            if(connectedTower != null)
            {
                connectedTower.connectedCount -= 1;
                connectedTower = null;
            }
        }
    }
}
