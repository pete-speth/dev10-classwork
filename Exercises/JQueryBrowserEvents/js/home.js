$(document).ready(function () {

    // Show only the main to begin
    $("#minneapolisInfoDiv").hide();
    $("#akronInfoDiv").hide();
    $("#louisvilleInfoDiv").hide();

    // When a button is pressed, show that div and hide all others
    $("#akronButton").on("click", function(){
      $("#mainInfoDiv").hide();
      $("#minneapolisInfoDiv").hide();
      $("#louisvilleInfoDiv").hide();

      $("#akronInfoDiv").show();
      $("#akronWeather").hide();
    });

    $("#minneapolisButton").on("click", function(){
      $("#mainInfoDiv").hide();
      $("#akronInfoDiv").hide();
      $("#louisvilleInfoDiv").hide();

      $("#minneapolisInfoDiv").show();
      $("#minneapolisWeather").hide();
    });

    $("#louisvilleButton").on("click", function(){
      $("#mainInfoDiv").hide();
      $("#minneapolisInfoDiv").hide();
      $("#akronInfoDiv").hide();

      $("#louisvilleInfoDiv").show();
      $("#louisvilleWeather").hide();
    });

    $("#mainButton").on("click", function(){
      $("#akronInfoDiv").hide();
      $("#minneapolisInfoDiv").hide();
      $("#louisvilleInfoDiv").hide();

      $("#mainInfoDiv").show();
    });

    // Toggle weather when button is pressed
    $("#akronWeatherButton").on("click", function(){
      $("#akronWeather").toggle();
    });

    $("#minneapolisWeatherButton").on("click", function(){
      $("#minneapolisWeather").toggle();
    });

    $("#louisvilleWeatherButton").on("click", function(){
      $("#louisvilleWeather").toggle();
    });

    // Change color of non-header table rows on hover
    $("tr:not(:first-child)").hover(
      function(){
        $(this).css('background-color','WhiteSmoke');
      },
      function(){
        $(this).css('background-color','');
      }
  );

});
