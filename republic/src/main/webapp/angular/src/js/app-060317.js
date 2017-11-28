$(document).ready(function () {

  //moadl
  var modalPosition = function () {
    var dHeight = $(document).height();
    $(".modal-bg").height(dHeight);
    var wPosition = $(window).scrollTop();
    $(".modal-container").css("top", wPosition + 30);
  }

  modalPosition();

  //reg modal
  $(".custom-event-reg").click(function () {
    $(".modal-bg.zg-reg-modal").fadeIn();
    modalPosition();
    return false;
  })

  $(".modal-bg a.modal-close").click(function () {
    $(".modal-bg").fadeOut();
    return false;
  })

  $(".zg-reg-form").validate({

    rules: {
      name: {
        required: true
      },
      email: {
        required: true,
        email: true
      },
      phone: {
        required: true,
        digits: true
      },
      captha: {
        required: true,
        min: 4,
        max: 4
      }
    },
    messages: {
      name: {
        required: "Поле «Имя» обязательное для заполнения"
      },
      email: {
        required: "Поле «E-mail» обязательное для заполнения",
        email: "Введите пожалуйста корректный e-mail"
      },
      phone: {
        required: "Поле «Телефон» обязательное для заполнения",
        digits: "Введите пожалуйста корректный «Телефон»"
      },
      captha: {
        required: "Решите задачу",
        min: "Не верное решение",
        max: "Не верное решение",
      }
    },
    errorClass: "form-input_error",
    validClass: "form-input_success"
  });


  //banner slider
  sliderMain = $('.banners-slider').bxSlider({
    mode: 'horizontal',
    auto: true,
    pause: 3000,
    controls: false
  });

  //column height fix
  var columnHeight = function () {

    var lSidebarHeight = $(".right-sidebar").height();
    var lMainContentHeight = $(".main-content").height();

    if (lMainContentHeight > lSidebarHeight) {

      $(".right-sidebar").height(lMainContentHeight);

    }

  }

  setTimeout(function () {

    columnHeight();

  }, 400);

  $(window).resize(function () {

    columnHeight();

  })

  columnHeight();


  //order click

  $(".order-btn").click(function () {

    var headerHeight = $(".header").outerHeight();
    var bannerHeight = $(".top-banner").outerHeight();

    $("html:not(:animated),body:not(:animated)").animate({scrollTop: headerHeight + bannerHeight}, 400);

    var thisParent = $(this).parents(".block");
    var orderBlock = $(".block.order-page");
    var sideBarBlock = $(".sidebar-block-container");
    var sideBarOrder = $(".order-type");

    thisParent.fadeOut(400);
    sideBarBlock.fadeOut(400);

    $(".right-sidebar").css("min-height", $(window).height());

    setTimeout(function () {

      orderBlock.fadeIn(500);
      sideBarOrder.fadeIn(500);
      stickyBlock.removeClass("fixed");
      columnHeight();

    }, 600);

    return false;

  });


  //sticky

  var stickyBlock = $(".block.sidebar-sticky");
  var stickyBlockCont = $(".sidebar-sticky-container");

  if ($(window).width() < $("body").width()) {

    stickyBlock.addClass("no-sticky");

  }

  $(window).scroll(function () {

    if ($(stickyBlock).length) {

      var winScrollTop = $(window).scrollTop();
      var stickyBlockPosition = stickyBlockCont.offset();
      var footerPosition = $(".footer").offset();

      if (winScrollTop >= stickyBlockPosition.top) {

        if (!stickyBlock.hasClass("fixed")) {

          stickyBlock.addClass("fixed").css("top", 0);

        }

        var stickyBlockHeight = $(".block.sidebar-sticky").outerHeight();

        if (winScrollTop + stickyBlockHeight >= footerPosition.top) {

          stickyBlock.css("top", 0 - (winScrollTop + stickyBlockHeight - footerPosition.top));

        } else {

          stickyBlock.css("top", 0);
        }

      } else {

        stickyBlock.removeClass("fixed");

      }

    }

  });


  //count input

  $(".quantity-control .quantity-btn").click(function () {

    var tecketField = $(this).parent().find(".value-field")
    var ticketValue = tecketField.val();

    if ($(this).hasClass("minus") && ticketValue >= 1) {

      tecketField.val(ticketValue - 1);

    } else if ($(this).hasClass("pluse")) {

      tecketField.val(++ticketValue);

    }

  })

  // order actions

  var aOrderTotalPrice = $(".action-order-ticket-total-price");
  var aOrderTicketsPrice = $(".action-order-ticket-price");
  var aOrderTicketQuantity = $(".action-order-ticket-quantity");
  var aOrderTicketName = $(".action-order-ticket-name");

  var aOrderPhone = $(".action-order-phone");
  var aOrderAddress = $(".action-order-address");
  var aOrderPhoneField = $(".action-order-phone-field");
  var aOrderAddressField = $(".action-order-address-field");

  var aOrderSubmit = $(".action-order-submit");


  var thousandSeparator = function (str) {
    var parts = (str + '').split('.'),
      main = parts[0],
      len = main.length,
      output = '',
      i = len - 1;

    while (i >= 0) {
      output = main.charAt(i) + output;
      if ((len - i) % 3 === 0 && i > 0) {
        output = ' ' + output;
      }
      --i;
    }

    if (parts.length > 1) {
      output += '.' + parts[1];
    }
    return output;
  };


  var aOrderTotalPricCalc = function () {

    aOrderTotalPrice.text("250"); // стоимость доставки
    aOrderTicketQuantity.text("0");

    $(".order-page .ticket-select .ticket-quantity").each(function () {

      var aOrderTotalPriceValue = aOrderTotalPrice.text().replace(/\s/g, '');

      var aOrderTotalQuantity = aOrderTicketQuantity.text().replace(/\s/g, '');

      var aOrderTicketValue = $(this).find(".value-field").val();
      var aOrderTicketItemPrice = $(this).find(".price").text().replace(/\s/g, '');

      var aOrderTotalPriceValueNew = parseInt(aOrderTotalPriceValue) + parseInt(aOrderTicketValue * parseInt(aOrderTicketItemPrice));

      aOrderTotalPrice.text(thousandSeparator(aOrderTotalPriceValueNew));
      aOrderTicketsPrice.text(thousandSeparator(aOrderTotalPriceValueNew));

      aOrderTicketQuantity.text(parseInt(aOrderTotalQuantity) + parseInt(aOrderTicketValue));

    })

    var aOrderTicketQuantityNew = aOrderTicketQuantity.text();
    var aOrderTicketQuantityNewLast = aOrderTicketQuantityNew[aOrderTicketQuantityNew.length - 1];


    if (parseInt(aOrderTicketQuantityNew) >= 11 && parseInt(aOrderTicketQuantityNew) <= 19) {

      aOrderTicketName.text("билетов");

    } else if (aOrderTicketQuantityNewLast >= 2 && aOrderTicketQuantityNewLast <= 4) {

      aOrderTicketName.text("билета");

    } else if (aOrderTicketQuantityNewLast == 1) {

      aOrderTicketName.text("билет");

    } else if (aOrderTicketQuantityNewLast == "0" || aOrderTicketQuantityNewLast >= 5) {

      aOrderTicketName.text("билетов");

    }


    if (parseInt(aOrderTicketQuantity.text()) > 0) {

      aOrderSubmit.removeClass("disabled").removeAttr('disabled');

    } else {

      aOrderSubmit.addClass("disabled").attr('disabled', 'disabled');

    }


  }

  aOrderTotalPricCalc();


  $(".order-page .quantity-control .quantity-btn").click(function () {

    aOrderTotalPricCalc();

  })


  aOrderPhoneField.focusout(function () {

    var thisValue = $(this).val();

    if (!thisValue == "") {

      aOrderPhone.text(thisValue);

    } else {

      aOrderPhone.text("(Укажите ваш номер телефона)");

    }

  })


  aOrderAddressField.focusout(function () {

    var thisValue = $(this).val();

    if (!thisValue == "") {

      aOrderAddress.text(thisValue);

    } else {

      aOrderAddress.text("(Укажите ваш адрес доставки)");

    }

  })


})
