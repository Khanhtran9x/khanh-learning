import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

export class WebSocketAPI {
  webSocketEndPoint = 'http://localhost:8080/ws';
  topic = '/topic/chats';
  stompClient = null;

  constructor() {
  }

  connect() {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, frame => {
      console.log('connect thanh cong');
      console.log(this.stompClient);
      this.stompClient.subscribe(this.topic, chat => {
        console.log('ChatMessage received');
        this.onMessageReceived(JSON.parse(chat.body).content);
      });
    }, this.errorCallBack);
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }
  errorCallBack(error) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      this.connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   */

  onMessageReceived(message: string) {
    console.log('ChatMessage received from Server: ' + message);
  }
}
