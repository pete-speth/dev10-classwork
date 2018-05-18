
loadMovies();

function loadMovies(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/movies",
        success: function(movieArray){
            $.each(movieArray, function(index, movie){
                var newHtml = "<tr>"
                newHtml += "<td>" + movie.title + "</td>";
                newHtml += "<td>" + movie.releaseDate + "</td>";
                newHtml += "<td>" + movie.directorName + "</td>";
                newHtml += "<td>" + movie.ratingMPAA + "</td>";
                newHtml += "</tr>";

                $("#tableContent").append(newHtml);
            });
        },
        error: function(){
            console.log("Failure");
        }
    })
}