package faanka.alc.Blocks;

import arc.util.Log;
import mindustry.gen.Building;
import mindustry.world.blocks.power.PowerBlock;
import mindustry.Vars;
import mindustry.graphics.Drawf;

public class SolarNode extends PowerBlock {
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

        @Override
        public void drawSelect()
        {
            if(connectedTowerBuild != null)
            {
                Drawf.dashLine(team.color, tileX() * Vars.tilesize, tileY() * Vars.tilesize, (connectedTowerBuild.tileX() + ((SolarTower)connectedTowerBuild.block).size * 0.25f) * Vars.tilesize, (connectedTowerBuild.tileY() + ((SolarTower)connectedTowerBuild.block).size * 0.25f) * Vars.tilesize);
            }
        }
    }
}
