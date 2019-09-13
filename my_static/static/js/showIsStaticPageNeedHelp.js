function showIsStaticPageNeedHelp() {// 静态页面提示（回复页面，先隐藏回复页面，在弹出提示）
	$('#exampleModal').modal('hide');
	addShowIsStaticPageNeedHelpElement("../img/pay.png");
	return false;
}

$(function(){
	//关闭提示窗
	$("#showIsStaticPageNeedHelp").on("click",".close",function() {
		delShowIsStaticPageNeedHelpElement();
	})
	
	
	
})

//直接弹出静态提示
function easyTOshowIsStaticPageNeedHelp(imgPath) {
	addShowIsStaticPageNeedHelpElement(imgPath);
}
	
function addShowIsStaticPageNeedHelpElement(imgPath){
	var tipsElement = `<div class="qr_code" style="display: block;">
		<div class="center">
			<span class="close">+</span>
			<h5>因（阿里）云服务器<span style="color: red;">免费期</span>已到，动态交互页面暂<span style="color: red;">停止使用</span>，域名暂解析到<span style="color: red;">静态</span>GitHub上。<br><br>扫描资助我 ↓↓↓<br><br>
				<span style="color: red;">感谢您的打赏</span> &nbsp;&nbsp;  <div style="display: inline-block;height: 18px;margin: auto;text-align: center"><input type="radio" checked="">支付宝</div>
			</h5>
			<div style="text-align: center"><img src="`+imgPath+`"></div>
			</div>
		</div>`;
	$("#showIsStaticPageNeedHelp").append(tipsElement);
}
function delShowIsStaticPageNeedHelpElement(){
	$("#showIsStaticPageNeedHelp").empty();
}
