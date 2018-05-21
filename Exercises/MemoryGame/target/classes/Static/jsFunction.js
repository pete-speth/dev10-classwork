$(document).ready(function () {

    var choice = [];
    var lastButton;
    var compScore = 0;
    var playerScore = 0;
    var ai = [];
    loadCards();
    displayScore();
    $(".card-button p").hide();


    var vue = new Vue({
        el: "#game-board",
        data: {
            cards: []
        },
        methods: {

            // action for clicks on item buttons
            selectCard: function (event) {

                if (event) {

                    getTurn();


                }
            }
        }

    });


    //-----------------------------------------
    function loadCards() {
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/api/cards",
            success: function (cards) {
                vue.cards = cards;
            },
            error: function () {
                console.log("AJAX failed.")
            }
        });
    }



function computerPlayer() {

    var buttonComp;
    var lastComp;



    var allButtons = document.getElementsByClassName("card-button");
    var firstChoice = Math.floor(Math.random() * 36);
    
    while(allButtons[firstChoice].disabled == true) {
        firstChoice = Math.floor(Math.random() * 36);
    }



    var secondChoice = Math.floor(Math.random() * 36);
    
    while(allButtons[secondChoice].disabled == true || secondChoice == firstChoice) {
        secondChoice = Math.floor(Math.random() * 36);
    }

    buttonComp = allButtons[firstChoice];
    lastComp = allButtons[secondChoice];



    

    setTimeout(() => {
        buttonComp.children[0].style.display = "block";

        setTimeout(() => {
            lastComp.children[0].style.display = "block";

            setTimeout(() => {

            if(buttonComp.children[0].innerHTML == lastComp.children[0].innerHTML) {

                buttonComp.disabled = true;
                lastComp.disabled = true;
                compScore++;
                displayScore();
            }
            else{
        
                lastComp.children[0].style.display = "none";
                buttonComp.children[0].style.display = "none";
            }

        }, 3000);
            
        }, 3000);

    }, 3000);

    // ai.push({location: firstChoice, val: buttonComp.children[0].innerHTML});
    // ai.push({location: firstChoice, val: lastComp.children[0].innerHTML});
    

}


function getTurn() {


    var button;

    if (event.target.localName === "button") {
        button = event.target;
    } else {
        button = event.target.parentElement;
    }

    button.children[0].style.display = "block";


    if (choice === undefined || choice.length < 1) {

        choice[0] = button.children[0].innerText;
        button.disabled = true;
        lastButton = button;

    } else {

        choice[1] = button.children[0].innerText;
        button.disabled = true;

        if (choice[0] === choice[1]) {

            playerScore++;
            displayScore();

        } else {

            setTimeout(resetCards, 3000);
            

            function resetCards() {
                lastButton.disabled = false;
                button.disabled = false;
                lastButton.children[0].style.display = "none";
                button.children[0].style.display = "none";
            }
        }

        // var allButtons = document.getElementsByClassName("card-button");
        // allButtons.forEach(function(index, current){
        //     if (current === button) {
        //         ai.push({ location: index, val: button.children[0].innerText});
        //     }

        //     if (current === lastButton) {
        //         ai.push({ location: index, val: button.children[0].innerText});
        //     }



       // });



        choice = [];
        computerPlayer();

    }
    

}

function displayScore() {


    var pScore = document.getElementById("player-score");
    var cScore = document.getElementById("comp-score");

    pScore.innerHTML = playerScore;
    cScore.innerHTML = compScore;

    checkScore();
}

function checkScore() {
    var winner;

    if (playerScore + compScore === 17) {

        if(playerScore > compScore ) {
            winner = 'You win!'

            
        } else {
            winner = 'You lose!'
        }
        alert('Game Over! ' + winner)
    }
}

});