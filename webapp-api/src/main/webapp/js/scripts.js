jQuery(function($){

'use strict';

/* === Preloader === */

  (function () {
      $(window).load(function() {
          $('#pre-status').fadeOut();
          $('#st-preloader').delay(350).fadeOut('slow');
      });
  }());


/* === Back To Top === */

  (function () {
      $('.scroll-up a').click(function(){
            $('html, body').animate({scrollTop : 0},800);
            return false;
        });
  }());


/* === Menu === */

  (function () {
    $('.top-menu-are').slicknav({
      prependTo:'.menu-mobile',
      label:''
    })
  }());


/* === Featured slider === */
  (function () {
    $("#featured-slider").owlCarousel({
        items : 6,
        itemsDesktop : [1199,3],
        itemsDesktopSmall : [979,3],
        navigation : false,
        pagination : false,
    });
  }());


/* === instafeed === */
  (function () {
    $('#instafeed').owlCarousel({
        items : 6,
        itemsDesktop : [1199,4],
        itemsDesktopSmall : [979,3],
        navigation : true,
        navigationText : ["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
        pagination : false
      });
  }());


/* === Fitvids js === */
  (function () {
      $(".wpb_wrapper").fitVids();
      $(".entry-content").fitVids();
      $(".entry-video").fitVids();
      $(".entry-audio").fitVids();
  }()); 


/* === Masonery === */
  (function () {
    var masonry = $('.masonery_area');
    $(window).load(function(){
        masonry.masonry();//Masonry
    });
  }()); 


/* === Magnific Popup js === */
  (function () {

    $('.gallery-item').magnificPopup({ 
      delegate: 'a',
      type: 'image',
      // other options
      closeOnContentClick: false,
      closeBtnInside: false,
      mainClass: 'mfp-with-zoom mfp-img-mobile',

      gallery: {
        enabled: true
      },
      zoom: {
        enabled: true,
        duration: 300, // don't foget to change the duration also in CSS
        opener: function(element) {
          return element.find('img');
        }
      }

    });

  }()); 
  
});