/**
 * Created by Maibenben on 2017/5/21.
 */
$(function () {
    //点击收藏/取消收藏事件
    $(".top_content").on("click",".love",function(){
        var goodsId = $(this).parent().children(".willLoveGoodsId").val();
        var isLove = $(this).parent().children(".Ilove").val();
        var thisStatus = $(this).parent().children(".Ilove");
        var thisValueStr = $(this);
        console.log(goodsId+isLove);
        $(".container").css("display","block");
        loveIt(thisStatus,thisValueStr,goodsId,isLove);
    })

    //收藏方法（商品id）
    function loveIt(thisValue,thisStr,goodsId,isLove){
        if($(".isLoginCode").val() == "1"){
            //判断已收藏
            if(isLove == "true"){
                console.log("已经收藏。")
                //已经收藏，执行取消收藏操作
                $.post("/disLike",{userId:$(".head-userId").text(),goodsId:goodsId},function(returnMap){
                    console.log(returnMap);
                    if(returnMap.respCode == "1"){
                        alert("已取消收藏")
                        //修改状态
                        thisValue.val("false");
                        thisStr.val("收藏");
                    }else{
                        alert(returnMap.respDesc)
                    }
                    $(".container").css("display","none");
                })
            }else{
                //已经登录，请求ajax添加收藏
                $.post("/love",{userId:$(".head-userId").text(),goodsId:goodsId},function (returnMap) {
                    console.log(returnMap);
                    if(returnMap.respCode == "1"){
                        alert("已收藏")
                        //修改节点收藏属性
                        thisValue.val("true");
                        thisStr.val("已收藏");

                    }else{
                        alert(returnMap.respDesc)
                    }
                    $(".container").css("display","none");
                })
            }
        }else{
            //未登录
            if(confirm("请先登录？") == true){
                location.href = '/page/mall_page.html';
            }else{
                return false;
            }
        }
    }
});