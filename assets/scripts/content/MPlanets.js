// 负责加载所有星球相关模块 并导出

// 加载星球
const 芙星 = require("content/planets/芙星");
require("content/presets/芙星Presets");
exportObj("芙星", 芙星);

function exportObj(name, obj){
    module.exports[name] = obj;
}