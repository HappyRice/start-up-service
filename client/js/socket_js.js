const url = 'http://localhost:9080';
let stompClient;
let gameId;
let playerId;

function connectToSocket(gameId) {
    console.log('Connecting to the game...');
    let socket = new SockJS(url + '/player/start');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    stompClient.connect({
        server: "Hello World"
    }, function (frame) {
        console.log('Connected to the frame.');
        stompClient.subscribe('/topic/game-progress/' + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log('Game state received');
            console.log(data);
            $('.winsRequired').text('Wins Required: ' + data.winsRequired);
        })
    })
}

function create_game() {
    $.ajax({
        url: url + '/game',
        type: 'POST',
        contentType: 'application/json',
        success: function (data) {
            gameId = data.entity.code;
            //refreshGameBoard(data);
            console.log('Your created a game. Game code is: ' + gameId);
            console.log('Your created a game. Game guid is: ' + data.entity.guid);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function connectToSpecificGame() {
    let name = document.getElementById('name').value;
    if (name == null || name === '') {
        alert('Please enter name');
    } else {
        gameId = document.getElementById('game_id').value;
        if (gameId == null || gameId === '') {
            alert('Please enter game code');
        }
        $.ajax({
            url: url + '/player/' + gameId + '?name=' + name,
            type: 'POST',
            contentType: 'application/json',
            success: function (data) {
                playerId = data.entity;
                connectToSocket(gameId);
                console.log('Congrats you joined the game. Your player ID is: ' + playerId);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}