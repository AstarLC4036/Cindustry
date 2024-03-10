/** 
    js anuke只会加载main.js(当 scripts/ 只有一个js文件是会当成main.js加载)
    所以这里需要用require(<文件相对路径>(相对scripts/))来加载指定js文件
*/

require("content/MPlanets");
require("核心/便携核心");
require("核心/强化前哨核心");
require("物品");
require("液体");
require("content/planets/原版隐藏星球");
