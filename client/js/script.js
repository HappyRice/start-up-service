function makeAMove() {
    $.ajax({
        url: url + '/player/start?playerId=' + playerId,
        type: 'POST',
        contentType: 'application/json',
        success: function (data) {
            console.log("Made a move...")
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

// function refreshGameBoard(data) {
//     let pits = data.pits;
//     for (let i = 0; i < pits.length; i++) {
//         $("#pit_" + i).text(pits[i].stones);
//     }
//     if (data.winner != null) {
//         alert("Winner is " + data.winner.name);
//     }
//     playerTurnNow = data.playerTurn;
//
//     $("#firstPlayerName").text(data.firstPlayer.name + "'s larger pit");
//     $("#secondPlayerName").text(data.secondPlayer== null ? "second player" : data.secondPlayer.name + "'s larger pit");
//
//     if (playerType === "FIRST_PLAYER") {
//         $("#firstPlayerName").background="#1472a9";
//         $("#secondPlayerName").background="#333";
//         $("#opponentLogin").text(data.secondPlayer!= null ? data.secondPlayer.name : "");
//
//     } else {
//         $("#secondPlayerName").background ="#1472a9";
//         $("#firstPlayerName").background="#333";
//         $("#opponentLogin").text(data.firstPlayer.name);
//     }
//
// }
//