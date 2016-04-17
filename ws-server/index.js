var WebSocketServer = require('ws').Server
  , wss = new WebSocketServer({ port: 7070 });

wss.broadcast = function broadcast(data) {
  wss.clients.forEach(function each(client) {
    client.send(data);
  });
};

wss.on('connection', function connection(ws) {
  ws.on('message', function incoming(message) {
    wss.broadcast(message);
    console.log(message);
  });
});