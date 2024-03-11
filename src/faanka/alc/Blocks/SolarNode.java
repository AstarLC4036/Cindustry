package faanka.alc.Blocks;

import arc.util.Log;
import mindustry.gen.Building;
import mindustry.world.Block;

public class SolarNode extends Block {
    public SolarNode(String name)
    {
        super(name);
        buildType = SolarNodeBuild::new;
    }

    public class SolarNodeBuild extends Building {
        public SolarTower.SolarTowerBuild connectedTowerBuild = null;

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
        public void remove()
        {
            if(connectedTowerBuild != null)
            {
                connectedTowerBuild.connectedCount -= 1;
                connectedTowerBuild.needUpdate = true;
                connectedTowerBuild = null;
            }
        }
    }
}
