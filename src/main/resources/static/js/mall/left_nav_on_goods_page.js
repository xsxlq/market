$(function () {
    var maxPage = 1;
    //填充第一页,（更新最大页数）
    selectByCounts(1);
    //刷新maxPage的值
    function updateMaxPage(data){
        maxPage = data;
    }
    //初始化刷新页码
    function initPageNum(maxPage){
        console.log("maxPage:"+maxPage);
        //清空节点
        $(".pagination_div ul").empty();
        $(".lastPage").text(maxPage);
        for(var i = 0;i<maxPage;i++){
            var thisPageNum = i+1;
            if(thisPageNum > 5)//最大显示5页
                break;
            if(thisPageNum == 1){
                var pageStr = `<li class="current_page"><a>${thisPageNum}</a></li>`;
            }else{
                var pageStr = `<li><a>${thisPageNum}</a></li>`;
            }
            $(".pagination_div ul").append(pageStr);
        }
    }
    var type_list = getTypeList();

    // 导航滚动
    $(window).scroll(function () {
        var temp = $(this).scrollTop();
        if (temp > 100) {
            $('.my_type_div').css({"margin-top": "8%"});
            $('.particular_type_div').css({"margin-top": "8%"});
        } else {
            $('.my_type_div').css({"margin-top": "15%"});
            $('.particular_type_div').css({"margin-top": "15%"});
        }
    });

    $('.my_type_div ul li').hover(function () {
        var temp_class = $(this).attr("class");
        if (temp_class == 'type_1') {
            addList(0);
        } else if (temp_class == 'type_2') {
            addList(1);
        } else if (temp_class == 'type_3') {
            addList(2);
        } else if (temp_class == 'type_4') {
            addList(3);
        } else if (temp_class == 'type_5') {
            addList(4);
        } else if (temp_class == 'type_6') {
            addList(5);
        }
        function addList(id) {
            var which = type_list[id];
            var my_string = "";
            for (j = 0; j < which.length; j++) {
                var type_i = which[j];
                var arr = type_i.content;
                var a_list = "";
                for (i = 0; i < arr.length; i++) {
                    a_list += "<a id = '" + arr[i].id + "' class='shop_sort'>" + arr[i].name + "</a>";
                }
                my_string += "<div class='one_part'><div class='type_title_div'>" +
                    "<span class='type_border_span'>1</span><h3>" + type_i.name + "</h3></div><div " +
                    "class='type_goods_list'>" + a_list + "</div></div>";
            }
            $('.particular_type_div').html(my_string);
            //  点击事件
            $('.type_goods_list a.shop_sort').click(function () {
                var wsk = $(this).attr('id');
                var wskStr = $(this).text();
                var $all_product = $('.all_product');
                $(".selectGoodsType").text(wskStr);
                $(".selectGoodsType").attr("name",wsk);
                //点击后清空节点
                $('.particular_type_div').html("");
                // $.ajax({
                //     url: 'selectBySort',
                //     type: 'post',
                //     dataType: 'JSON',
                //     data: {sort: wsk},
                //     success: function (data) {
                //         $all_product.html('');
                //         if (data.length === 0) {
                //             $all_product.append("<div class='product_content_div'>" +
                //                 "<figure class='detail_product'>" +
                //                 "<input type='hidden' value= ''/>" +
                //                 "<img src='' title='暂时没有该分类的商品' />" +
                //                 "<span class='detail_product_name'></span><br/>" +
                //                 "<span class='detail_product_cost'></span><br/>" +
                //                 "<span class='detail_buy product_1'>加入购物车</span>" +
                //                 "</figure>" +
                //                 "</div>");
                //         }
                //         for (var i = 0; i < data.length; i++) {
                //             $all_product.append("<div class='product_content_div'>" +
                //                 "<div class='detail_product'>" +
                //                 "<input type='hidden' value=" + data[i].id + " '/>" +
                //                 "<div class='product_img_div'><img class='show_img' src='" + data[i].image + "' title=" + data.name + "'/></div>" +
                //                 "<p class='show_tip'>"+data[i].remark+"</p>"+
                //                 "<span class='detail_product_name' value='"+data[i].id+"'>" + data[i].name + "</span><br/>" +
                //                 "<span class='detail_product_cost'>$" + data[i].price + "</span><br/>" +
                //                 "<span class='detail_buy product_1' value='"+data[i].id+"'>加入购物车</span>" +
                //                 "</div>" +
                //                 "</div>");
                //         }
                //         //进入查看商品的详情,通过id
                //         $('.detail_product_name').click(function () {
                //             var id = $(this).attr('value');
                //             window.location.href='/selectById?id='+id;
                //         });
                //         insertShopCar();
                //     }
                // });
                // alert(wsk);

            })
        }

        $('.particular_type_div').show(0);
    });

    $('header').click(function () {
        hideParticular();
    });
    //new
    // $('body').on("click","li",function (){
    //     bindClick();
    // })
    //  直接点击页数
    function bindClick() {
        $('.pagination_div ul').on("click","li",function () {
                //当前选中页数
                var cur = $(".pagination_div ul .current_page").children("a").text();
                var thisPage = $(this).text();
                console.log("点击页数："+thisPage);
                console.log("最大页数："+maxPage);
                if(thisPage > maxPage){
                    alert("无此页，总页数："+maxPage);
                    return false;
                }else{
                    // 移除选中样式
                    $(".pagination_div ul li").removeClass("current_page");
                    // 给点击的页数加上选中样式
                    $(this).addClass("current_page");
                    //  点击的页数
                    var which_click = $(this).children("a").text();
                    //  在if里面处理
                    if (cur !== which_click) {
                        console.log("选中："+which_click)

                        selectByCounts(which_click);
                }

            }
        });
    }

    //  上一页
    $('.pagination_lt').click(function () {
        var current = $('.pagination_div ul li.current_page');
        var temp = current.children("a").html();
        //  已经达到最左边，再点无反应
        if (temp == 1) {
            return false;
        }
        updateCurrent(current, 1, temp);
        //      这个就是当前的页数
        var current_page = $('.pagination_div ul li.current_page').children("a").html();
        selectByCounts(current_page);
    });
    //下一页
    $('.pagination_gt').click(function () {
        var current = $('.pagination_div ul li.current_page');
        var temp = current.children("a").html();
        // 到达最右边
        if(temp >= maxPage){
            return false;
        }else{
            updateCurrent(current, 2, temp);
            var current_page = $('.pagination_div ul li.current_page').children("a").html();
            //      通过这个current_page 来获取数据
            selectByCounts(current_page);
        }


    });

    // temp 当前的值（1,2,3,4...）
    function updateCurrent(current, to, temp) {
        //    1左，2右
        var num = current.nextAll().length;
        if (to == 1) {
            if (num == 4) {
                current.siblings(":last").remove();
                current.before("<li><a>" + (temp - 1) + "</a></li>");
            }
            if (num == 3) {
                if (!(temp - 2 < 1)) {
                    current.siblings(":last").remove();
                    current.siblings(":first").before("<li><a>" + (temp - 2) + "</a></li>");
                }
            }
            current.removeClass("current_page");
            current.prev().addClass("current_page");
        } else {
            if (num == 0) {
                current.siblings(":first").remove();
                current.after("<li><a>" + (parseInt(temp) + 1) + "</a></li>");
            }
            if (num == 1) {
                if (!(temp + 2 > maxPage)) {
                    current.siblings(":first").remove();
                    current.siblings(":last").after("<li><a>" + (parseInt(temp) + 2) + "</a></li>");
                }
            }
            current.removeClass("current_page");
            current.next().addClass("current_page");
        }
        bindClick();
    }

    /**
     * 选中页数后处理
     * @param currentCounts
     */

    function selectByCounts(currentCounts) {
        var $all_product = $('.product_content_div');
        //获取搜索关键词
        var nav_search_input = $(".nav_search_input_copy").val();
        console.log("nav_search_input:"+nav_search_input)
        //获取分类
        var nav_search_type_id = $(".selectGoodsType").attr("name");
        //获取价格
        var goodsPrice = Number($(".selectGoodsPrice").val());
        //获取成色
        var goodsQuality = $(".select_quality").select().val();
        if(nav_search_input == '' || nav_search_input == null){
            nav_search_input = "all";
        }
        $.ajax({
            url: '/pageShopList',
            //同步请求
            async: false,
            type: 'post',
            dataType: 'JSON',
            data: {
                pageNum: currentCounts,
                pageSize:30,
                goodsName:nav_search_input,
                userId:$(".head-userId").text(),
                goodsTypeId:nav_search_type_id,
                goodsPrice:goodsPrice,
                goodsQuality:goodsQuality
            },
            success: function (shopData) {
                console.log("ajax:maxPage:"+maxPage);
                console.log("ajax:lastPage:"+maxPage);
                console.log(shopData);
                updateMaxPage(shopData.lastPage);
                $all_product.html('');
                if (shopData.pageNum > shopData.lastPage) {
                    $all_product.append("<div class='product_content_div'>" +
                        "<div class='detail_product'>" +
                        "<input type='hidden' value= ''/>" +
                        "<div class='product_img_div'><img src='' title='暂时没有该分类的商品' /></div>" +
                        "<span class='detail_product_name'></span><br/>" +
                        "<span class='detail_product_cost'></span><br/>" +
                        "<span class='detail_buy product_1'>加入购物车</span>" +
                        "</div>" +
                        "</div>");
                }else{
                    for(var i = 0;i<shopData.list.length;i++){
                        var Ilove = shopData.list[i].isLove;
                        var ILoveStr = "收藏";
                        if(Ilove == true){
                            ILoveStr = "已收藏";
                        }
                        var str = `<div class="detail_product" onclick="goodsInfo(${shopData.list[i].goodsId})">
                                <input type="hidden" name="id"/>
                                <input type="hidden" class="willLoveGoodsId" value="${shopData.list[i].goodsId}">
                                <div class="product_img_div">
                                    <img src=${shopData.list[i].goodsImg} class="show_img"/>
                                </div>
                                <p class="show_tip">${shopData.list[i].goodsDesc}</p>
                                <span class="detail_product_cost">¥${shopData.list[i].goodsPrice}.00元</span><br/>
                                 <a class="product_name">【${shopData.list[i].shopQuality.name}】${shopData.list[i].goodsName}</a><br/>
                                <span class="product_area">
                                    <span class="iconfont icon-weizhi"></span>
                                    河南科技学院
                                </span><br/>
                                <span class="product_area">
                                <span class="iconfont icon-shoucang"></span>
                                 ${shopData.list[i].allLoveNum}
                                </span><br/>
                                <input type="hidden" class="ILove" value=${shopData.list[i].isLove}>
                                <!--<span class="detail_buy product_1 love">${ILoveStr}</span>-->
                            </div>`;
                        $(".product_content_div").append(str);

                    }
                }
                //进入查看商品的详情,通过id
                // $('.detail_product_name').click(function () {
                //     var id = $(this).attr('value');
                //     window.location.href='/selectById?id='+id;
                // });
                // insertShopCar();
            }
        });
    }
    //进入查看商品的详情,通过id
    $('.detail_product_name').click(function () {
        var id = $(this).attr('value');
        window.location.href='/selectById?id='+id;
    });

    initPageNum(maxPage);
    bindClick();
    //搜索按钮
    $('.search_icon').click(function () {
        $(".nav_search_input_copy").val($(".nav_search_input").val());
        selectByCounts(1);
        initPageNum(maxPage);
    });


    //筛选条件点击事件
    $(".select_type_button").click(function(){
        select_type_button();
    })
    //重置筛选条件
    $(".re_select_type_button").click(function(){
        //搜索关键词
        $(".nav_search_input").val("");
        $(".nav_search_input_copy").val("");
        //分类
        $(".selectGoodsType").attr("name","-100");
        $(".selectGoodsType").text("全部");
        //价格
        $(".selectGoodsPrice").val("");
        //成色
        $(".select_quality option:first").prop("selected", 'selected');
        $(".nav_search_input_copy").val($(".nav_search_input").val());
        selectByCounts(1);
        initPageNum(maxPage);
    })
    function select_type_button(){
        $(".nav_search_input_copy").val($(".nav_search_input").val());
        selectByCounts(1);
        initPageNum(maxPage);

    }
});
function hideParticular() {
    if ($('.particular_type_div').is(":visible")) {
        $('.particular_type_div').hide(0);
    }
}
function goodsInfo(goodsId){
    console.log("dianji ")
    if($(".isLoginCode").val() == "1"){
        //已经登录，请求ajax添加收藏
        window.location = '/goodsInfo?goodsId='+goodsId;
    }else{
        //未登录
        if(confirm("请先登录？") == true){
            location.href = '/page/mall_page.html';
        }
    }
}
