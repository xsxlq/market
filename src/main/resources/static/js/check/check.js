//图片大小验证
function checkImg(file) {
    var result = {};
    var fileSize = 0;
    var fileMaxSize = 1024;//1M
    var filePath = file.value;
    if (filePath) {
        fileSize = file.files[0].size;
        var size = fileSize / 1024;
        if (size > fileMaxSize) {
            result.error = '图片大小不能高于1M！';
            file.value = "";
            return result;
        } else if (size <= 0) {
            result.error = '图片大小不能为0M！';
            file.value = "";
            return result;
        }
    } else {
        return "ok";
    }
}