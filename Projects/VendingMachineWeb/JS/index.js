
var vm = new Vue({
    el: ".content",

    data: {
        items: []
    },
    methods: {
        // action for clicks on item buttons
        selectItem: function (event) {
            if (event) {
                $("#messageDisplay").val("");
                $("#changeDisplay").val("");
                console.log(event);

                var button
                if (event.target.localName === "button") {
                    button = event.target
                } else {
                    button = event.target.parentElement
                }

                var id = button.getAttribute("data-id");
                var name = button.getAttribute("data-name");

                $("#itemInput").val(id + ". " + name);
                $("#itemInput").attr("data-id", id);
            }
        }
    }
});

// loads any arbitrary number of vending items
loadItems();
var money = 0;

// listens for clicks on the buttons that add money
trackMoney();

// listens for clicks on purchase button
$("#purchaseBtn").click(function () {
    makePurchase();
});

// listens for clicks on change return button
$("#changeBtn").click(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/change/" + $("#moneyIn").val(),
        success: function(change){
            var changeMsg = "Quarters: " + change.quarters + "\n"
                + "Dimes: " + change.dimes + "\n"
                + "Nickels: " + change.nickels + "\n"
                + "Pennies: " + change.pennies;

            $("#changeDisplay").val(changeMsg);
        },
        error: function(){
            console.log("AJAX failed.");
            $("#errorMessages")
          .append($("<li>")
            .attr({
              class: "list-group-item list-group-item-danger"
            })
            .text("Error calling web service. Please try agian later."));
        }
    });

money = 0;
$("#moneyIn").val(money.toFixed(2));

$("#messageDisplay").val("");
$("#itemInput").val("");
})





// ----------- HELPER FUNCTIONS --------------
function loadItems() {

    clearItems();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/items",
        success: function (items) {
            vm.items = items;
        },
        error: function () {
            console.log("AJAX failed.")
            $("#errorMessages")
          .append($("<li>")
            .attr({
              class: "list-group-item list-group-item-danger"
            })
            .text("Error calling web service. Please try agian later."));
        }
    })



}

function trackMoney() {

    $("moneyIn").val(0);

    $("#addDollarBtn").click(function () {
        money += 1;
        $("#moneyIn").val(money.toFixed(2));
    });

    $("#addQuarterBtn").click(function () {
        money += 0.25;
        $("#moneyIn").val(money.toFixed(2));
    });

    $("#addDimeBtn").click(function () {
        money += 0.1;
        $("#moneyIn").val(money.toFixed(2));
    });

    $("#addNickelBtn").click(function () {
        money += 0.05;
        $("#moneyIn").val(money.toFixed(2));
    });
}

function makePurchase() {

    var id = $("#itemInput").attr("data-id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/money/" + money + "/item/" + id,
        success: function (change) {
            $("#messageDisplay").val("THANK YOU!!!")

            var changeMsg = "Quarters: " + change.quarters + "\n"
                + "Dimes: " + change.dimes + "\n"
                + "Nickels: " + change.nickels + "\n"
                + "Pennies: " + change.pennies;

            $("#changeDisplay").val(changeMsg);

            loadItems();
            money = 0;
            $("#moneyIn").val(money.toFixed(2));
        },
        error: function (response) {
            var errorMsg = response.responseJSON.message;
            $("#messageDisplay").val(errorMsg);
        }
    });
}

function clearItems() {
    for (var i = 1; i <= 9; i++) {
        $("#item" + i + " button").empty();
    }
}