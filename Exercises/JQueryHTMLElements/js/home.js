$(document).ready(function () {

  $("H1").css('text-align','center');
  $("H2").css('text-align','center');

  $(".myBannerHeading").addClass('page-header');
  $(".page-header").removeClass('myBannerHeading');

  $("#yellowHeading").text("Yellow team");

  $(".orange").css('background-color','orange');
  $(".blue").css('background-color','blue');
  $(".red").css('background-color','red');
  $(".yellow").css('background-color','yellow');

  $("#yellowTeamList").html(
    "<li>Joseph Banks</li>"+
    "<li>Simon Jones</li>");

  $("#oops").hide();

  $("#footerPlaceholder").remove();

  $("#footer").html(
    "<p class='footerP'>Peter Spethmann</p>"+
    "<p class = 'footerP'>pspethmann@yahoo.com</p>");
  $(".footerP").css({'font-family':'Courier New, Courier', 'font-size':'24px'});
});
